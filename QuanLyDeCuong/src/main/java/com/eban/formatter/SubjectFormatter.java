/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.formatter;

import com.eban.pojo.Subject;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


/**
 *
 * @author Admin
 */
public class SubjectFormatter implements Formatter<Subject>{

    @Override
    public String print(Subject sub, Locale locale) {
        return String.valueOf(sub);
    }

    @Override
    public Subject parse(String text, Locale locale) throws ParseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
