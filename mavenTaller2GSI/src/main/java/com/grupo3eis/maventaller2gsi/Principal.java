/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi;

import com.grupo3eis.maventaller2gsi.controlador.Ventana1Controlador;
import com.grupo3eis.maventaller2gsi.vista.Ventana1;

/**
 *
 * @author lenovo-G40
 */
public class Principal {
    
    public static void main(String[]args) {
        Ventana1 v1 = new Ventana1();
        Ventana1Controlador v1c = new Ventana1Controlador(v1);
    }
}
