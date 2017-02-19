package pl.thewalkingcode.dao;

import pl.thewalkingcode.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;


public class UserDAO {

    @Inject
    private Logger LOG;

    @Inject
    private EntityManager entityManager;

    public User find(int pk) {
        return entityManager.find(User.class, pk);
    }

    @SuppressWarnings("unchecked")
    public User findUserByEmail(String email) {
        LOG.info("email : " + email);
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u from User u WHERE u.email = :EMAIL", User.class)
                .setParameter("EMAIL", email);
        List<User> resultList = query.getResultList();
        LOG.info("found : " + resultList.size());
        return resultList.size() == 1 ? resultList.get(0) : null;
    }

    public void merge(User user) {
        entityManager.merge(user);
    }

    public void create(User user) {
        entityManager.persist(user);
    }

}
