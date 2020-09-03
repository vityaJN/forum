package forum.messenger.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    private Set<User> user;

    @Getter @Setter
    @Column(name = "role")
    private String role;

    public Authority() {
        this.user = new HashSet<>();
    }

    public void setUser(User user) {
        this.user.add(user);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
