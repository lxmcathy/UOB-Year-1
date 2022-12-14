package com.bham.pij.assignments.converters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Converter {
    public static enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN};
    private ArrayList<String> inputValues;
    private ArrayList<String> outputValue;
    private Number number;
    private ConvertMode mode;

    public Converter(Converter.ConvertMode cm){
        this.mode = cm;
    }
    public String convert(String value) throws InvalidFormatException{
        switch (mode){
            case BIN2DEC:
                number = new Binary(value);
                Binary binary = (Binary) number;
                return binary.toDecimal();
            case BIN2HEX:
                number = new Binary(value);
                binary = (Binary) number;
                return  binary.toHexadecimal();
            case DEC2BIN:
                number = new Decimal(value);
                Decimal decimal = (Decimal) number;
                return decimal.toBinary();
            case HEX2BIN:
                number = new Hexadecimal(value);
                Hexadecimal hexadecimal = (Hexadecimal) number;
                return hexadecimal.toBinary();
            default:
                return value;
        }
    }
    public void fromFile(String filename){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            inputValues = new ArrayList<>();
            outputValue = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                inputValues.add(str);
                outputValue.add(convert(str));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> getInputValues(){
        return inputValues;
    }
    public ArrayList<String> getOutputValues(){
        return outputValue;
    }
    public static void main(String[] args){
        Converter converter = new Converter(ConvertMode.DEC2BIN);
        System.out.println(converter.convert("00"));
    }
}
