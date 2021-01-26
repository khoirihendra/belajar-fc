package com.hindra.fc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "m_login")
public class Login {
    
    @Id
    @Column(name = "user_id")
    private String userid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "token", nullable = false)
    private String token;

    @Transient
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
