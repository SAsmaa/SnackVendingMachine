/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.machine.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import vending.api.Inventory;
import vending.api.InventoryUtil;
import vending.api.SnackVendingMachineInt;
import vending.beans.Coin;
import vending.beans.MachineOutput;
import vending.beans.Note;
import vending.beans.PriceBundle;
import vending.beans.SnackItem;
import vending.exception.NotAChangedException;
import vending.exception.NotEnoughBalanceException;
import vending.exception.SnackItemNotFoundException;
import vending.exception.SnackNotSelectedException;

/**
 *
 * @author Asmaa
 */
public class SnackMachineController implements SnackVendingMachineInt {

    private Inventory<SnackItem, Integer> itemInventory = new Inventory<SnackItem, Integer>();
    private Inventory<Coin, Integer> cashInventory = new Inventory<Coin, Integer>();
    private Inventory<Note, Integer> noteCashInventory = new Inventory<Note, Integer>();
    private SnackItem selectedItem;
    private int totalMachineCashCoin, totalCashNote;

    public SnackMachineController() {
        initialize();
    }

    private void initialize() {
        this.itemInventory.putInventory(new SnackItem("TWIX", "A2", 73), 6);
        this.itemInventory.putInventory(new SnackItem("BOUNTY", "A4", 70), 10);
        this.itemInventory.putInventory(new SnackItem("LAYS", "B1", 35), 2);
        this.itemInventory.putInventory(new SnackItem("MARS", "B4", 30), 2);
        this.itemInventory.putInventory(new SnackItem("M&Ms", "C3", 80), 10);
        this.itemInventory.putInventory(new SnackItem("SNIKERS", "C5", 48), 6);
        this.itemInventory.putInventory(new SnackItem("CHEETOS", "D2", 25), 7);
        this.itemInventory.putInventory(new SnackItem("NUMBER_ONE", "D4", 15), 10);
        this.itemInventory.putInventory(new SnackItem("LAVIVA", "E1", 65), 4);
        this.itemInventory.putInventory(new SnackItem("HOBBIES", "E4", 93), 8);

        this.cashInventory.putInventory(Coin.FIFTYCENT, 10);
        this.cashInventory.putInventory(Coin.TENCENT, 5);
        this.cashInventory.putInventory(Coin.TWENTYCENT, 2);

        this.noteCashInventory.putInventory(Note.FIFTY, 7);
        this.noteCashInventory.putInventory(Note.ONE, 4);
        this.noteCashInventory.putInventory(Note.TWENTY, 3);
        this.setTotalMachineCash();
        System.out.println("Inventory coins : " + this.totalMachineCashCoin);
        System.out.println("Inventory notes : " + this.totalCashNote);
    }

