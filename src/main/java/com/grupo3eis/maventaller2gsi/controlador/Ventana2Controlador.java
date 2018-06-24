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
    JLabel ltiempo, llineas, laciertos, lporcentaje;
    public JScrollPane scroll;
    public JTable tabla;
    public String res;
    public DefaultTableModel modelo;
    public int naciertos, nlineas;
    public double porcentaje_aciertos;
    
    public Ventana2Controlador(Ventana2 v2, String res, float tiempo, int nlineas, int naciertos, double porcentaje_aciertos) {
        this.scroll = v2.scroll;
        this.tabla = v2.tabla;
        this.ltiempo = v2.ltiempo;
        this.llineas = v2.llineas;
        this.laciertos = v2.laciertos;
        this.lporcentaje = v2.lporcentaje;        
        this.res = res;
        this.nlineas = nlineas;
        this.naciertos = naciertos;
        this.porcentaje_aciertos = porcentaje_aciertos;
        
        ltiempo.setText(ltiempo.getText()+" "+Float.toString(tiempo)+" seg");
        llineas.setText("Numero de lineas:  "+this.nlineas);
        laciertos.setText("Numero de aciertos:  "+this.naciertos);
        lporcentaje.setText("Porcentaje de aciertos:  "+this.porcentaje_aciertos+" %");
        
        //System.out.println(res);
        String aux[] = res.split("\n");
        
        Vector columnas = new Vector();
       
        String[] arr = aux[0].split("-----");
        columnas.add(arr[0]);
        columnas.add(arr[1]);
        columnas.add(arr[2]);
        columnas.add(arr[3]);
       
        modelo = new DefaultTableModel(null,columnas);
        
        for(int i=1;i<aux.length;i++) {
            Vector fila = new Vector();
            
            String[] arr1 = aux[i].split("-----");
            fila.add(arr1[0]);
            fila.add(arr1[1]);
            fila.add(arr1[2]);
            fila.add(arr1[3]);
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
