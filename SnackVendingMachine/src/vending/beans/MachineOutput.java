/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.beans;

import java.util.List;

/**
 *
 * @author Asmaa
 */
public class MachineOutput {

    private SnackItem item;
    private PriceBundle bundle;

    public MachineOutput() {
    }

    public MachineOutput(SnackItem item, PriceBundle bundle) {
        this.item = item;
        this.bundle = bundle;
    }
    
    

    public SnackItem getItem() {
        return item;
    }

    public void setItem(SnackItem item) {
        this.item = item;
    }

    public PriceBundle getBundle() {
        return bundle;
    }

    public void setBundle(PriceBundle bundle) {
        this.bundle = bundle;
    }

   
    
    
}
