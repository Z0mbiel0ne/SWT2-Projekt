/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhdo.swt2.udrive.model;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
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
public class SQLServiceTest {
    
    public SQLServiceTest() {
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
     * Test of addKunde method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testAddKunde() {
        System.out.println("addKunde");
        String vorname = "Hans";
        String nachname = "Peter";
        int plz = 45327;
        String stadt = "Essen";
        String strasse = "Gaststraße";
        int hausnummer = 34;
        int kontonummer = 1111111;
        int blz = 4526789;
        int iban = 8323223;
        int bic = 343545;
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        instance.addKunde(vorname, nachname, plz, stadt, strasse, hausnummer, kontonummer, blz, iban, bic);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteKunde method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testDeleteKunde() throws Exception {
        System.out.println("deleteKunde");
        int id = 18;
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        instance.deleteKunde(id);
    }

    /**
     * Test of checkPasswort method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testCheckPasswort() {
        System.out.println("checkPasswort");
        String user = "test";
        String pass = "test";
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        boolean expResult = true;
        boolean result = instance.checkPasswort(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of checkPasswort method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testCheckPasswort2() {
        System.out.println("checkPasswort");
        String user = "Peter";
        String pass = "Lutz";
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        boolean expResult = false;
        boolean result = instance.checkPasswort(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteFahrstunde method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testDeleteFahrstunde() {
        System.out.println("deleteFahrstunde");
        int id = 0;
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        instance.deleteFahrstunde(id);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateCredit method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testUpdateCredit() {
        System.out.println("updateCredit");
        int id = 20;
        int value = 60;
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        instance.updateCredit(id, value);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of get method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String sql = "";
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        ResultSet expResult = null;
        ResultSet result = instance.get(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of set method, of class DerRestDerInKeineKategoriePasstService.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        String sql = "";
        DerRestDerInKeineKategoriePasstService instance = new DerRestDerInKeineKategoriePasstService();
        instance.set(sql);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
