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
import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lenovo-G40
 */
public class Ventana1Controlador implements ActionListener {
    
    private Ventana1 obj;
    private long tfinal;
    private long tinicio;
    private ForkJoinPool forkJoinPool;
    private TokenizerME tokenizer;
    private POSModel posModel;
    private POSTaggerME posTagger;
    
    
    public String sentenceDetect(String paragraph) {
	
        
	// Tagger tagging the tokens
        String tokens[] = tokenizer.tokenize(paragraph);
	String tags[] = posTagger.tag(tokens);
        
        boolean sol = true;
        
        //Declaración de las expresiones irregulares
        Pattern tagsFormasToBe = Pattern.compile("^is$|^were$|^was$|^be$|^are$|^been$");
            Pattern tagsVerbos = Pattern.compile("^VB$|^VBD$|^VBP$|^VBN$|^VBZ$");
            Pattern tagsNombre = Pattern.compile("^NNS$");
        Pattern tagsPrepos = Pattern.compile("^by$|^on$|^to$");        
        
        // Validar voz pasiva (to be + verbo)
        for (int i=0;i<tags.length;i++) {
            System.out.println(tags[i]);   
        }
        for (int i=0;i<tags.length-3;i++) {
            
            Matcher formasToBe = tagsFormasToBe.matcher(tokens[i]);            
             if (formasToBe.matches()) {
                 Matcher verbos = tagsVerbos.matcher(tags[i+1]);
                 Matcher nombre = tagsNombre.matcher(tags[i-1]);
                 if (verbos.matches()) {
                     Matcher preposiciones = tagsPrepos.matcher(tokens[i+2]);
                     if (preposiciones.matches()) {
                        Matcher verbos1 = tagsVerbos.matcher(tags[i+3]);
                        if (!verbos1.matches()) {
                            sol = false;
                        }
                    }else if(tokens[i+2].equals("and")){
                        Matcher verbos1 = tagsVerbos.matcher(tags[i+3]);
                        if (verbos1.matches()) {
                            sol = false;
                        }
                    }
                }else{
                     Matcher preposiciones = tagsPrepos.matcher(tokens[i+3]);
                     if (preposiciones.matches()) {
                         sol = false;
                    }
                 }
            }                                   
        } 
        
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
               tinicio = System.currentTimeMillis();
               ArrayList<String> arr = new ArrayList();
               while((aux=lee.readLine())!=null)
               {
                   texto += (aux+"-----"+sentenceDetect(aux)+ "\n");//arr.add(aux);
               }
               lee.close();
               //String result = forkJoinPool.invoke(new Auxiliar(this, arr, 0, arr.size()));
               //texto = result;
               
            }    
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null,ex+"" +
                 "\nNo se ha encontrado el archivo",
                       "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
        //JOptionPane.showMessageDialog(null, texto);
        //System.out.println(texto);
        tfinal = System.currentTimeMillis();
        //JOptionPane.showMessageDialog(null, "Se ha terminado de leer los datos del archivo!!.");
        return texto;//El texto se almacena en el JTextArea
    }
    
    public Ventana1Controlador() {
        FileInputStream is1 = null;
        try {
            int nThreads = Runtime.getRuntime().availableProcessors();
            System.out.println("Numero de Procesadores: "+nThreads);
            forkJoinPool = new ForkJoinPool(nThreads);
            //String sentence = "John is 27 years old.";
            is1 = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(is1);
            tokenizer = new TokenizerME(tokenModel);
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream 
            InputStream posModelIn = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-pos-maxent.bin");
            // loading the parts-of-speech model from stream
            posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model 
            posTagger = new POSTaggerME(posModel);
            posModelIn.close();
            is1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana1Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana1Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is1.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana1Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Ventana1Controlador(Ventana1 obj) {
        InputStream is1 = null;
        try {
            this.obj=obj;
            this.obj.baceptar.addActionListener(this);
            this.obj.bbuscar.addActionListener(this);
            // this.obj.tffrase.setText("This is a statement. This is another statement. Now is an abstract word for time, that is always flying.");
            this.obj.tffrase.setText("John is 27 years old.");
            int nThreads = Runtime.getRuntime().availableProcessors();
            System.out.println("Numero de Procesadores: "+nThreads);
            forkJoinPool = new ForkJoinPool(nThreads);
            //String sentence = "John is 27 years old.";
            is1 = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(is1);
            tokenizer = new TokenizerME(tokenModel);
            
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream 
            InputStream posModelIn = new FileInputStream("src/main/java/com/grupo3eis/maventaller2gsi/modelsopennlp/en-pos-maxent.bin");

            // loading the parts-of-speech model from stream
            posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model 
            
            posTagger = new POSTaggerME(posModel);
            
            posModelIn.close();
            is1.close();
        }catch(Exception e) {
            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == obj.baceptar) {
            if(obj.tffrase.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "No ha ingresado una frase. ");
            }else {
                obj.text3.setText(sentenceDetect(obj.tffrase.getText()));
                
            }
        }
        if(ae.getSource() == obj.bbuscar) {
            String arc  = abrirArchivo();
            if(arc == null || arc.equals("")) {
                 JOptionPane.showMessageDialog(null, "No selecciono un archivo");
            }else {
                Ventana2 v2 = new Ventana2();
                Ventana2Controlador vc2 = new Ventana2Controlador(v2, arc, (tfinal-tinicio)/1000);
            }
        }
    
    }
    
}
