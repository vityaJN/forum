package forum.messenger.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

//todo fix validation
@Valid
public class TopicCreationDTO {

    @Getter @Setter
    private String name;

}
