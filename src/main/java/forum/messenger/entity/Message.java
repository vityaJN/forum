package forum.messenger.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @ManyToOne
    private Topic topic;

    @Getter @Setter
    @ManyToOne
    private User user;

    @Getter @Setter
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Column(name = "date")
    private LocalDateTime date;

    @Getter @Setter
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
}
