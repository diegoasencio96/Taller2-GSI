/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.controlador;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import com.grupo3eis.maventaller2gsi.vista.Ventana1;
import com.grupo3eis.maventaller2gsi.vista.Ventana2;

/**
 *
 * @author lenovo-G40
 */
public class Ventana1Controlador implements ActionListener {
    
    private Ventana1 obj;
    
    
    public String sentenceDetect(String paragraph) throws InvalidFormatException, IOException {
	
        //String sentence = "John is 27 years old.";
        InputStream is1 = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-token.bin");
	TokenizerModel tokenModel = new TokenizerModel(is1);
	Tokenizer tokenizer = new TokenizerME(tokenModel);
	String tokens[] = tokenizer.tokenize(paragraph);
 	// Parts-Of-Speech Tagging
	// reading parts-of-speech model to a stream 
        InputStream posModelIn = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-pos-maxent.bin");
	
	// loading the parts-of-speech model from stream
	POSModel posModel = new POSModel(posModelIn);
	// initializing the parts-of-speech tagger with model 
	POSTaggerME posTagger = new POSTaggerME(posModel);
	// Tagger tagging the tokens
	String tags[] = posTagger.tag(tokens);
        
        boolean sol = false;
        for (int i=0;i<tags.length-1;i++) {
            System.out.println(tags[i]);
            if(tags[i].equals("PRP")||tags[i].equals("NNP")||tags[i].equals("NNS")||tags[i].equals("WP")||tags[i].equals("NN")) {
                if(tags[i+1].equals("VB")||tags[i+1].equals("VBD")||tags[i+1].equals("VBP")||tags[i+1].equals("VBN")) {
                    sol = true;
                }
            }
        }
        
        posModelIn.close();
        is1.close();
        if(sol) {
            JOptionPane.showMessageDialog(null, "Activo");
            return "Activo";
        }else {
            JOptionPane.showMessageDialog(null, "Pasivo");
            return "Pasivo";
        }
    }
    
    private String[] abrirArchivo() {
        String[] sol = new String[2];
        String aux="";   
        String texto="";
        File abre = null;
        try {
            JFileChooser file=new JFileChooser();
            file.showOpenDialog(obj);
            abre=file.getSelectedFile();

            if(abre!=null) {     
               FileReader archivos=new FileReader(abre);
               BufferedReader lee=new BufferedReader(archivos);
               while((aux=lee.readLine())!=null)
               {
                  texto+= aux+ "\n";
               }
                  lee.close();
            }    
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,ex+"" +
                 "\nNo se ha encontrado el archivo",
                       "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
        //JOptionPane.showMessageDialog(null, texto);
        System.out.println(texto);
        sol[0] = abre.getAbsolutePath();
        sol[1] = texto;
        return sol;//El texto se almacena en el JTextArea
    }
    
    public Ventana1Controlador(Ventana1 obj) {
        this.obj=obj;
        this.obj.baceptar.addActionListener(this);
        this.obj.baceptar.addActionListener(this);
        this.obj.bbuscar.addActionListener(this);
       // this.obj.tffrase.setText("This is a statement. This is another statement. Now is an abstract word for time, that is always flying.");
        this.obj.tffrase.setText("John is 27 years old.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == obj.baceptar) {
            if(obj.tffrase.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "No ha ingresado una frase. ");
            }else {
                try {
                    JOptionPane.showMessageDialog(null, "Comprobando frase -> "+obj.tffrase.getText());
                    obj.text2.setText(sentenceDetect(obj.tffrase.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(Ventana1Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(ae.getSource() == obj.bbuscar) {
            String arc[]  = abrirArchivo();
            if(arc[0] == null || arc[0].equals("")) {
                
            }else {
                //obj.tffrase.setText(arc[1]);
                obj.ruta.setText(arc[0]);
                new Ventana2();
            }
        }
    
    }
    
}
