package com.bham.pij.assignments.converters;

public class Binary extends Number {
    public Binary(String value){
        if (value.length()==8){
            for (int i = 0;i<value.length();i++){
                if (value.charAt(i)!='1'&&value.charAt(i)!='0'){
                    throw new InvalidFormatException();
                }
            }
        }else {
            throw new InvalidFormatException();
        }
        this.number = value;
    }

    public String toDecimal(){
        int num = 0;
        int count = 0;
        for (int i = number.length()-1;i>=0;i--){
            if (number.charAt(i)=='1'){
                int pow = 1;
                for (int m = 0;m<count;m++){
                    pow = pow * 2;
                }
                num = num + pow;
            }
            count++;
        }
        return num+"";
    }
    public String toHexadecimal(){
        String preHex = number.substring(0,4);
        preHex = new Binary("0000"+preHex).toDecimal();
        String postHex = number.substring(4);
        postHex = new Binary("0000"+postHex).toDecimal();
        return  dec2hex(preHex)+dec2hex(postHex);
    }

    private String dec2hex(String value){
        switch (value){
            case "10":
                return "A";
            case "11":
                return "B";
            case "12":
                return "C";
            case "13":
                return "D";
            case "14":
                return "E";
            case "15":
                return "F";
            default:
                return value;
        }
    }
}
