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
    protected String name;

    @Getter @Setter
    @Column(name = "topic_description")
    private String topicDescription;

    @Getter @Setter
    @Column(name = "creation_date")
    protected LocalDateTime creationDate;

    @Getter @Setter
    @Column(name = "is_the_topic_deleted")
    protected boolean isTheTopicDeleted;

    @Getter @Setter
    @Column(name = "last_message_by")
    protected String lastMessageBy;

    @Getter @Setter
    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    public Topic() {
        messages = new ArrayList<>();
    }
}
