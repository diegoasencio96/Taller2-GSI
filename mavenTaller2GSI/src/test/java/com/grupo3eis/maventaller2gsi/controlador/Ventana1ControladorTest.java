/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo3eis.maventaller2gsi.controlador;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lenovo-G40
 */
public class Ventana1ControladorTest {
    
    public Ventana1ControladorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sentenceDetect method, of class Ventana1Controlador.
     */
    @Test
    public void testSentenceDetect() throws Exception {
        System.out.println("sentenceDetect");
        String paragraph = "Harry ate six shrimp at dinner.";
        Ventana1Controlador instance = new Ventana1Controlador();
        String expResult = "activo";
        String result = instance.sentenceDetect(paragraph);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)) {
            fail("The test case is a prototype.");
        }else {
            System.out.println("Prueba acertada");
        }
    }

    
}
