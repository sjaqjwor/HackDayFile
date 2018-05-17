package hackday.webtoon.utils;

import hackday.webtoon.exceptions.FileException;
import hackday.webtoon.models.dtos.request.ImageUploadForm;
import hackday.webtoon.models.dtos.response.StatusCode;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

public class FileUtils {

    public void check(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) throw new FileException(StatusCode.FILEEMPTY);

        Tika t = new Tika();

        String mimeType = t.detect(multipartFile.getInputStream());

        if (!mimeType.split("/")[1].equals("jpeg")) throw new FileException(StatusCode.EXTENTION);
    }

    public String parseExtention(String file) {
        return file.substring(file.lastIndexOf("."));
    }

    public String getPath(Long uid, Long round) {
        return uid + "/" + round + "/";
    }

    public String getFilePath(ImageUploadForm imageUploadForm, String hash, String... thumb) {
        if (thumb.length == 0) {
            return getPath(imageUploadForm.getUserId(), imageUploadForm.getRound())
                    + hash + "_";
        } else {
            return getPath(imageUploadForm.getUserId(), imageUploadForm.getRound()) + "thumb/"
                    + hash + "_";
        }
    }

    public boolean fileDelete(File path){
        try {
            if (!path.exists()) {
                return false;
            }
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
            return path.delete();
        } catch (Exception e) {
            throw new FileException(StatusCode.NOTFOUND);
        }
    }
    public boolean fileSizeCheck(MultipartFile multipartFile,MultipartFile ...multipartFiles){
        long size = 0;

        if(multipartFile.getSize() >= 1048576){
            throw new FileException(StatusCode.THUMBNAILWRITE);
        }
        for(MultipartFile m : multipartFiles){
            size+=m.getSize();
            if(size >=  10485760){
                throw new FileException(StatusCode.IMAGEFILEWRITE);
            }
        }
        return true;
    }

}


