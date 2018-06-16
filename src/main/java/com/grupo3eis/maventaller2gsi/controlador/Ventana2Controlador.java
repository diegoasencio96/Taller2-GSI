/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.controlador;

import com.grupo3eis.maventaller2gsi.vista.Ventana2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo-G40
 */
public class Ventana2Controlador implements ActionListener {
    JLabel ltiempo;
    public JScrollPane scroll;
    public JTable tabla;
    public String res;
    public DefaultTableModel modelo;
    
    public Ventana2Controlador(Ventana2 v2, String res, long tiempo) {
        this.scroll = v2.scroll;
        this.tabla = v2.tabla;
        this.ltiempo = v2.ltiempo;
        this.res = res;
        
        ltiempo.setText(ltiempo.getText()+" "+Long.toString(tiempo)+" seg");
        Vector columnas = new Vector();
        columnas.add("Frase en ingles");
        columnas.add("Resultado");
        
        modelo = new DefaultTableModel(null,columnas);
        //System.out.println(res);
        String aux[] = res.split("\n");
        
        for(int i=0;i<aux.length;i++) {
            Vector fila = new Vector();
            fila.add(aux[i].split("-")[0]);
            fila.add(aux[i].split("-")[1]);
            modelo.insertRow(modelo.getRowCount(),fila);
        }
        
        
        tabla.setModel(modelo);
        
    }
    
    public void setResultados(String res) {
        this.res = res;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
