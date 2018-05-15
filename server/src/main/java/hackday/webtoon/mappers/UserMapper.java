package hackday.webtoon.mappers;

import hackday.webtoon.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> selectAll();
}
