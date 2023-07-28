package com.example.demo.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {

  //  private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public String getDate(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }


}
