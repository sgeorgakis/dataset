package com.syntax.interview.service.dto.changeset;

import java.util.Date;

public class ChangeSetDTO {

    private Long id;

    private Date dateCreated;

    private Date dateModified;

    private ChangeSetData data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public ChangeSetData getData() {
        return data;
    }

    public void setData(ChangeSetData data) {
        this.data = data;
    }
}
