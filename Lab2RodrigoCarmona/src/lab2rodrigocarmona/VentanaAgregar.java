/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2rodrigocarmona;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Kmkze
 */
public class VentanaAgregar extends Application {
    int aux = 0;
    ArrayList<String> listaPalabras = new ArrayList<String>();
    @Override
    public void start(Stage primaryStage) {
        GridPane LA = new GridPane();
        LA.setHgap(10);
        LA.setVgap(10);
        
        
        Label lbDiccionario = new Label("Diccionario de palabras técnicas de informática");
        Label lbIngresar = new Label("Ingresar Palabra: ");
        Label lbDef = new Label("Deficion de la palabra: ");
        
        TextArea tapalabra = new TextArea();
    //     tapalabra.setPrefWidth(20);
      //   tapalabra.setPrefHeight(10);
      tapalabra.setPrefSize(20, 10);
        TextArea tasignificado = new TextArea();
      // tfsignificado.setPrefWidth(300);
       // tfsignificado.setPrefHeight(150);
        Button bingresar = new Button("Ingresar");
        bingresar.setMinWidth(30);
        bingresar.setMinHeight(30);
        Button blimpiar = new Button("Limpiar");
        blimpiar.setMinWidth(30);
        blimpiar.setMinHeight(30);
        
         //       (Columna,Fila,espaciocolumna,espacioFila)
        LA.setConstraints(lbDiccionario, 1, 1);
        LA.setConstraints(lbIngresar, 1, 5);
        LA.setConstraints(tapalabra, 1, 6 );
        LA.setConstraints(lbDef, 1, 7);
        LA.setConstraints(tasignificado, 1, 8);
        LA.setConstraints(bingresar, 1, 9);
        LA.setConstraints(blimpiar, 1, 10);
        LA.getChildren().addAll(lbDiccionario, lbIngresar, tapalabra, lbDef, tasignificado,bingresar, blimpiar);
        
        bingresar.setOnAction(e -> {
            String palabra = (tapalabra.getText()).trim();
            palabra.toLowerCase();
            String significado = (tasignificado.getText()).trim();
            boolean repetido= false;
 
             if ((palabra == "") || (significado == "") ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error ingresar palabra");
                alert.setHeaderText("Error agregar palabra");
                alert.setContentText("Rellene los campos.");
                alert.showAndWait();
             }else{
                 final String dir = System.getProperty("user.dir");
                 System.out.println("current dir = " + dir);
                 
                 File ruta = new File("diccionario.txt");
                 
        
                 if(ruta.exists() && !ruta.isDirectory()) { 
                    // do something 
                    if(aux==0){
                        try{
                            
                        //HAGO UNA LISTA DONDE GUARDARE LAS PALABRAS DEL DICCIOANRIO
                            File myObj = new File("diccionario.txt");
                            Scanner myReader = new Scanner(myObj);
                     //UN CICLO PARA EXTRAER LAS PALABRAS GUARDADAS
                            while (myReader.hasNextLine()) {
                                
                                String data = myReader.nextLine();
                                String[] informacion = data.split("=");
                                String p1=informacion[0];
                         // ACA ENVIO LAS PALABRAS A AL ARRAY
                                listaPalabras.add(p1.toLowerCase());
                      //   String p3=informacion[2];
                            }
                            
                     // aux para marcar que ya se a extraido las palabras del archivo
                            aux=1;
                       
                        }catch(FileNotFoundException x){
                            System.out.println("Un error ha ocurrido");
                            x.printStackTrace();
                        }
                        
                    // reviso si la palabra nueva ya esta en el archivo
                        for (String p : listaPalabras) {
                            if(palabra.toLowerCase().equals(p)){
                             repetido = true;
                            }
                        }
                        if(repetido==false){
                            escribir(dir+ "\\diccionario.txt",palabra.toLowerCase() + "=" + significado + "\n");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Palabra Registrada!");
                            alert.setHeaderText("Palabra agregada");
                            alert.setContentText("Se agrego la palabra " + palabra + " al diccionario");
                            alert.showAndWait();
                            listaPalabras.add(palabra.toLowerCase());
                        }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error ingresar palabra");
                            alert.setHeaderText("Error agregar palabra");
                            alert.setContentText("Esta palabra ya esta agregada");
                            alert.showAndWait();                    
                        }
                    }else{
                          for (String p : listaPalabras) {
                            if(palabra.toLowerCase().equals(p)){
                             repetido = true;
                            }
                        }
                        if(repetido==false){
                            escribir(dir+ "\\diccionario.txt",palabra.toLowerCase()+ "=" + significado + "\n");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Palabra Registrada!");
                            alert.setHeaderText("Palabra agregada");
                            alert.setContentText("Se agrego la palabra " + palabra + " al diccionario");
                            alert.showAndWait();
                            listaPalabras.add(palabra.toLowerCase());
                        }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error ingresar palabra");
                            alert.setHeaderText("Error agregar palabra");
                            alert.setContentText("Esta palabra ya esta agregada");
                            alert.showAndWait();                    
                        }
                    
                    
                    }
                 }else{
                     //SI EL ARCHIVO NO EXISTE, SE CREARA UNO Y SE AGREGARA LA PALABRA CON SU SIGNIFICADO SEPARADO POR UN =
                     try {  
                         ruta.createNewFile();
                     } catch (IOException ex) {
                         Logger.getLogger(VentanaAgregar.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     escribir(dir+ "\\diccionario.txt",palabra.toLowerCase() + "= " + significado + "\n");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Palabra Registrada!");
                        alert.setHeaderText("Palabra agregada");
                        alert.setContentText("Se agrego la palabra " + palabra + " al diccionario");
                        alert.showAndWait();
                        listaPalabras.add(palabra.toLowerCase());
                 }
             }
             
            
        });
        
        blimpiar.setOnAction(e -> {
             tapalabra.setText("");
             tasignificado.setText("");
        });
        Scene scene = new Scene(LA, 500, 500);
        primaryStage.setTitle("Agregar significado a diccionario");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Esta funcion agrega texto a un archivo.
     * @param direccion la ruta donde se encuentra el archivo a escribir.
     * @param text el texto que desea ser agregado en el texto.
     */
    public void escribir(String direccion, String text){
        try {
            Files.write(Paths.get(direccion),text.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
        }
    }
    
    
    
  
    
}
