package forum.messenger.Services;

import forum.messenger.DTO.TopicCreationDTO;
import forum.messenger.entity.Message;
import forum.messenger.entity.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void updateLastMessageBy(String name, long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setLastMessageBy(name);
        em.persist(topic);
    }

    @Transactional
    public void createTopic(TopicCreationDTO topicDTO) {
        em.persist(new Topic(topicDTO.getName(), LocalDateTime.now()));
    }

    //todo after deleting the topic delete also the messages
    @Transactional
    public void deleteTopic(long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setTheTopicDeleted(true);
    }

    /**
     * recovery the message by topicId
     */
    @Transactional
    public void recoveryTopic(long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setTheTopicDeleted(true);
    }

    /**
     * get single topic by topicID @param
     *
     * @return
     */
    @Transactional
    public Topic getTopic(long topicId) {
        return em.find(Topic.class, topicId);
    }

    @Transactional
    public List<Topic> getTopics(long limit) {
        List<Topic> topics = em.createQuery("select t from Topic t order by creationDate desc ").setMaxResults((int)limit)
                .getResultList();
        return topics;
    }

    //todo implement QUERYDSL
    @Transactional
    public Topic getSingleTopic(long topicId, String orderby, int limit, String direction) {

        List<Message> limitedMessages;

        switch (direction) {
            case "desc":
                break;
            case "asc":
                break;
            default:
                direction = "desc";
        }

        switch (orderby) {
            case "name":
                break;
            case "date":
                break;
            default:
                orderby = "date";
        }

        limitedMessages = em.createQuery("select m from Message m where m.topic.id = :tId").setParameter("tId", topicId).setMaxResults(10).getResultList();

        Topic topic = (Topic) em.createQuery("SELECT t from  Topic t where t.id = :tid").setParameter("tid", topicId).getSingleResult();
        topic.setMessages(limitedMessages);
        return topic;
    }
}

