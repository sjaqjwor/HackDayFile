package hackday.webtoon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
public class User {
    @JsonProperty("id")
    Integer userId;
    String name;
    RoleType role;
    String email;
    String password;

    private enum  RoleType {
        AUTHOR, READER
    }
}
