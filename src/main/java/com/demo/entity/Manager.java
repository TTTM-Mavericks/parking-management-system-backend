package com.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    @Column(name = "Id_Manager", unique = false)
    private String IdUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "Id_Manager", referencedColumnName = "Id_User", insertable = false, updatable = false)
    private User user;

    @Column(name = "Role")
    private int Role;
    // role 1 : security
    // role 2: manager Area
    // role 3: Manager

    @OneToOne(mappedBy = "manager")
    private Building building;
}
