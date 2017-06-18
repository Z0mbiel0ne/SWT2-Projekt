/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.model.dto.Fahrschueler;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcel
 */
public class KundenServiceTest {
    
    public KundenServiceTest() {
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
     * Test of addKunde method, of class KundenService.
     */
    @Test
    public void testAddKunde() {
        System.out.println("addKunde");
        Fahrschueler kunde = null;
        KundenService instance = new KundenService();
        instance.addKunde(kunde);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteKunde method, of class KundenService.
     */
    @Test
    public void testDeleteKunde() throws Exception {
        System.out.println("deleteKunde");
        Fahrschueler kunde = null;
        KundenService instance = new KundenService();
        instance.deleteKunde(kunde);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getKundenTable method, of class KundenService.
     */
    @Test
    public void testGetKundenTable() {
        System.out.println("getKundenTable");
        KundenService instance = new KundenService();
        ArrayList<Fahrschueler> expResult = null;
        ArrayList<Fahrschueler> result = instance.getKundenTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getKunden method, of class KundenService.
     */
    @Test
    public void testGetKunden() {
        System.out.println("getKunden");
        KundenService instance = new KundenService();
        ArrayList<Fahrschueler> expResult = null;
        ArrayList<Fahrschueler> result = instance.getKunden();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateCredit method, of class KundenService.
     */
    @Test
    public void testUpdateCredit() {
        System.out.println("updateCredit");
        Fahrschueler kunde = null;
        KundenService instance = new KundenService();
        instance.updateCredit(kunde);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
