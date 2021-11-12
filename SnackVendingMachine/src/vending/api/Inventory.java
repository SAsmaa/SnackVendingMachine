/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.api;

import vending.beans.SnackItem;
import java.util.HashMap;

/**
 *
 * @author Asmaa
 */
public class Inventory<T,T2> {
    private HashMap<T,T2> inentory = new HashMap<T,T2>();
	
	public boolean hashItem(SnackItem snack) {
		return true;
	}

	public HashMap<T,T2> getInvetory() {
		return inentory;
	}
	
	public void putInventory(T t, T2 t2){
		this.inentory.put(t, t2);
	}
}
