/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Aditya Mishra
 */
public class DataManager {
        
        public ObservableList<String[]> masterData = null;//FXCollections.observableArrayList();
        public ObservableList<String[]> filteredData = null;//FXCollections.observableArrayList();
        public ObservableList<TableColumn<String[], ?>> columnsData = null;
        
        public DataManager() {
        }
}
    