package com.bham.pij.assignments.converters;

public class Decimal extends Number{
    public Decimal(String value){
        if (value.length()<=3){
            for (int i = 0;i<value.length();i++){
                if (value.charAt(i)<'0'||value.charAt(i)>'9'){
                    throw new InvalidFormatException();
                }
            }
            if (Tools.String2Int(value)<0||Tools.String2Int(value)>255){
                System.out.println(Tools.String2Int(value));
                throw new InvalidFormatException();
            }
        }else {
            throw new InvalidFormatException();
        }
        this.number = value;
    }
    public String toBinary(){
        int length = 8;
        String result = "";
        int remainder = 0;
        int quotient = Tools.String2Int(number);
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
}
