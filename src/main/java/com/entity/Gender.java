package com.entity;

public enum Gender {
	MEN, WOMEN, BOYS, GIRLS;
	
	public static String getGender(Gender gender){
        switch (gender){
            case MEN:
                return "Men";
            case WOMEN:
                return "Women";
            case BOYS:
                return "Boys";
            case GIRLS:
                return "Girls";
            default:
                return "";
        }
    }

}
