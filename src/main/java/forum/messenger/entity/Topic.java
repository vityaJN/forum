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
    private Long id;

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
    @OneToMany(mappedBy = "topic", cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
    private List<Message> messages;

    public Topic(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public Topic() {
        messages = new ArrayList<>();
    }
}
