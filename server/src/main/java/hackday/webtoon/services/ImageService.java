package hackday.webtoon.services;

import hackday.webtoon.exceptions.FileException;
import hackday.webtoon.exceptions.HashException;
import hackday.webtoon.mappers.ImageMapper;
import hackday.webtoon.mappers.ManuscriptMapper;
import hackday.webtoon.mappers.ThumbnailMapper;
import hackday.webtoon.models.dtos.ImageDto;
import hackday.webtoon.models.dtos.ManuscriptSaveDto;
import hackday.webtoon.models.dtos.ThumbnailDto;
import hackday.webtoon.models.dtos.request.ImageUploadForm;
import hackday.webtoon.models.dtos.response.StatusCode;
import hackday.webtoon.utils.FileUtils;
import hackday.webtoon.utils.ImageHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class ImageService{

    @Value("${image.dir}")
    private String dir;

    @Value("${image.url}")
    private String url;

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ThumbnailMapper thumbnailMapper;

    @Transactional(rollbackFor = Exception.class)
    public void fileUpload(MultipartFile thumbnail,MultipartFile[] multipartFile, ImageUploadForm imageUploadForm)
            {
        File file=null;
        File thumbFile=null;
        FileUtils fu = new FileUtils();
        fu.fileSizeCheck(thumbnail,multipartFile);
        try {
            String hash = new ImageHash().createHash(imageUploadForm.getTitle());

            ManuscriptSaveDto manuscriptSaveDto = ManuscriptSaveDto.builder()
                    .userId(imageUploadForm.getUserId())
                    .turn(imageUploadForm.getRound())
                    .summary(imageUploadForm.getOneWorld())
                    .title(imageUploadForm.getTitle())
                    .build();


            manuscriptMapper.addManuScript(manuscriptSaveDto);

            file = new File(dir + fu.getPath(imageUploadForm.getUserId(), imageUploadForm.getRound()));
            thumbFile = new File(dir + fu.getPath(imageUploadForm.getUserId(), imageUploadForm.getRound()) + "thumb/");

            if (!thumbFile.exists()) {
                thumbFile.mkdirs();
            }

            fu.check(thumbnail);
            InputStream inputStream = thumbnail.getInputStream();
            String extention = fu.parseExtention(thumbnail.getOriginalFilename());
            String filePath = fu.getFilePath(imageUploadForm, hash, "thumb") + extention;
            Path path = Paths.get(dir + filePath);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            thumbnailMapper.addThumbnail(ThumbnailDto.builder()
                    .manuscriptId(manuscriptSaveDto.getId())
                    .mediaType(extention)
                    .origin_name(thumbnail.getOriginalFilename())
                    .path(filePath)
                    .userId(imageUploadForm.getUserId())
                    .build());


            if (!file.exists()) {
                file.mkdirs();
            }

            for (MultipartFile m : multipartFile) {
                fu.check(m);
            }

            int number = 1;
            for (MultipartFile m : multipartFile) {
                inputStream = m.getInputStream();
                extention = fu.parseExtention(m.getOriginalFilename());
                filePath = fu.getFilePath(imageUploadForm, hash) + number + extention;
                path = Paths.get(dir + filePath);
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                imageMapper.addImage(ImageDto.builder()
                        .userId(imageUploadForm.getUserId())
                        .manuscriptId(manuscriptSaveDto.getId())
                        .path(filePath).name(m.getOriginalFilename())
                        .mediaType(extention)
                        .sequence(number)
                        .build());
                number++;
            }
        }catch (IOException e){
            fu.fileDelete(file);
            fu.fileDelete(thumbFile);
            throw new FileException(StatusCode.FILEWRITE);
        }catch (InvalidKeySpecException e){
            throw new HashException();
        }catch (NoSuchAlgorithmException e){
            throw new HashException();
        }
    }

}
