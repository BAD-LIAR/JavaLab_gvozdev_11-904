package ru.itis.javalab.models;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class Comment {
    private Long id;
    private Long userId;
    private Long newsId;
    private String text;
    private Date date;
    private Time time;
}
