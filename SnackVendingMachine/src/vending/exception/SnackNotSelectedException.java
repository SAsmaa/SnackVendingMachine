/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.exception;

/**
 *
 * @author Asmaa
 */
public class SnackNotSelectedException extends Exception{
    private String message;
	public SnackNotSelectedException(String msg){
		super(msg);
		this.message = msg;
	}
	
	public void getExceptionMessage(){
		System.out.println(this.message);
	}
}
