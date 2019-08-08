package com.hlhealth.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse("2017-12-15 19:15:01", formatter);
        String date = dateTime.format(formatter);
        System.out.println(date);
        
        dateTime = dateTime.plusDays(20);
        System.out.println(dateTime);
    }
}
