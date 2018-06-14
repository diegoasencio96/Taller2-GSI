/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lenovo-G40
 */
public class Ventana2 extends JFrame {
    
    public JLabel text;
    public JButton baceptar, bcancelar;
    
    public Ventana2() {
        iniciarComponentes();
        setTitle("Taller 2 - GSI");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setVisible(true);
    }
    
    private void iniciarComponentes() {
        setLayout(null);        
        text = new JLabel();
        text.setText("Listado de frases (en inglés): ");
        text.setBounds(10, 100, 200, 30);
        
        
        
        baceptar = new JButton();
        baceptar.setText("Comprobar");
        baceptar.setToolTipText("Compruebe las frases.");
        baceptar.setBounds(10, 190, 100, 30);
        
        bcancelar = new JButton();
        bcancelar.setText("Atras");
        bcancelar.setToolTipText("Devolver de ventana");
        bcancelar.setBounds(120, 190, 100, 30);
       
        
        add(text);
        add(baceptar);
        add(bcancelar);

    }
    
}
