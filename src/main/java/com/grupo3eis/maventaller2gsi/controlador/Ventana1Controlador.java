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
        
        boolean sol = true;
        System.out.println(tags[0]);
        for (int i=0;i<tags.length-1;i++) {
            //System.out.println(tags[i]);
            if(tags[i].equals("PRP")||tags[i].equals("NNP")||tags[i].equals("NNS")||tags[i].equals("WP")||tags[i].equals("NN")) {
                if(tags[i+1].equals("VB")||tags[i+1].equals("VBD")||tags[i+1].equals("VBP")||tags[i+1].equals("VBN")||tags[i+1].equals("VBZ")||tags[i+1].equals("MD")) {
                    sol = true;
                }
            }
        } 
        // Validar voz pasiva (to be + verbo)
        for (int i=0;i<tags.length-3;i++) {
            System.out.println(tags[i]);
            if(tokens[i].equals("is")||tokens[i].equals("were")||tokens[i].equals("was")||tokens[i].equals("be")||tokens[i].equals("are")) {
                if(tags[i+1].equals("VB")||tags[i+1].equals("VBD")||tags[i+1].equals("VBP")||tags[i+1].equals("VBN")||tags[i+1].equals("VBZ")) {
                    if(tokens[i+2].equals("by")||tokens[i+2].equals("on")||tokens[i+2].equals("to")){
                        sol = false;
                    }
                }
                else{
                    if(tokens[i+3].equals("by")||tokens[i+2].equals("on")||tokens[i+2].equals("to")){
                        sol = false;
                    }
                }
            }
        } 
        posModelIn.close();
        is1.close();
        if(sol) {
            //JOptionPane.showMessageDialog(null, "Activo");
            return "activo";
        }else {
            //JOptionPane.showMessageDialog(null, "Pasivo");
            return "pasivo";
        }
    }
    
    private String abrirArchivo() {
        //String[] sol = new String[2];
        String aux="";   
        String texto="";
        File abre = null;
        try {
            JFileChooser file=new JFileChooser();
            file.showOpenDialog(obj);
            abre=file.getSelectedFile();
            int c = 0;
            if(abre!=null) {     
                JOptionPane.showMessageDialog(null, "Leyendo datos del archivo\nEspere un momento por favor.");
               FileReader archivos=new FileReader(abre);
               BufferedReader lee=new BufferedReader(archivos);
               while((aux=lee.readLine())!=null)
               {
                  texto+= (aux+"-"+sentenceDetect(aux)+ "\n");
                  
                  if (c < 0) break;
                  c++;
               }
                  lee.close();
            }    
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,ex+"" +
                 "\nNo se ha encontrado el archivo",
                       "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
        //JOptionPane.showMessageDialog(null, texto);
        //System.out.println(texto);
        
        JOptionPane.showMessageDialog(null, "Se ha terminado de leer los datos del archivo!!.");
        return texto;//El texto se almacena en el JTextArea
    }
    
    public Ventana1Controlador() {
        
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
                    //JOptionPane.showMessageDialog(null, "Comprobando frase -> "+obj.tffrase.getText());
                    obj.text3.setText(sentenceDetect(obj.tffrase.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(Ventana1Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(ae.getSource() == obj.bbuscar) {
            String arc  = abrirArchivo();
            if(arc == null || arc.equals("")) {
                
            }else {
                //obj.tffrase.setText(arc[1]);
              
                Ventana2 v2 = new Ventana2();
                Ventana2Controlador vc2 = new Ventana2Controlador(v2, arc);
            }
        }
    
    }
    
}
