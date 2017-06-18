/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import fhdo.swt2.udrive.model.dto.Fahrlehrer;
import fhdo.swt2.udrive.model.dto.Fahrschueler;
import fhdo.swt2.udrive.model.dto.Fahrstunde;
import fhdo.swt2.udrive.model.dto.Treffpunkt;
import java.util.ArrayList;
import java.util.Date;
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
public class FahrstundeServiceTest {
    
    public FahrstundeServiceTest() {
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
     * Test of getFahrstundeTable method, of class FahrstundeService.
     */
    @Test
    public void testGetFahrstundeTable() {
        System.out.println("getFahrstundeTable");
        int kundenID = 0;
        FahrstundeService instance = new FahrstundeService();
        ArrayList<Fahrstunde> expResult = null;
        ArrayList<Fahrstunde> result = instance.getFahrstundeTable(kundenID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteFahrstunde method, of class FahrstundeService.
     */
    @Test
    public void testDeleteFahrstunde() {
        System.out.println("deleteFahrstunde");
        Fahrstunde fahrstunde = null;
        FahrstundeService instance = new FahrstundeService();
        instance.deleteFahrstunde(fahrstunde);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of insertFahrstunde method, of class FahrstundeService.
     */
    @Test
    public void testInsertFahrstunde() {
        System.out.println("insertFahrstunde");
        Date datum = null;
        Treffpunkt treffpunkt = null;
        Fahrlehrer fahrlehrer = null;
        Fahrschueler kunde = null;
        int rechnungID = 0;
        FahrstundeService instance = new FahrstundeService();
        instance.insertFahrstunde(datum, treffpunkt, fahrlehrer, kunde, rechnungID);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
