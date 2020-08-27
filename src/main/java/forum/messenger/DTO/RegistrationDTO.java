package forum.messenger.DTO;

import forum.messenger.common.CommonConstans;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Valid
public class RegistrationDTO {

    @NotNull
    @Size(min = 3, max = 12)
    String firstName;

    @NotNull
    @Size(min = 3, max = 12)
    String username;

    @NotNull
    @Size(min = 3, max = 12)
    String password;

    @NotNull
    @Size(min = 3, max = 12)
    String passwordTwo;

    @Email
    String email;

    @DateTimeFormat(pattern = CommonConstans.DATE_FORMAT)
    LocalDate birthday;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public RegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }
}
