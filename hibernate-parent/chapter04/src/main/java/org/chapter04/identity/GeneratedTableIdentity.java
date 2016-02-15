package org.chapter04.identity;

import javax.persistence.*;

@Entity
public class GeneratedTableIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Column
    String value;

    public GeneratedTableIdentity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
