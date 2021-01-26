package com.hindra.fc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_admin")
public class Admin {
    
    @Id
    @Column(name = "user_id")
    private String userid;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "created_at")
    private Date createdat;

    @Column(name = "created_by")
    private String createdby;

    @Column(name = "updated_at")
    private Date updatedat;

    @Column(name = "updated_by")
    private String updatedby;
}
