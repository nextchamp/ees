/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotResult;
import javafx.scene.image.WritableImage;
import javafx.util.Callback;

/**
 *
 * @author Aditya Mishra
 */
public class PrintCallback implements Callback<SnapshotResult, Void> {
    @Override
    public Void call(SnapshotResult param) {
        try {
            if (param != null) {
                WritableImage wi = param.getImage();
                if (wi != null) {

                    java.awt.image.BufferedImage bufferedImage = null;
                    bufferedImage = SwingFXUtils.fromFXImage(wi, null);
                    if (bufferedImage != null) {
                        PrintUtils.print(bufferedImage);
                    }
                }
                System.out.println("Job is printed.");
                return null;
            } 
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Job could not be printed.");
        return null;
    }
}
