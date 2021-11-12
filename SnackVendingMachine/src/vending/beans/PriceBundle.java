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
public class PriceBundle {
    List<Coin> coins;
    List<Note> notes;

    public PriceBundle() {
    }

    public PriceBundle(List<Coin> coins, List<Note> notes) {
        this.coins = coins;
        this.notes = notes;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
    
    
}
