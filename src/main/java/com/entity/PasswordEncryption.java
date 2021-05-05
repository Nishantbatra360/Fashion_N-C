package com.entity;

public class PasswordEncryption {
	
	public static String encrypt(String password) {
		String encrypted="";
		for(int i=0;i<password.length();i++) {
			int ascii=password.charAt(i);
				encrypted+=ascii;
		}
		return encrypted;
	}
}
