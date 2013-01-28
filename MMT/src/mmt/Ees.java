/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxResult;
import se.mbaeumer.fxmessagebox.MessageBoxType;

/**
 *
 * @author Aditya Mishra
 */
public class Ees extends Application {
    
    private Parent root = null;
    @Override
    public void start(Stage stage) throws Exception {
        /*Parent*/ root = FXMLLoader.load(getClass().getResource("mmt.fxml"));
        
        // No decoration and minimize, maximize, close options
        //stage.initStyle(StageStyle.UNDECORATED);
        
        // Only, close button is shown.
        //stage.initStyle(StageStyle.UTILITY);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        //
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override public void handle(WindowEvent e) {
                        try {
                            boolean b = false;
                            Object o = e.getSource();
                            o.toString();
                            Stage st = (Stage) o;
                            Scene sc = st.getScene();
                            sc.toString();
                            
                            //e.target.root.isDirty();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        MessageBox mb = new MessageBox("Data has been changed. Do you still want to close?", MessageBoxType.YES_NO);
                        mb.showAndWait();

                        if (mb.getMessageBoxResult() == MessageBoxResult.YES){
                            System.out.println("YES");
                        }else{
                            System.out.println("NO");
                            e.consume();
                        }
                    }
        });
        
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
