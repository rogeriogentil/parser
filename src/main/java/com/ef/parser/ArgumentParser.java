/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ef.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Rogerio J. Gentil
 */
public class ArgumentParser {

    public static void checkDurationArg(String durationArg) {
        DurationArg.valueOf(durationArg.toUpperCase());
    }

    public static void checkThresholdArg(String thresholdArg) {
        Integer.parseInt(thresholdArg);
    }

    public static void checkStartDateArg(String startDateTxt) throws java.text.ParseException {
        final String dateFormat = "yyyy-MM-dd.HH:mm:ss";
        DateFormat format = new SimpleDateFormat(dateFormat);
        format.parse(startDateTxt);
    }
    
}
