package forum.messenger.DTO;


import lombok.Data;

import javax.validation.Valid;

@Valid
public class TopicCreationDTO {

    private String name;

    public TopicCreationDTO(String name) {
        this.name = name;
    }
}
