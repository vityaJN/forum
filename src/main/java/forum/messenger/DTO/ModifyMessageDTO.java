package forum.messenger.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ModifyMessageDTO {

    private String text;

    private long messageId;

}
