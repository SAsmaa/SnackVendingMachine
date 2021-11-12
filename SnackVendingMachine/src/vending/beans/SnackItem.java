/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.beans;

/**
 *
 * @author Asmaa
 */
public class SnackItem {

    private String itemName;
    private String itemCode;
    private long itemPrice;

    public SnackItem() {
    }

    public SnackItem(String itemName, String itemCode, long itemPrice) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;
    }

    public SnackItem(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

}
