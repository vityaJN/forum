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
    @Column(name = "topic_name")
    private String name;

    @Getter @Setter
    @Column(name = "topic_description")
    private String topicDescription;

    @Getter @Setter
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Getter @Setter
    @Column(name = "is_the_topic_deleted")
    private boolean isTheTopicDeleted;

    @Getter @Setter
    @Column(name = "last_message_by")
    private String lastMessageBy;

    @Getter @Setter
    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    @Getter @Setter
    private int dontDeletedMessagesCount;

    public Topic() {
        this.messages = new ArrayList<>();
    }
}
