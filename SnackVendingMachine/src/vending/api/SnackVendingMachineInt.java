/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.api;

import vending.beans.MachineOutput;
import vending.beans.PriceBundle;
import vending.beans.SnackItem;
import vending.exception.NotAChangedException;
import vending.exception.NotEnoughBalanceException;
import vending.exception.SnackItemNotFoundException;
import java.util.Optional;
import vending.exception.SnackNotSelectedException;

/**
 *
 * @author Asmaa
 */
public interface SnackVendingMachineInt {
    public int getPriceOfSelectItem(SnackItem snack) throws SnackItemNotFoundException;
    public void displayInsertedCoinValue(PriceBundle price);
    public Optional<MachineOutput> insertCoin(PriceBundle price) throws NotEnoughBalanceException, SnackNotSelectedException ;
    public MachineOutput getItemsAndChange(PriceBundle bundle) throws NotAChangedException;
    public void addToCashInventory(PriceBundle bundle);
}
