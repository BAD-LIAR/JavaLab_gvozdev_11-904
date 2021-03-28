package ru.itis.javalab.models;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class News {
    private Long id;
    private String tittle;
    private String info;
    private Date date;
    private Time time;
}
