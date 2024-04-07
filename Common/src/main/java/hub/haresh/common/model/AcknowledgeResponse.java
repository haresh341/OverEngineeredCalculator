package hub.haresh.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcknowledgeResponse {
    private String message;
    private String url;
}
