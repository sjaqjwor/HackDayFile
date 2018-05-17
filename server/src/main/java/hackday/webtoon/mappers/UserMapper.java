package hackday.webtoon.mappers;

import hackday.webtoon.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> selectAll();
}
