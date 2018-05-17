package hackday.webtoon.mappers;

import hackday.webtoon.models.Manuscript;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@Mapper
@Repository
public interface ManuscriptMapper {
    LinkedList<Manuscript> findByUserId(@Param("userId") long userId, @Param("offset") long offset, @Param("count") long count);

    Manuscript findOneById(@Param("manuscriptId") long manuscriptId);
}
