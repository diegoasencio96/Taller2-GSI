/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.controlador;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
=======
>>>>>>> 3a057b7d61171c5ac330c954b8f05e49e2acfd9a
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lenovo-G40
 */
public class Auxiliar extends RecursiveTask<String>  {
    String texto;
    int i, f;
    ArrayList<String> arr;
    Ventana1Controlador v1c;
    public Auxiliar(Ventana1Controlador v1c, ArrayList arr, int i, int f) {
        this.arr = arr;
        this.v1c = v1c;
        this.i = i;
        this.f = f;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 3a057b7d61171c5ac330c954b8f05e49e2acfd9a
    

    @Override
    protected String compute() {
<<<<<<< HEAD
        if(f - i <= 10) {
            String t = "";
            for(int i = this.i; i < this.f; i++) {
                try {
                    t += (arr.get(i)+"-"+v1c.sentenceDetect(arr.get(i))+ "\n");
                } catch (IOException ex) {
=======
        if(f - i <= 5) {
            //System.out.println("aca 1");
            String t = "";
            for(int i = this.i; i < this.f; i++) {
                try {
                    t += (arr.get(i)+"-----"+v1c.sentenceDetect(arr.get(i))+ "\n");
                } catch (Exception ex) {
>>>>>>> 3a057b7d61171c5ac330c954b8f05e49e2acfd9a
                    Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return t;
<<<<<<< HEAD
        } else {	    	
=======
        } else {	
            //System.out.println("aca 2");
>>>>>>> 3a057b7d61171c5ac330c954b8f05e49e2acfd9a
            int mid = this.i + (this.f - this.i) / 2;
            Auxiliar left  = new Auxiliar(v1c, arr, this.i, mid);
            Auxiliar right = new Auxiliar(v1c, arr, mid, this.f);
            left.fork();
<<<<<<< HEAD
            String rightResult = right.compute();
=======
            //right.fork();
            String rightResult = right.compute();
            //String rightResult = right.join();
>>>>>>> 3a057b7d61171c5ac330c954b8f05e49e2acfd9a
            String leftResult  = left.join();
            return leftResult + rightResult;
         }
    }
    
}
