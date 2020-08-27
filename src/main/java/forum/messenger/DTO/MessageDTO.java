package forum.messenger.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDTO {

    private long topicId;

    @NotNull
    @Size(min = 3, max = 1000)
    private String text;

    public MessageDTO(long topicId) {
        this.topicId = topicId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public long getTopicId() {
        return topicId;
    }
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}
