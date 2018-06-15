/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.vista;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author lenovo-G40
 */
public class Ventana2 extends JFrame {
    
    public JLabel text;
    public JTable tabla;
    public JScrollPane scroll;
    
    public Ventana2() {
        iniciarComponentes();
        setTitle("Taller 2 - GSI");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private void iniciarComponentes() {
        setLayout(null);        
        text = new JLabel();
        text.setText("Listado de frases (en inglés): ");
        text.setBounds(10, 10, 200, 30);
        
        
        
        tabla = new JTable();
        scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 50, 670, 400);
       
        
        add(text);
        add(scroll);

    }
    
}
