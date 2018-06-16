/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.controlador;

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
    
    

    @Override
    protected String compute() {
        if(f - i <= 5) {
            //System.out.println("aca 1");
            String t = "";
            for(int i = this.i; i < this.f; i++) {
                try {
                    t += (arr.get(i)+"-----"+v1c.sentenceDetect(arr.get(i))+ "\n");
                } catch (Exception ex) {
                    Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return t;
        } else {	
            //System.out.println("aca 2");
            int mid = this.i + (this.f - this.i) / 2;
            Auxiliar left  = new Auxiliar(v1c, arr, this.i, mid);
            Auxiliar right = new Auxiliar(v1c, arr, mid, this.f);
            left.fork();
            //right.fork();
            String rightResult = right.compute();
            //String rightResult = right.join();
            String leftResult  = left.join();
            return leftResult + rightResult;
         }
    }
    
}
