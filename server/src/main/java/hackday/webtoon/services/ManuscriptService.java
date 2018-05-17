package hackday.webtoon.services;

import hackday.webtoon.Mappers.ManuscriptMapper;
import hackday.webtoon.exceptions.NotFoundException;
import hackday.webtoon.models.Image;
import hackday.webtoon.models.Manuscript;
import hackday.webtoon.models.dto.ManuscriptDto;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@Service
public class ManuscriptService {
    private final ManuscriptMapper manuscriptMapper;

    public ManuscriptService(ManuscriptMapper manuscriptMapper) {
        this.manuscriptMapper = manuscriptMapper;
    }

    public LinkedList<ManuscriptDto> findByUserId(long userId, long page, long perPage) {
        LinkedList<Manuscript> manuscripts = manuscriptMapper.findByUserId(userId, (page - 1) * perPage, perPage);
        LinkedList<ManuscriptDto> manuscriptDtos = new LinkedList<>();
        manuscripts.forEach(it -> manuscriptDtos.add(toManuscriptDto(it)));

        return manuscriptDtos;
    }

    public ManuscriptDto findOneById(long manuscriptId) {
        Manuscript manuscript = manuscriptMapper.findOneById(manuscriptId);
        if (manuscript == null) {
            throw new NotFoundException("Not Exist manuscriptId: " + manuscriptId);
        }

        ManuscriptDto manuscriptDto = toManuscriptDto(manuscript);
        manuscriptDto.setImagePath(toImagePath(manuscript.getImages()));
        return manuscriptDto;
    }

    /*
    * @param Manuscript
    * @return ManuscriptDto
    *
    * */
    private ManuscriptDto toManuscriptDto(Manuscript manuscript) {
        ManuscriptDto manuscriptDto = new ManuscriptDto();
        manuscriptDto.setManuscriptId(manuscript.getManuscriptId());
        manuscriptDto.setTitle(manuscript.getTitle());
        manuscriptDto.setSummary(manuscript.getSummary());
        manuscriptDto.setTurn(manuscript.getTurn());
        manuscriptDto.setUpdatedAt(manuscript.getUpdatedAt());
        return manuscriptDto;
    }


    /*
    * @param Image Set
    * @return Map: sequence of index, value of image path
    *
    * */
    private Map<Long, String> toImagePath(Set<Image> images) {
        return images.stream().collect(Collectors.toMap(Image::getSequence, Image::getPath));
    }

}
