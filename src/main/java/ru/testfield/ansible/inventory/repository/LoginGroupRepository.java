package ru.testfield.ansible.inventory.repository;

import org.springframework.stereotype.Repository;
import ru.testfield.ansible.inventory.model.LoginUser;
import ru.testfield.ansible.inventory.model.LoginUserGroup;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;

@Repository
public class LoginGroupRepository {

    final EntityManager em;

    public LoginGroupRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persitAnsibleHostGroup(LoginUserGroup loginUserGroup) {
        em.persist(loginUserGroup);
    }

    public long count(){
        Query queryTotal = em.createQuery("Select count(ahg.uuid) from LoginUserGroup ahg");
        return (long) queryTotal.getSingleResult();
    }

    @Transactional
    public void deleteAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<LoginUserGroup> cd = builder.createCriteriaDelete(LoginUserGroup.class);
        cd.from(LoginUserGroup.class);
        em.createQuery(cd).executeUpdate();
    }
}
