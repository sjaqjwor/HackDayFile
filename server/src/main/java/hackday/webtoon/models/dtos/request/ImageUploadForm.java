package hackday.webtoon.models.dtos.request;

import lombok.Data;

@Data
public class ImageUploadForm {
    private Long userId;
    private String title;
    private String oneWorld;
    private Long round;
}
