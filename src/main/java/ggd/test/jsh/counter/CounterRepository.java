package ggd.test.jsh.counter;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class CounterRepository {

    @PersistenceContext
    EntityManager entityManager;


    public void persist(Counter counter) {
        entityManager.persist(counter);
    }

    public Counter get(String id) {
        return entityManager.find(Counter.class, id);
    }

}
