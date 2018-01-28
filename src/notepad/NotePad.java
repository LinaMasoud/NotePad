/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author LinaMasoud
 */
public class NotePad extends Application {
    public MenuBar myBar;
    public Menu file;
    public Menu edit;
    public Menu help;
    public MenuItem item1;
    public MenuItem item2;
    public MenuItem item3;
    public MenuItem item4;
    public MenuItem item5;
    public MenuItem item6;
    public MenuItem item7;
    public MenuItem item8;
    public MenuItem item9;
    public MenuItem item10;
    public MenuItem item11;
    public MenuItem item12;
    public TextArea textArea;
    public BorderPane myPane;
    public String selectedString;
    public Stage myStage;
    public String filePath;
        
    @Override
    public void init() throws Exception{
        myBar = new MenuBar();
        file = new Menu("File");
        edit = new Menu("Edit");
        help = new Menu("Help");
        item1 = new MenuItem("New");
        item2 = new MenuItem("Open");
        item3 = new MenuItem("Save");
        item4 = new MenuItem("Exit");
        item5 = new MenuItem("Undo");
        item6 = new MenuItem("Cut");
        item7 = new MenuItem("Copy");
        item8 = new MenuItem("Paste");
        item9 = new MenuItem("Delete");
        item10 = new MenuItem("Select All");
        item11 = new MenuItem("About NotePad");
        item12 = new MenuItem("Compile File");
        textArea = new TextArea();
        myPane = new BorderPane();       
    }
    
        @Override
    public void start(Stage primaryStage) {
        myStage = primaryStage;
        item1.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        item2.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        item3.setAccelerator(KeyCombination.keyCombination("Ctrl+a"));
        item4.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        file.getItems().addAll(item1,item2,item3,item4);
        edit.getItems().addAll(item5,item6,item7,item8,item9,item10);
        help.getItems().addAll(item11,item12);
        myBar.getMenus().addAll(file,edit,help);
        myPane.setTop(myBar);
        myPane.setCenter(textArea);
        Scene myScene = new Scene(myPane, 600, 400);    
        primaryStage.setTitle("UnTitled File..");
        primaryStage.setScene(myScene);
        primaryStage.show();
        NotePadMethods();
    }
    
    public void NotePadMethods() {
        
        item1.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            if(!textArea.getText().equalsIgnoreCase("")){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Be Careful!");
                alert.setContentText("Do you want to save this ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    File savedFile;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save At..");
            savedFile = fileChooser.showSaveDialog(myStage);
            myStage.setTitle(savedFile.getName());
            filePath = savedFile.getAbsolutePath();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            if (file != null) {
                try {
                    fileWriter = new FileWriter(filePath, true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                printWriter = new PrintWriter(fileWriter);
                printWriter.println();
                printWriter.println(textArea.getText()); 
                printWriter.close();
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
           
                }
            textArea.setText("");
            myStage.setTitle("UnTitled File..");
            
            }
        }
    }
});

        item2.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            File openedFile;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File..");
            openedFile = fileChooser.showOpenDialog(myStage);
            myStage.setTitle(openedFile.getName());
            filePath = openedFile.getAbsolutePath();
            try{
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    int size = fis.available();
                    byte[] b = new byte[size];
                    fis.read(b);
                    textArea.setText(new String(b));
                }  
            }
            catch(IOException ex){
                ex.printStackTrace();
            }   
        }
    });

        item3.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            File savedFile;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save At..");
            savedFile = fileChooser.showSaveDialog(myStage);
            myStage.setTitle(savedFile.getName());
            filePath = savedFile.getAbsolutePath();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            if (file != null) {
                try {
                    fileWriter = new FileWriter(filePath, true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                printWriter = new PrintWriter(fileWriter);
                printWriter.println();
                printWriter.println(textArea.getText()); 
                printWriter.close();
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    });            

        item4.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            if(!textArea.getText().equalsIgnoreCase("")){
                if (myStage.getTitle().equals("UnTitled File..")){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Be Careful!");
                alert.setContentText("Do you want to save this ?");

                ButtonType buttonTypeOne = new ButtonType("Yes");
                ButtonType buttonTypeTwo = new ButtonType("No");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne){
                    // ... user chose "One"
                    File savedFile;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save At..");
            savedFile = fileChooser.showSaveDialog(myStage);
            myStage.setTitle(savedFile.getName());
            filePath = savedFile.getAbsolutePath();
            FileWriter fileWriter = null;
            PrintWriter printWriter = null;
            if (file != null) {
                try {
                    fileWriter = new FileWriter(filePath, true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                printWriter = new PrintWriter(fileWriter);
                printWriter.println();
                printWriter.println(textArea.getText()); 
                printWriter.close();
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
             Platform.exit(); 
            
              }
                     
                else if (result.get() == buttonTypeTwo) {
                    // ... user chose "Two"
                     Platform.exit(); 
                    }
                else{
                    
                }
                 
                }
            else{
                Platform.exit(); 
            }
            } 
        }
    });

        item5.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            textArea.undo(); 
        }
    });

        item6.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            selectedString = textArea.getSelectedText();
            textArea.replaceSelection("");
            
        }
    });

        item7.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            selectedString = textArea.getSelectedText(); 
        }
    });

        item8.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            textArea.replaceSelection(selectedString); 
        }
    });

        item9.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            textArea.replaceSelection(""); 
        }
    });

        item10.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            textArea.selectAll();
        }
    });

        item11.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About NotePad..");
            alert.setHeaderText(null);
            alert.setContentText("This NotePad Developed By Lina Hamed in 4,Jan,2018");
            alert.showAndWait(); 
        }
    });
        
        item12.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle (ActionEvent e){
            String s;
            Process p;
            String compiledFile = filePath;
        try {
            p = Runtime.getRuntime().exec(new String[]{"javac",compiledFile});
            BufferedReader be = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = be.readLine()) != null){ 
                textArea.appendText("\n" + s);               
            }
            if (be.readLine() == null){
              textArea.appendText("\n" + "Your File is Compiled Correctly..!"); 
            }
            be.close();
            } 
        catch (Exception ex) {
            ex.printStackTrace();
        }        
        }
    });
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
