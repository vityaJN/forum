package forum.messenger.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Valid
public class TopicCreationDTO {

    @Getter @Setter
    private String name;

    public TopicCreationDTO(String name) {
        this.name = name;
    }
}
