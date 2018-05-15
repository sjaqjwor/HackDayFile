package hackday.webtoon.services;

import hackday.webtoon.mappers.UserMapper;
import hackday.webtoon.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
