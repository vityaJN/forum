package forum.messenger.container;
import forum.messenger.entity.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    User user;

    //todo check out this part
    @Column(name = "isDeleted")
    Boolean isDeleted;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "text")
    private String text;

    public Message() {
    }

    public Message(User user, String name, LocalDateTime date, String text, Topic topic) {
        this.user = user;
        this.name = name;
        this.date = date;
        this.text = text;
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deletedMessage) {
        this.isDeleted = deletedMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
