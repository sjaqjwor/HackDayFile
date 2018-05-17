package hackday.webtoon.mappers;

import hackday.webtoon.models.dtos.ThumbnailDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ThumbnailMapper {
    void addThumbnail(ThumbnailDto thumbnailDto);
}
