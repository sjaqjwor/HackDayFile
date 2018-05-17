package hackday.webtoon.mappers;

import hackday.webtoon.models.dtos.ImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    void addImage(ImageDto imageDto);
}
