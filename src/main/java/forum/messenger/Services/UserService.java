package forum.messenger.Services;

import forum.messenger.entity.Authority;
import forum.messenger.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService implements UserDetailsManager {

    @PersistenceContext
    EntityManager em;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUser Called");
        try {
            return (User) em.createQuery("select u FROM  User u  JOIN FETCH u.authorities where u.name = :usrname ")
                    .setParameter("usrname", username).getSingleResult();
        } catch (Exception e) {
            String isUserNull = username == null ? " the user is null" : " the user is not null";
            logger.error("user: " + username);
            logger.error(isUserNull);
            logger.error("exception", e);
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    public boolean userExists(String username) {
        try {
            User u = (User) em.createQuery("SELECT  u from  User u where u.username = :usrname").setParameter("usrname", username).getSingleResult();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void createUser(UserDetails userDetails) {
        Authority authority = (Authority) em.createQuery("select a from Authority  a where a.role = :auth").setParameter("auth", "ROLE_USER")
                .getSingleResult();
        User user = (User) userDetails;
        user.addAuthority(authority);
        em.persist(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }
}
