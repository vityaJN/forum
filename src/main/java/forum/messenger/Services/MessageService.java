package forum.messenger.Services;
import forum.messenger.DTO.MessageDTO;
import forum.messenger.entity.Message;
import forum.messenger.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        message.setIsDeleted(true);
    }

    @Transactional
    public void recoveryMessage(long messageId) {
        Message message = em.find(Message.class, messageId);
        message.setIsDeleted(false);
    }

    @Transactional
    public long fromWhichTopicTheMessage(long messageId){
       return em.find(Message.class,messageId).getTopic().getId();
    }

    @Transactional
    public void createMessage(MessageDTO messagedto) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = new Message(loggedInUser, loggedInUser.getName(), LocalDateTime.now(), messagedto.getText(), topicService.getTopic(messagedto.getTopicId()));
        em.persist(message);
        topicService.updateLastMessageBy(loggedInUser.getName(), messagedto.getTopicId());
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

    //todo complete this function
    public void updateMessage(Message message) {
    }
}