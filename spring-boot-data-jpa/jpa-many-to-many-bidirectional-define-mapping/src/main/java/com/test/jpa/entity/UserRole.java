package com.test.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "t_user_role")
@Data
public class UserRole  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="role_id")
    private Role role;
    
    @Column(name="user_role_type")
    private String userRoleType;

}



























//

