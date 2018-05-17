package hackday.webtoon.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Created by koseungbin on 2018. 5. 17.
 */

@Getter
@Setter
public class ManuscriptDto {
    @JsonProperty("id")
    private long manuscriptId;
    private String title;
    private String summary;
    private String turn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Map<Long, String> imagePath;
}
