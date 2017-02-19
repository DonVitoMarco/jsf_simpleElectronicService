package pl.thewalkingcode.dao;

import pl.thewalkingcode.model.Role;
import pl.thewalkingcode.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class RoleDAO {

    @Inject
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager
                .createQuery("SELECT r from Role r WHERE r.name = :NAME", Role.class)
                .setParameter("NAME", name);
        List<Role> resultList = query.getResultList();
        return resultList.size() == 1 ? resultList.get(0) : null;
    }

}
