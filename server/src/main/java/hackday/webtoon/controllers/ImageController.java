package hackday.webtoon.controllers;

import hackday.webtoon.models.dtos.request.ImageUploadForm;
import hackday.webtoon.models.dtos.response.DefaultResponse;
import hackday.webtoon.models.dtos.response.StatusCode;
import hackday.webtoon.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController{

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> fileUpload(
            @RequestPart(name = "thumb") MultipartFile thumbnailFile,
            @RequestPart(name = "files") MultipartFile[] multipartFile,
            @RequestPart(name ="manuscript") ImageUploadForm imageUploadForm) throws Exception{
        imageService.fileUpload(thumbnailFile,multipartFile,imageUploadForm);
        DefaultResponse dr = DefaultResponse.builder().result("SUCCESS")
                .statusCode(StatusCode.OK.toString())
                .msg("SUCCESS")
                .build();
        return new ResponseEntity<>(dr,HttpStatus.OK);
    }

}
