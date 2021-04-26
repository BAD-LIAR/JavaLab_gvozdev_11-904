package ru.itis.javalab.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeUnionCommittee {
    private Long id;
    private Long authorId;
    private Long newsId;
}
