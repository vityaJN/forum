package forum.messenger.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MessageDTO {

    private long topicId;

    @NotNull
    @Size(min = 3, max = 1000)
    private String text;

    public MessageDTO(long topicId) {
        this.topicId = topicId;
    }
}
