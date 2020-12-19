package forum.messenger.Services;
import forum.messenger.DTO.MessageDTO;
import forum.messenger.entity.Message;
import forum.messenger.entity.Topic;
import forum.messenger.entity.User;
import forum.messenger.helpers.SecHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @PersistenceContext
    private EntityManager em;

    private final TopicService topicService;

    @Autowired
    public MessageService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Transactional
    public void deleteMessage(long messageId) {
        Message message = em.find(Message.class, messageId);
        Topic topic = message.getTopic();
        topic.setDontDeletedMessagesCount(topic.getDontDeletedMessagesCount()-1);
        message.setIsDeleted(true);
        topicService.updateLastMessageBy(message.getTopic().getId());
    }

    @Transactional
    public void recoveryMessage(long messageId) {
        Message message = em.find(Message.class, messageId);
        message.getTopic().setDontDeletedMessagesCount(message.getTopic().getDontDeletedMessagesCount()+1);
        message.setIsDeleted(false);
        topicService.updateLastMessageBy(message.getTopic().getId());
    }

    /**
     * Returns with the actual message's topic id
     */
    @Transactional
    public long findTheTopicIdOfMessage(long messageId){
       return em.find(Message.class,messageId).getTopic().getId();
    }

    @Transactional
    public void createMessage(MessageDTO messagedto) {
        User loggedInUser = SecHelper.getLoggedInUser();
        Message message = new Message(loggedInUser,LocalDateTime.now(),messagedto.getText(), topicService.getTopic(messagedto.getTopicId()));
        message.getTopic().setDontDeletedMessagesCount(message.getTopic().getDontDeletedMessagesCount()+1);
        em.persist(message);
        topicService.updateLastMessageBy(messagedto.getTopicId());
    }

    @Transactional
    public List<Message> getDeletedMessages() {
        List<Message> messagesList = em.createQuery("select m from Message m where m.isDeleted = true ").getResultList();
        return messagesList;
    }

    @Transactional
    public Message getASingleMessage(Long messageId) {
        return em.find(Message.class, messageId);
    }

    @Transactional
    public void updateMessage(Message message) {
       Message updateMessage = em.find(Message.class,message.getId());
       updateMessage.setText(message.getText());
    }
}