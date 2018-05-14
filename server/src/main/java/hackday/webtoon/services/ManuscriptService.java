package hackday.webtoon.services;

import hackday.webtoon.mappers.ManuscriptMapper;
import hackday.webtoon.exceptions.NotFoundException;
import hackday.webtoon.models.Manuscript;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@Service
public class ManuscriptService {
    private final ManuscriptMapper manuscriptMapper;

    public ManuscriptService(ManuscriptMapper manuscriptMapper) {
        this.manuscriptMapper = manuscriptMapper;
    }

    public LinkedList<Manuscript> findByUserId(long userId, long page, long perPage) {
        return manuscriptMapper.findByUserId(userId, (page - 1) * perPage, perPage);
    }

    public Manuscript findOneById(long manuscriptId) {
        return Optional
                .ofNullable(manuscriptMapper.findOneById(manuscriptId))
                .orElseThrow(() -> new NotFoundException("Not Exist manuscriptId: " + manuscriptId));
    }

}
