package forum.messenger.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @Getter @Setter
    @Column(name = "password")
    private String password;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "birthday")
    public LocalDate birthday;

    @Getter @Setter
    @OneToMany
    private List<Message> messageList;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Authority> authorities;

    public User() {
    }

    public User(String username, String password, String firstName, String email, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.name = firstName;
        this.email = email;
        this.birthday = birthday;
        authorities = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthority(String authority) {
        this.authorities.remove(authority);
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
