package hackday.webtoon.models;

import hackday.webtoon.utils.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * Created by koseungbin on 2018. 5. 17.
 */

@Getter
@Setter
@ToString
@Alias("Image")
public class Image {
    private long userId;
    private long manuscriptId;
    private long sequence;
    private String path;
    private String mediaType;
    private String originName;
}
