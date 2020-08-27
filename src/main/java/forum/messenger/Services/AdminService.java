package forum.messenger.Services;

import forum.messenger.container.Message;
import forum.messenger.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AdminService {

    @PersistenceContext
    EntityManager em;

    //todo add more orderby options
    @Transactional
    public List<Message> getUserMessages(String orderby, String direction, String user) {

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
                orderby = "name";
        }

        List<Message> messages = em.createQuery("SELECT m FROM  Message m where m.user.username = :userName").setParameter("userName", user).getResultList();

        return messages;
    }

    @Transactional
    public List<User> getUsers() {
        List<User> users = em.createQuery("SELECT u from  User u  ").getResultList();
        return users;
    }
}
