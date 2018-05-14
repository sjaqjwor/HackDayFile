package hackday.webtoon.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@Getter
@Setter
@ToString
@Alias("Manuscript")
public class Manuscript {
    @JsonProperty("id")
    long manuscriptId;
    String title;
    String summary;
    String turn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt;
    @JsonIgnore
    long userId;
}
