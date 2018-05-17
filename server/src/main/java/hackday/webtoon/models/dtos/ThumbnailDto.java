package hackday.webtoon.models.dtos;

import lombok.Builder;

@Builder
public class ThumbnailDto {
    private Long userId;
    private Long manuscriptId;
    private String origin_name;
    private String path;
    private String mediaType;
}
