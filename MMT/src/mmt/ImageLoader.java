/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author sanjeev
 */
public class ImageLoader implements Runnable, FileVisitor<Path> {
    private AnchorPane pane;
    // Directory to start scanning for pics        
    private String root;

    private ImageView current;
    private ImageView next;

    Boolean paused = false;
    //private Image image =null;
    private int transitionDelayInSec = 1;
    private int pauseDurationInSec = 3;
    
    public ImageLoader(AnchorPane pane) throws Exception {
        if (pane!=null) {
            this.pane = pane;
            this.width = (int)pane.getPrefWidth();
            this.height = (int)pane.getPrefHeight();
        }
        //root = AppSettings.HelpRootPath;//"E:/workspace/ees/MMT/data/help";
        root = AppUtils.getDataFile(AppSettings.HelpRootPath, "").getPath();
    }
    
    BlockingQueue<Image> images = new ArrayBlockingQueue(5);
    boolean complete;
    int width;
    int height;

    @Override
    public void run() {
        try {
            Files.walkFileTree(Paths.get(root), this);
            complete = true;
            accumulator.addAll(images);
            images.clear();
            System.out.println("help load completes");

        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName())
                    .log(Level.INFO, null, ex.getMessage());
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path t, BasicFileAttributes bfa)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path t, BasicFileAttributes bfa)
            throws IOException {
        try {
            Image image = new Image(t.toUri().toString(),
                    width, height, true, true, false);

            if (!image.isError()) {
                images.put(image);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageLoader.class.getName())
                    .log(Level.SEVERE, null, ex);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path t, IOException ioe)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path t, IOException ioe)
            throws IOException {
        return FileVisitResult.CONTINUE;
    }
    
    void setNextImage(final AnchorPane pane, Image image) {        
        ObservableList<Node> c = pane.getChildren();

        if (current != null)
            c.remove(current);

        current = next;
        next = null;

        // Create fade-in for new image.
        next = new ImageView(image);

        next.setFitHeight(height);
        next.setFitWidth(width);
        next.setPreserveRatio(true);
        next.setOpacity(0);

        c.add(next);

        FadeTransition fadein = new FadeTransition(Duration.seconds(transitionDelayInSec), next);

        fadein.setFromValue(0);
        fadein.setToValue(1);

        ScaleTransition dropout = new ScaleTransition(Duration.seconds(transitionDelayInSec), current);
        dropout.setInterpolator(Interpolator.EASE_OUT);
        dropout.setFromX(1);
        dropout.setFromY(1);
        dropout.setToX(0.75);
        dropout.setToY(0.75);

        PauseTransition delay = new PauseTransition(Duration.seconds(pauseDurationInSec));

         SequentialTransition seqTransition = new SequentialTransition(
            new ParallelTransition(fadein, dropout), delay);
         
        seqTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (paused)return;
                Image image = getNextImage();

                if (image != null) {
                    setNextImage(pane, image);
                }
            }
        });

        seqTransition.playFromStart();
    }
    
    synchronized Image getPrevImage() {
        if (complete && !accumulator.isEmpty()) {            
            if (currentIndex <0) {
                //System.out.println
                //    (String.format("resetting currentIndex as currentIndex<%d>==size<%d> ",accumulator.size(), currentIndex));

                currentIndex = accumulator.size()-1;
            }
            //System.out.println("currentIndex: " + currentIndex);
            try {
                return accumulator.get(currentIndex--);
            }
            catch (Throwable t) {
                return accumulator.get(currentIndex--);
            }
        }
        return null;
    }
    
    synchronized Image getNextImage() {
        if (complete) {            
            if (!accumulator.isEmpty()) {
                if (currentIndex==accumulator.size()) {
                    //System.out.println
                    //    (String.format("resetting currentIndex as currentIndex<%d>==size<%d> ",accumulator.size(), currentIndex));
                    currentIndex =0;
                }
                
                //System.out.println("currentIndex: " + currentIndex);
                try {
                    return accumulator.get(currentIndex++);
                }
                catch (Throwable t) {
                    return accumulator.get(currentIndex++);
                }
            }
            return null;
        }
        try {
            Image image = images.take();
            accumulator.add(image);
            return image;
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void togglePause() {
        paused =!paused;
    }
    
    private List<Image> accumulator = new ArrayList<Image>();
    int currentIndex =0;
}
