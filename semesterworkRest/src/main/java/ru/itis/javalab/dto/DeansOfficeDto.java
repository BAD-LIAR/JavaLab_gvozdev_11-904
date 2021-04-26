package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.DeansOffice;

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
public class DeansOfficeDto {
    private Long id;
    private Long authorId;
    private Long newsId;

    public static DeansOfficeDto from(DeansOffice deansOffice) {
        if (deansOffice == null) {
            return null;
        }
        return DeansOfficeDto.builder()
                .id(deansOffice.getId())
                .authorId(deansOffice.getAuthorId())
                .newsId(deansOffice.getNewsId())
                .build();
    }

    public static List<DeansOfficeDto> from(List<DeansOffice> deansOffices) {
        return deansOffices.stream()
                .map(DeansOfficeDto::from)
                .collect(Collectors.toList());
    }
}
