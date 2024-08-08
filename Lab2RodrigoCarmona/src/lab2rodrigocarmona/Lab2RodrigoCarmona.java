/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2rodrigocarmona;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// ACA VOY HACER LA VENTANA PARA CONVERTIR LAS PALABRAS Y SIGNIFICADOS EN UN NODO Y CON ELLO HACER UN ARBOL
/**
 *
 * @author Kmkze
 */
public class Lab2RodrigoCarmona extends Application {
    int aux= 0;
    @Override
    public void start(Stage primaryStage) {
        
        ArbolBinario arbol = new ArbolBinario();
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
                        String p2=informacion[1];
                         // ACA ENVIO LAS PALABRAS A AL ARRAY
                        arbol.agregarNodo(p1,p2);
                      //   String p3=informacion[2];
                    }
                            
                     // aux para marcar que ya se a extraido las palabras del archivo
                            aux=1;
                       
                        }catch(FileNotFoundException x){
                            System.out.println("Un error ha ocurrido");
                            x.printStackTrace();
                        }

                 }
        
        }
        
        
        
        GridPane LAR = new GridPane();
        LAR.setHgap(10);
        LAR.setVgap(10);
        Label lbDiccionario = new Label("Diccionario de palabras técnicas de informática");
        Label lbIngresar = new Label("Buscar Palabra: ");
        Label lbDef = new Label("Deficion de la palabra: ");
        
        TextArea tapalabra = new TextArea();
        tapalabra.setPrefSize(20, 10);
        
        TextArea tasignificado = new TextArea();
        tasignificado.setEditable(false);
        Button bbuscar= new Button("Buscar");
        bbuscar.setMinWidth(30);
        bbuscar.setMinHeight(30);
        Button blimpiar = new Button("Limpiar");
        blimpiar.setMinWidth(30);
        blimpiar.setMinHeight(30);
        
        
        LAR.setConstraints(lbDiccionario, 1, 1);
        LAR.setConstraints(lbIngresar, 1, 5);
        LAR.setConstraints(tapalabra, 1, 6 );
        LAR.setConstraints(lbDef, 1, 7);
        LAR.setConstraints(tasignificado, 1, 8);
        LAR.setConstraints(bbuscar, 1, 9);
        LAR.setConstraints(blimpiar, 1, 10);
        LAR.getChildren().addAll(lbDiccionario, lbIngresar, tapalabra, lbDef, tasignificado,bbuscar, blimpiar);
        
         bbuscar.setOnAction(e -> {
            String palabra = (tapalabra.getText()).trim();
            String significado = (tasignificado.getText()).trim();
             if (palabra == "" ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error buscar palabra");
                alert.setHeaderText("Error al buscar la palabra");
                alert.setContentText("Rellene los campos.");
                alert.showAndWait();
             }else{
                 String resultado =arbol.buscar(palabra.toLowerCase());
                 if(resultado.equals("false")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error buscar la palabra");
                    alert.setHeaderText("Error al buscar la palabra");
                    alert.setContentText("No se ha encontrado tal palabra en el arbol");
                    alert.showAndWait();
                 
                 
                 }else{
                    tasignificado.setText(resultado);
                 
                 }
             
             }
        });
        
          blimpiar.setOnAction(e -> {
             tapalabra.setText("");
             tasignificado.setText("");
        });
        
        
        Scene scene = new Scene(LAR, 500, 500);
        primaryStage.setTitle("Buscar significado a diccionario");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
        

    public static void main(String[] args) {
        launch(args);
    }
    
}

//Detalle de que las palabras esten primero mayuscula