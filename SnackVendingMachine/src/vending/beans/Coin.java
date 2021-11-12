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
public enum Coin {
  ONE(1),TENCENT(10),TWENTYCENT(20),FIFTYCENT(50);
	private int coinValue;
	Coin(int i) {
		this.coinValue = i;
	}
	public int getCoinValue(){
		return this.coinValue;
	}
	
}