    private void setTotalMachineCash() {
        if (this.cashInventory.getInvetory().size() > 0) {
            Optional<Integer> total = this.cashInventory.getInvetory().entrySet().stream().map(e -> e.getKey().getCoinValue() * e.getValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
            this.totalMachineCashCoin = total.get().intValue();
        }
        if (this.noteCashInventory.getInvetory().size() > 0) {
            Optional<Integer> totalNote = this.noteCashInventory.getInvetory().entrySet().stream().map(e -> e.getKey().getNoteValue() * e.getValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
            this.totalCashNote = totalNote.get().intValue();
        }
        
        
    }

    @Override
    public int getPriceOfSelectItem(SnackItem snack) throws SnackItemNotFoundException {
        List<Map.Entry<SnackItem, Integer>> snackPrice = this.itemInventory.getInvetory().entrySet().stream().filter(e -> e.getKey().getItemCode().equals(snack.getItemCode())).collect(Collectors.toList());
        if (!snackPrice.isEmpty()) {
            SnackItem selectedProduct = snackPrice.get(0).getKey();
            this.selectedItem = selectedProduct;
            return (int) selectedProduct.getItemPrice();
        } else {
            throw new SnackItemNotFoundException("This Snack is Not available");
        }
    }

    @Override
    public void displayInsertedCoinValue(PriceBundle price) {
        int insertedNoteValue = 0;
        Optional<Integer> insertedCoinValue = price.getCoins().stream().map(e -> e.getCoinValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
        int insertedValue = insertedCoinValue.get().intValue();
        if (price.getNotes().size() > 0) {
            Optional<Integer> insertedNoteValueOpt = price.getNotes().stream().map(e -> e.getNoteValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
             insertedNoteValue = insertedNoteValueOpt.get().intValue();
        }
        
        System.out.println("Inserted Coin Value: " + insertedValue + ", Inserted Note Value: " + insertedNoteValue);
    }

    @Override
    public Optional<MachineOutput> insertCoin(PriceBundle priceBundle) throws NotEnoughBalanceException, SnackNotSelectedException {
        MachineOutput bucket = null;
        int insertedNoteValue = 0;
        if (selectedItem != null) {
            Optional<Integer> insertedCoinValue = priceBundle.getCoins().stream().map(Coin::getCoinValue).collect(Collectors.toList()).stream().reduce(Integer::sum);
            int insertedValue = insertedCoinValue.get().intValue();
            if (insertedValue < this.selectedItem.getItemPrice()) {

                if(priceBundle.getNotes() != null && priceBundle.getNotes().size() > 0){
                Optional<Integer> insertedNoteValueOpt = priceBundle.getNotes().stream().map(Note::getNoteValue).collect(Collectors.toList()).stream().reduce(Integer::sum);
                insertedNoteValue = insertedNoteValueOpt.get().intValue();
                }
                int totalCoinAndNote = insertedValue + insertedNoteValue * 10;// convert note to coine ---- 1 note = 10 coin
                if (totalCoinAndNote < this.selectedItem.getItemPrice()) {
                    bucket = new MachineOutput(new SnackItem("Not a fullPaid"), priceBundle);
                } else {
                    try {
                        bucket = this.getItemsAndChange(priceBundle);
                    } catch (NotAChangedException e1) {
                        e1.printStackTrace();
                    }
                }

            } else {
                try {
                    bucket = this.getItemsAndChange(priceBundle);
                } catch (NotAChangedException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            throw new SnackNotSelectedException("Item Not Selected");
        }

        MachineOutput returnBucket = bucket == null ? new MachineOutput(new SnackItem("Item not found"), priceBundle) : bucket;
        Optional<MachineOutput> opt = Optional.ofNullable(returnBucket);
        return opt;
    }

    @Override
    public MachineOutput getItemsAndChange(PriceBundle bundle) throws NotAChangedException {
        this.addToCashInventory(bundle);
        this.setTotalMachineCash();

        System.out.println("Inventory coins after insert coin: " + this.totalMachineCashCoin);
        System.out.println("Inventory notes after insert note: " + this.totalCashNote);

        int changedValue = this.getChanged(bundle, (int) this.selectedItem.getItemPrice());
        this.substractChangedFromInventory(changedValue);

        this.removedItemFromInventory();
        InventoryUtil uitls = new InventoryUtil();
        MachineOutput output = null;
        output = new MachineOutput(this.selectedItem, uitls.convertToCoin(new ArrayList<Coin>(), new ArrayList<Note>(), changedValue));
        if (output.getBundle() != null && output.getBundle().getCoins() != null) {
            this.totalMachineCashCoin = this.totalMachineCashCoin - output.getBundle().getCoins().stream().map(Coin::getCoinValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
        }
        if (output.getBundle() != null && output.getBundle().getNotes() != null && output.getBundle().getNotes().size() > 0) {
            this.totalCashNote = this.totalCashNote - output.getBundle().getNotes().stream().map(Note::getNoteValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
        }

        return output;
    }

    @Override
    public void addToCashInventory(PriceBundle bundle) {
        int numberOfCoin = 0, numberofNote = 0;
        if (bundle!= null &&bundle.getCoins() != null) {
            for (Coin coin : bundle.getCoins()) {
                numberOfCoin = this.cashInventory.getInvetory().get(coin);
                this.cashInventory.getInvetory().put(coin, numberOfCoin + 1);
            }
        }
        if (bundle != null && bundle.getNotes() != null) {
            for (Note note : bundle.getNotes()) {
                numberofNote = this.noteCashInventory.getInvetory().get(note);
                this.noteCashInventory.getInvetory().put(note, numberofNote + 1);
            }
        }

    }

    private int getChanged(PriceBundle bundle, int itemPrice) {
        int coinChange;
        int insertedNoteValue = 0;
        int totalInserted = 0;

        int insertedCoinValue = bundle.getCoins().stream().map(Coin::getCoinValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
        if (bundle.getNotes() != null && bundle.getNotes().size() > 0) {
            insertedNoteValue = bundle.getNotes().stream().map(Note::getNoteValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
        }
        totalInserted = insertedCoinValue + insertedNoteValue * 10;

        if (totalInserted > itemPrice) {
            coinChange = totalInserted - itemPrice;
        } else {
            coinChange = itemPrice - totalInserted;
        }

        return coinChange;
    }

    private void substractChangedFromInventory(PriceBundle bundle) {
        if (bundle.getCoins() != null) {
            int changeValue = bundle.getCoins().stream().map(Coin::getCoinValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
            substractChangedFromInventory(changeValue);
        }
        if (bundle.getNotes() != null) {
            int changeValue = bundle.getNotes().stream().map(Note::getNoteValue).collect(Collectors.toList()).stream().reduce(Integer::sum).get().intValue();
            substractNoteChangedFromInventory(changeValue);
        }
    }

    private int putCoinAndDecrement(Coin coin, int changedValue) {
        int reminder = changedValue / coin.getCoinValue();
        int numberOfCoin = this.cashInventory.getInvetory().get(coin);
        if (numberOfCoin > reminder) {
            numberOfCoin = numberOfCoin - reminder;
        }
        this.cashInventory.getInvetory().put(coin, numberOfCoin);
        int test = changedValue - (reminder * coin.getCoinValue());
        return test;
    }

    private int putCoinAndDecrement(Note note, int changedValue) {
        int reminder = changedValue / note.getNoteValue();
        int numberOfCoin = this.cashInventory.getInvetory().get(note);
        if (numberOfCoin > reminder) {
            numberOfCoin = numberOfCoin - reminder;
        }
        this.noteCashInventory.getInvetory().put(note, numberOfCoin);
        int test = changedValue - (reminder * note.getNoteValue());
        return test;
    }

    private void substractChangedFromInventory(int changedValue) {

        if (changedValue >= Coin.FIFTYCENT.getCoinValue()) {
            int test = this.putCoinAndDecrement(Coin.FIFTYCENT, changedValue);
            if (test != 0) {
                substractChangedFromInventory(test);
            }

        } else if (changedValue >= Coin.TWENTYCENT.getCoinValue()) {
            int test = this.putCoinAndDecrement(Coin.TWENTYCENT, changedValue);
            if (test != 0) {
                substractChangedFromInventory(test);
            }

        } else if (changedValue >= Coin.TENCENT.getCoinValue()) {
            int test = this.putCoinAndDecrement(Coin.TENCENT, changedValue);
            if (test != 0) {
                substractChangedFromInventory(test);
            }

        } else if (changedValue >= Coin.ONE.getCoinValue()) {
            int test = this.putCoinAndDecrement(Coin.ONE, changedValue);
            if (test != 0) {
                substractChangedFromInventory(test);
            }

        }

    }

    private void substractNoteChangedFromInventory(int changedValue) {

        if (changedValue >= Note.FIFTY.getNoteValue()) {
            int test = this.putCoinAndDecrement(Note.FIFTY, changedValue);
            if (test != 0) {
                substractNoteChangedFromInventory(test);
            }

        } else if (changedValue >= Note.TWENTY.getNoteValue()) {
            int test = this.putCoinAndDecrement(Note.TWENTY, changedValue);
            if (test != 0) {
                substractNoteChangedFromInventory(test);
            }

        } else if (changedValue >= Note.ONE.getNoteValue()) {
            int test = this.putCoinAndDecrement(Note.ONE, changedValue);
            if (test != 0) {
                substractNoteChangedFromInventory(test);
            }

        }

    }

    private void removedItemFromInventory() {
        int itemCount = this.itemInventory.getInvetory().get(selectedItem);
        this.itemInventory.getInvetory().put(selectedItem, itemCount - 1);
    }

    public SnackItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(SnackItem selectedItem) {
        this.selectedItem = selectedItem;
    }

}
