/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author lenovo-G40
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
/**
 * @author tutorialkart
 */
public class SentenceDetectExample {

	public static void main(String[] args) {
		try {
			new SentenceDetectExample().sentenceDetect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sentenceDetect() throws InvalidFormatException, IOException {
		//String paragraph = "Apache openNLP supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning.";
                String paragraph = "This is a statement. This is another statement. Now is an abstract word for time, that is always flying.";
 
		// refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
		InputStream is = new FileInputStream("D:\\Especializacion en Ingenieria de Software\\Gestion de Software I\\taller 2\\Taller2-GSI\\Taller2GSI\\models-nlp\\en-sent.bin");
		SentenceModel model = new SentenceModel(is);

		// load the model
		SentenceDetectorME sdetector = new SentenceDetectorME(model);

		// detect sentences in the paragraph
		String sentences[] = sdetector.sentDetect(paragraph);

                // print the sentences detected, to console
                for (String sentence : sentences) {
                    System.out.println(sentence);
                }
		is.close();
	}
}