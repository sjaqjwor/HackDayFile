package hackday.webtoon.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("CartoonInfoAtTurn")
public class CartoonInfoAtTurn {
    @JsonProperty("id")
    long manuscriptId;
    @JsonIgnore
    long userId;
    String title;
}
