package com.victory.hr.attendance.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by ajkx
 * Date: 2017/5/13.
 * Time:11:05
 */
public enum Reos {

    VALUE1("A"),VALUE2("B");

    private String text;

    Reos(String text){this.text = text;}

    public String getText(){return this.text;}

    @JsonCreator
    public static Reos fromText(String text){
        for(Reos r : Reos.values()){
            if(r.getText().equals(text)){
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

//    @Override
//    public String toString() {
//        return text;
//    }
}