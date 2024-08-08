/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2rodrigocarmona;

/**
 *
 * @author Kmkze
 */
public class NodoArbol {
 //   int dato;
    String clave;
    String def; 
    NodoArbol hijoIzquierdo,hijoDerecho;
    public NodoArbol(String c, String de){
       // this.dato = d;
        this.clave = c;
        this.def = de;
        this.hijoDerecho=null;
        this.hijoIzquierdo=null;
        
    
    }
    @Override
    public String toString() {
        //return "NodoArbol{" + "dato=" + dato + ", clave=" + clave + ", def=" + def + '}';yy
        return "NodoArbol{" + ", clave=" + clave + ", def=" + def + '}';
    }
}
