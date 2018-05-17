package hackday.webtoon.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public class ManuscriptSaveDto {

    @Getter
    @Setter
    private Long id;
    private Long userId;
    private String title;
    private String summary;
    private Long turn;
}
