package hackday.webtoon.models.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
public class ImageDto {
    private Long userId;
    private Long manuscriptId;
    private Integer sequence;
    private String path;
    private String name;
    private String mediaType;
}
