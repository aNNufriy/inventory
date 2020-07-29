package ru.testfield.ansible.inventory.repository;

import org.springframework.stereotype.Repository;
import ru.testfield.ansible.inventory.model.LoginUser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class LoginUserRepository0 {

    final EntityManager em;

    public LoginUserRepository0(EntityManager em) {
        this.em = em;
    }

    public LoginUser findByLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LoginUser> cq = cb.createQuery(LoginUser.class);

        Root<LoginUser> root = cq.from(LoginUser.class);

        Predicate predicate = cb.equal(root.get("login"), login);

        cq.where(predicate);

        TypedQuery<LoginUser> query = em.createQuery(cq);

        return query.getSingleResult();
    }

    @Transactional
    public void persitLoginUser(LoginUser loginUser) {
        em.persist(loginUser);
    }

    @Transactional
    public void deleteAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<LoginUser> cd = builder.createCriteriaDelete(LoginUser.class);
        cd.from(LoginUser.class);
        em.createQuery(cd).executeUpdate();
    }

    public long count(){
        Query queryTotal = em.createQuery("Select count(ah.uuid) from LoginUser ah");
        return (long) queryTotal.getSingleResult();
    }

    @Transactional
    public void deleteById(UUID uuid) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<LoginUser> cd = cb.createCriteriaDelete(LoginUser.class);
        Root<LoginUser> root = cd.from(LoginUser.class);

        Predicate predicate = cb.equal(root.get("uuid"), uuid);
        cd.where(predicate);

        em.createQuery(cd).executeUpdate();
    }

    public List<LoginUser> findByGroup(String groupName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LoginUser> cq = cb.createQuery(LoginUser.class);
        Metamodel m = em.getMetamodel();
        EntityType<LoginUser> LoginUser_ = m.entity(LoginUser.class);

        Root<LoginUser> loginUser = cq.from(LoginUser.class);
        SetJoin<LoginUser, ?> groups = loginUser.join(LoginUser_.getSet("groups"));

        Predicate predicate = cb.equal(groups.get("groupName"), groupName);

        cq.where(predicate);

        TypedQuery<LoginUser> query = em.createQuery(cq);

        return query.getResultList();
    }
}
