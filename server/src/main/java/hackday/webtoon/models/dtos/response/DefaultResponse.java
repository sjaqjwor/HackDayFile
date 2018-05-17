package hackday.webtoon.models.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DefaultResponse {
    private String result;
    private String msg;
    private Object data;
    private String statusCode;
}
