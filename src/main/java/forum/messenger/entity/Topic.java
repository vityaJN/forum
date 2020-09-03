package forum.messenger.entity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    private LocalDateTime creationDate;

    @Getter @Setter
    private boolean isTheTopicDeleted;

    @Getter @Setter
    @Column(name = "lastMessageBy")
    private String lastMessageBy;

    @Getter @Setter
    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    public Topic() {
        messages = new ArrayList<>();
    }
}
