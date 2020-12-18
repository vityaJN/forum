package forum.messenger.Services;

import forum.messenger.entity.Topic;
import forum.messenger.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicService {
    private static final Logger logger = LoggerFactory.getLogger(TopicService.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void updateLastMessageBy(String name, long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setLastMessageBy(name);
        em.persist(topic);
    }

    @Transactional
    public void createTopic(Topic topic) {
       if (!doesThisTopicExist(topic.getName())){
           topic.setCreationDate(LocalDateTime.now());
           em.persist(topic);
       }
    }
    private boolean doesThisTopicExist(String topicName){
        try {
          return em.createQuery("SELECT t FROM Topic t where t.name =:topicName").setParameter("topicName", topicName).getSingleResult() != null;
        }catch (javax.persistence.NoResultException noEntity){
            return false;
        }catch (Exception e){
            logger.warn(e.getMessage());
            return false;
        }
    }

    @Transactional
    public void deleteTopic(long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setTheTopicDeleted(true);
    }

    /**
     * recovers the message by changing its boolean value in the database
     * @param topicId
     */
    @Transactional
    public void recoveryTopic(long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setTheTopicDeleted(true);
    }

    /**
     * get single topic by topicID @param
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

