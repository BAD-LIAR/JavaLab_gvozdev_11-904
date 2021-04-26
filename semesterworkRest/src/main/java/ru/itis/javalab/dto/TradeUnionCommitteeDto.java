package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.TradeUnionCommittee;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 22.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeUnionCommitteeDto {
    private Long id;
    private Long authorId;
    private Long newsId;

    public static TradeUnionCommitteeDto from(TradeUnionCommittee tradeUnionCommittee) {
        if (tradeUnionCommittee == null) {
            return null;
        }
        return TradeUnionCommitteeDto.builder()
                .id(tradeUnionCommittee.getId())
                .authorId(tradeUnionCommittee.getAuthorId())
                .newsId(tradeUnionCommittee.getNewsId())
                .build();
    }

    public static List<TradeUnionCommitteeDto> from(List<TradeUnionCommittee> tradeUnionCommittee) {
        return tradeUnionCommittee.stream()
                .map(TradeUnionCommitteeDto::from)
                .collect(Collectors.toList());
    }
}
