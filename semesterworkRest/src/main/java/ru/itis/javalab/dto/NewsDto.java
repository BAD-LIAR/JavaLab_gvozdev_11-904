package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.News;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {
    private Long id;
    private String tittle;
    private String info;
    private Date date;
    private Time time;

    public static NewsDto from(News news) {
        if (news == null) {
            return null;
        }
        return NewsDto.builder()
                .id(news.getId())
                .tittle(news.getTittle())
                .info(news.getInfo())
                .date(news.getDate())
                .time(news.getTime())
                .build();
    }

    public static List<NewsDto> from(List<News> news) {
        return news.stream()
                .map(NewsDto::from)
                .collect(Collectors.toList());
    }
}
