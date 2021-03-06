package hackday.webtoon.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@Getter
@Setter
@Alias("Manuscript")
public class Manuscript {
    private long manuscriptId;
    private String title;
    private String summary;
    private String turn;
    private LocalDateTime updatedAt;
    private long userId;
    private Set<Image> images;
}
