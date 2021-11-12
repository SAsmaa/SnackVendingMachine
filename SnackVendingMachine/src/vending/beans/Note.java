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
public enum Note {
    ONE(1),TWENTY(20),FIFTY(50);
	private int noteValue;
	Note(int i) {
		this.noteValue = i;
	}
	public int getNoteValue(){
		return this.noteValue;
	}
	
}
