package com.bham.pij.assignments.converters;

public class Hexadecimal extends Number{
    public Hexadecimal(String value){
        if (value.length()==2){
            for (int i = 0;i<2;i++){
                if (value.charAt(i)!='0'&&value.charAt(i)!='1'&&value.charAt(i)!='2'&&value.charAt(i)!='3'
                        &&value.charAt(i)!='4'&&value.charAt(i)!='5'&&value.charAt(i)!='6'&&value.charAt(i)!='7'&&value.charAt(i)!='8'
                        &&value.charAt(i)!='9'&&value.charAt(i)!='A'&&value.charAt(i)!='B' &&value.charAt(i)!='C'
                        &&value.charAt(i)!='D'&&value.charAt(i)!='E'&&value.charAt(i)!='F'){
                    throw new InvalidFormatException();
                }
            }
        }else {
            throw new InvalidFormatException();
        }
        this.number = value;
    }
    public String toBinary(){
        return  dec2bin(hex2dec(number.charAt(0)+""))+dec2bin(hex2dec(number.charAt(1)+""));
    }

    private String dec2bin(int value){
        int length = 4;
        String result = "";
        int remainder = 0;
        int quotient = value;
        for (int i = 0; i<length;i++) {
            if (quotient != 0) {
                remainder = quotient % 2;
                quotient = quotient / 2;
                result = remainder + result;
            } else {
                result = "0" + result;
            }
        }
        return result;
    }
    private int hex2dec(String hex){
        switch (hex){
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
            default:
                return Tools.String2Int(hex);
        }
    }
}
