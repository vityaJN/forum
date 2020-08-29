package forum.messenger.Services;

import forum.messenger.container.Message;
import forum.messenger.container.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void createTopic(Topic topic) {
        topic.setCreationDate(LocalDateTime.now());
        em.persist(topic);
    }

    @Transactional
    public void deleteTopic(long topicId) {
        Topic topic = em.find(Topic.class, topicId);
        topic.setTheTopicDeleted(true);
    }

    /**
     * this method recover the message by changing its boolean value in the database
     *
     * @param topicId
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

    //todo check the <limitt --(int)-- > bug then refactor
    @Transactional
    public List<Topic> getTopics(long limit) {
        int limitt = (int) limit;

        List<Topic> topics = em.createQuery("select t from Topic t order by creationDate desc ").setMaxResults(limitt)
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

        //      String query = "select m from Message m order by " + orderby + " " + direction + " where m.topic.id = :tId";

        limitedMessages = em.createQuery("select m from Message m where m.topic.id = :tId").setParameter("tId", topicId).setMaxResults(10).getResultList();


        // limitedMessages = em.createQuery("select m from Message m where m.topic.id = :tId").setParameter("tId",topicId).setMaxResults(10).getResultList();

//[select m from forum.messenger.container.Message m order by name desc where m.topic.id = :tId]
        Topic topic = (Topic) em.createQuery("SELECT t from  Topic t where t.id = :tid").setParameter("tid", topicId).getSingleResult();
        topic.setMessages(limitedMessages);
        return topic;
    }
}

