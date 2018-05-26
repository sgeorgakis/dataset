package com.syntax.interview.service.dto;

import com.syntax.interview.service.dto.changeset.ChangeSetDTO;

import java.util.Set;

public class UserDTO {

    private Long id;

    private String userName;

    private Set<ChangeSetDTO> changeSets;

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

    public Set<ChangeSetDTO> getChangeSets() {
        return changeSets;
    }

    public void setChangeSets(Set<ChangeSetDTO> changeSets) {
        this.changeSets = changeSets;
    }
}
