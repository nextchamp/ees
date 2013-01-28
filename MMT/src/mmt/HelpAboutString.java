/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;


        
/**
 *
 * @author Aditya Mishra
 */
public class HelpAboutString {
    public static String getString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MMT Employee Evaluation System");
        sb.append("\n    ");
        sb.append("\n Created By : ");
        sb.append("\n    Aditya Mishra,");
        sb.append("\n    Homestead High School, ");
        sb.append("\n    Future Business Leaders of America ");
        sb.append("\n       ");
        sb.append("\n School Year  ");
        sb.append("\n    2012 – 2013  ");
        sb.append("\n    ");
        sb.append("\n Language Used ");
        sb.append("\n    Java  1.7.11u");
        sb.append("\n ");
        sb.append("\n Software and Tools Used");
        sb.append("\n -  NetBeans (Java IDE)");
        sb.append("\n -  MS Word (Documentation)");
        sb.append("\n -  UML (UML Design)");
        sb.append("\n -  JavaFX 2.2, DataFX, SceneBuilder 1.0");
        sb.append("\n ");
        sb.append("\n Resources");
        sb.append("\n -  Images ");
        sb.append("\n -  Initial Data  ");
        sb.append("\n  ");
        sb.append("\n All images used are taken from public domain");
        sb.append("\n    http://www.iconfinder.com/ ");
        sb.append("\n");
        sb.append("\n A GPL Licensed random data generator utility was used for initial data.");
        sb.append("\n    http://freecode.com/projects/dummy-data-generator/ ");

        return sb.toString();
    }

    public HelpAboutString() {
    }
}
