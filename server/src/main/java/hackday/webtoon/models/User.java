package hackday.webtoon.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
public class User {
    private Integer userId;
    private String name;
    private RoleType role;
    private String email;
    private String password;

    private enum  RoleType {
        AUTHOR, READER
    }
}
