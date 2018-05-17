package hackday.webtoon.controllers;

import hackday.webtoon.models.dto.ManuscriptDto;
import hackday.webtoon.services.ManuscriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

/**
 * Created by koseungbin on 2018. 5. 16.
 */

@RestController
public class ManuscriptController {
    private final ManuscriptService manuscriptService;

    public ManuscriptController(ManuscriptService manuscriptService) {
        this.manuscriptService = manuscriptService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/manuscripts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LinkedList<ManuscriptDto> find(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "per_page", defaultValue = "10") int perPage) {
        long uid = 1; // 임시값
        return manuscriptService.findByUserId(1, page, perPage);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/manuscripts/{manuscriptId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ManuscriptDto findOne(@PathVariable("manuscriptId") long manuscriptId) {
        return manuscriptService.findOneById(manuscriptId);
    }

}
