/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.machine.controller;

import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vending.beans.MachineOutput;
import vending.beans.PriceBundle;
import vending.beans.SnackItem;

/**
 *
 * @author Asmaa
 */
public class SnackMachineControllerTest {
    
    public SnackMachineControllerTest() {
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
     * Test of getPriceOfSelectItem method, of class SnackMachineController.
     */
    @Test
    public void testGetPriceOfSelectItem() throws Exception {
        System.out.println("getPriceOfSelectItem");
        SnackItem snack = null;
        SnackMachineController instance = new SnackMachineController();
        int expResult = 0;
        int result = instance.getPriceOfSelectItem(snack);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayInsertedCoinValue method, of class SnackMachineController.
     */
    @Test
    public void testDisplayInsertedCoinValue() {
        System.out.println("displayInsertedCoinValue");
        PriceBundle price = null;
        SnackMachineController instance = new SnackMachineController();
        instance.displayInsertedCoinValue(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCoin method, of class SnackMachineController.
     */
    @Test
    public void testInsertCoin() throws Exception {
        System.out.println("insertCoin");
        PriceBundle priceBundle = null;
        SnackMachineController instance = new SnackMachineController();
        Optional<MachineOutput> expResult = null;
        Optional<MachineOutput> result = instance.insertCoin(priceBundle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsAndChange method, of class SnackMachineController.
     */
    @Test
    public void testGetItemsAndChange() throws Exception {
        System.out.println("getItemsAndChange");
        PriceBundle bundle = null;
        SnackMachineController instance = new SnackMachineController();
        MachineOutput expResult = null;
        MachineOutput result = instance.getItemsAndChange(bundle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToCashInventory method, of class SnackMachineController.
     */
    @Test
    public void testAddToCashInventory() {
        System.out.println("addToCashInventory");
        PriceBundle bundle = null;
        SnackMachineController instance = new SnackMachineController();
        instance.addToCashInventory(bundle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedItem method, of class SnackMachineController.
     */
    @Test
    public void testGetSelectedItem() {
        System.out.println("getSelectedItem");
        SnackMachineController instance = new SnackMachineController();
        SnackItem expResult = null;
        SnackItem result = instance.getSelectedItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedItem method, of class SnackMachineController.
     */
    @Test
    public void testSetSelectedItem() {
        System.out.println("setSelectedItem");
        SnackItem selectedItem = null;
        SnackMachineController instance = new SnackMachineController();
        instance.setSelectedItem(selectedItem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
