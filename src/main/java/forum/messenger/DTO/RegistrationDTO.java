package forum.messenger.DTO;

import forum.messenger.common.CommonConstants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Valid
@Data
public class RegistrationDTO {

    @NotNull
    @Size(min = 3, max = 12)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 12)
    private String username;

    @NotNull
    @Size(min = 3, max = 12)
    private String password;

    @NotNull
    @Size(min = 3, max = 12)
    private String passwordTwo;

    @Email
    private String email;

    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT)
    private LocalDate birthday;

}
