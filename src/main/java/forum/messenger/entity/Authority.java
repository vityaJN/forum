package forum.messenger.entity;

import forum.messenger.model.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "authorities",cascade=CascadeType.ALL)
    Set<User> user;

    @Column(name = "role")
    String role;

    public Authority() {
        this.user = new HashSet<>();
    }

    public void setUser(User user) {
        this.user.add(user);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
