/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lenovo-G40
 */
public class Ventana1 extends JFrame {
    
    public JLabel text, text2, text3;
    public JButton baceptar, bcancelar, bbuscar;
    public JTextArea tffrase;
    
    public Ventana1() {
        iniciarComponentes();
        
        
        setTitle("Taller 2 - GSI");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private void iniciarComponentes() {
        setLayout(null);        
        text = new JLabel();
        text.setText("Ingrese una frase (en inglés): ");
        text.setBounds(10, 100, 200, 30);
        
        tffrase = new JTextArea();
        tffrase.setToolTipText("Digite aqui la frase. ");
        tffrase.setBounds(10, 130, 580, 50);
        
        baceptar = new JButton();
        baceptar.setText("Aceptar");
        baceptar.setToolTipText("Compruebe la frase.");
        baceptar.setBounds(10, 190, 100, 30);
        
        bcancelar = new JButton();
        bcancelar.setText("Cancelar");
        bcancelar.setToolTipText("Borrar la frase.");
        bcancelar.setBounds(120, 190, 100, 30);
        
        bbuscar = new JButton();
        bbuscar.setText("Seleccionar Archivo");
        bbuscar.setToolTipText("Seleccionar archivos");
        bbuscar.setBounds(270, 190, 150, 30);
        
        
        text2 = new JLabel();
        text2.setText("La frase es de tipo: ");
        text2.setBounds(10, 270, 200, 30);
        
        text3 = new JLabel();
        text3.setText("");
        text3.setBounds(130, 270, 100, 30);
        
        
        
        add(text);
        add(tffrase);
        add(baceptar);
        add(bcancelar);
        add(bbuscar);
        add(text2);
        add(text3);
    }
    
    
}
