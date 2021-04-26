package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Comment;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private Long userId;
    private Long newsId;
    private String text;
    private Date date;
    private Time time;

    public static CommentDto from(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .newsId(comment.getNewsId())
                .text(comment.getText())
                .date(comment.getDate())
                .time(comment.getTime())
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}
