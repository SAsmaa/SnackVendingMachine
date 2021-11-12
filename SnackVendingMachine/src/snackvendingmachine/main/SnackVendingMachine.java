/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snackvendingmachine.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import vending.beans.Coin;
import vending.beans.MachineOutput;
import vending.beans.Note;
import vending.beans.PriceBundle;
import vending.beans.SnackItem;
import vending.machine.controller.SnackMachineController;

/**
 *
 * @author Asmaa
 */
public class SnackVendingMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        SnackMachineController snackMachineController = new SnackMachineController();
        
        try{
        SnackItem snackItem = new SnackItem("A4");
        int price = snackMachineController.getPriceOfSelectItem(snackItem);
        System.out.println("Snack Item Selected: " + snackMachineController.getSelectedItem().getItemName() + ", price = " + price + " Cents");
        
        List<Coin> coins = new ArrayList<>();
        List<Note> notes = new ArrayList<Note>();
        
        coins.add(Coin.TENCENT);
        coins.add(Coin.TWENTYCENT);

        notes.add(Note.ONE);
        notes.add(Note.TWENTY);
       PriceBundle bundle = new PriceBundle(coins, notes);
       
       Optional<MachineOutput> output = snackMachineController.insertCoin(bundle);
       snackMachineController.displayInsertedCoinValue(bundle);
       
       if(output.isPresent()){
				MachineOutput itemBucket = output.get();
				if(itemBucket.getItem() !=null){
					System.out.println("Return Snack Name: "+ itemBucket.getItem().getItemName());
					System.out.println("Snack Price: "+ itemBucket.getItem().getItemPrice());
                                        displayInsertedCoinValue(itemBucket.getBundle());
				}
			}
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void displayInsertedCoinValue(PriceBundle price) {
        int insertedNoteValue = 0;
        Optional<Integer> insertedCoinValue = price.getCoins().stream().map(e -> e.getCoinValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
        int insertedValue = insertedCoinValue.get().intValue();
        if (price.getNotes().size() > 0) {
            Optional<Integer> insertedNoteValueOpt = price.getNotes().stream().map(e -> e.getNoteValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
             insertedNoteValue = insertedNoteValueOpt.get().intValue();
            
        }
        
        System.out.println("Returned Coin Value: " + insertedValue + ", Returned Note Value: " + insertedNoteValue);
    }
    
}
