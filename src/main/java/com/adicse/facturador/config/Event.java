package com.adicse.facturador.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {
    public String name;
    
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;
}
