package com.hindra.fc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "m_login")
public class Login {
    
    @Id
    @ApiModelProperty(hidden=true)
    @Column(name = "user_id")
    private String userid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @ApiModelProperty(hidden=true)
    @Column(name = "token", nullable = false)
    private String token;

    @ApiModelProperty(hidden=true)
    @Transient
    private String fullname;

    @ApiModelProperty(hidden=true)
    @Column(name = "created_at")
    private Date createdat;

    @ApiModelProperty(hidden=true)
    @Column(name = "created_by")
    private String createdby;

    @ApiModelProperty(hidden=true)
    @Column(name = "updated_at")
    private Date updatedat;

    @ApiModelProperty(hidden=true)
    @Column(name = "updated_by")
    private String updatedby;
}
