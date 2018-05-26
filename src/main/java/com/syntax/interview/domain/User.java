package com.syntax.interview.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_username", columnNames = "username"))
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ChangeSet> changeSets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<ChangeSet> getChangeSets() {
        return changeSets;
    }

    public void setChangeSets(Set<ChangeSet> changeSets) {
        this.changeSets = changeSets;
    }
}
