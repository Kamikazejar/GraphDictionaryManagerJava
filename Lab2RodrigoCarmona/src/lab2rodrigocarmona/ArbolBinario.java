/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2rodrigocarmona;

import javafx.scene.control.Alert;

/**
 *
 * @author Kmkze
 */
public class ArbolBinario {
    NodoArbol raiz;
    public ArbolBinario(){
        raiz = null;
    
    }
   /**
    * Este metodo agrega un nuevo nodo al arbol de busqueda.
    * @param c String que viene a ser la clave del nodo.
    * @param de String que viene a ser la definición del nodo.
    */
    public void agregarNodo(String c, String de){
        NodoArbol nuevo = new NodoArbol(c ,de);
        if(raiz == null){
            raiz = nuevo;
        
        }else{
            NodoArbol auxiliar= raiz;
            NodoArbol padre;
        
            while(true){
                padre = auxiliar;
                //Aca lo que hago es comprar 2 strings, con el compare to, si me da -1, menor que 0 es por que la palabra va antes 
                //en el orden alfabetico entonces se pone a la izquierda
                if(c.compareTo(auxiliar.clave)< 0){
                    auxiliar = auxiliar.hijoIzquierdo;
                    if(auxiliar == null){
                        padre.hijoIzquierdo = nuevo;
                        return;
                    }
                //Aca lo mismo pero al revez, si es que da 1,mayor que 0 es por que va despues en el orden alfabetico por lo que se 
                // pone a la derecha
                }else{
                    auxiliar = auxiliar.hijoDerecho;
                    if(auxiliar == null){
                        padre.hijoDerecho = nuevo;
                        return;
                    }
                
                }
                
                }
            }
        
        }
    /**
     * Este metodo busca una palabra clave en el arbol de busqueda.
     * @param c String que sera la clave la cual se buscara.
     * @return Un String para señalar si no se encontro "false" o si se encontro devolvera la palabra clave.
     */
    public String buscar(String c){
         NodoArbol auxiliar= raiz;
         NodoArbol padre;
          while(true){
                padre = auxiliar;
                //Aca lo que hago es comprar 2 strings, con el compare to, si me da -1, menor que 0 es por que la palabra va antes 
                //en el orden alfabetico entonces se pone a la izquierda
                if(c.compareTo(auxiliar.clave)< 0){
                    auxiliar = auxiliar.hijoIzquierdo;
                    if(auxiliar == null){
                        return "false";
                    }
                //Aca lo mismo pero al revez, si es que da 1,mayor que 0 es por que va despues en el orden alfabetico por lo que se 
                // pone a la derecha
                }else if (c.compareTo(auxiliar.clave)> 0){
                    auxiliar = auxiliar.hijoDerecho;
                    if(auxiliar == null){
                        return "false";
                    }
                }else{
                    return auxiliar.def;
                }
          }
    }  
}
    

