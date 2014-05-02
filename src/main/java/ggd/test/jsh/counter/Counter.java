package ggd.test.jsh.counter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Counter {

    @Id
    private String id = UUID.randomUUID().toString();

    private long value;

    public String getId() {
        return id;
    }

    public long getValue() {
        return value;
    }

    public void increment() {
        value++;
    }
}
