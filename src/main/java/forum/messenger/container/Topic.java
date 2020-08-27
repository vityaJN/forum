package forum.messenger.container;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    private String name;
    private LocalDateTime creationDate;
    private boolean isTheTopicDeleted;

    @Column(name = "lastMessageBy")
    private String lastMessageBy;

    public boolean isTheTopicDeleted() {
        return isTheTopicDeleted;
    }

    public void setTheTopicDeleted(boolean theTopicDeleted) {
        isTheTopicDeleted = theTopicDeleted;
    }

    public Topic() {
        messages = new ArrayList<>();
    }

    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    public String getLastMessageBy() {
        return lastMessageBy;
    }

    public void setLastMessageBy(String lastMessageBy) {
        this.lastMessageBy = lastMessageBy;
    }

    public Topic(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
