package ru.testfield.ansible.inventory.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testfield.ansible.inventory.model.AnsibleUser;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Repository
public class AnsibleUserRepository {

    final EntityManager em;

    public AnsibleUserRepository(EntityManager em) {
        this.em = em;
    }

    public List<AnsibleUser> findAnsibleUserByUuidAndUsername(UUID uuid, String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AnsibleUser> cq = cb.createQuery(AnsibleUser.class);

        Root<AnsibleUser> ansibleUser = cq.from(AnsibleUser.class);

        Predicate usernamePredicate = cb.equal(ansibleUser.get("username"), username);
        Predicate uuidPredicate = cb.equal(ansibleUser.get("uuid"), uuid);
        cq.where(usernamePredicate, uuidPredicate);

        TypedQuery<AnsibleUser> query = em.createQuery(cq);
        return query.getResultList();
    }
}
