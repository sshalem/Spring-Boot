package com.jpa.one2many.bi.eager.entity;

import java.util.HashSet;
import java.util.Set;

//    @OneToMany(mappedBy = "user",
//            fetch = FetchType.EAGER,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @OneToMany(mappedBy = "user",
//            fetch = FetchType.EAGER,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
//            orphanRemoval = true)
//  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS_TB")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long pid;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,  CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<RoleEntity> roles;

    public UserEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RoleEntity role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
        role.setUser(this); // maintain both sides
    }

    public void removeRole(RoleEntity role) {
        roles.remove(role);
        role.setUser(null); // crucial: disconnect owning side
    }
//    @Override
//    public String toString() {
//        return "UserEntity [id=" + id + ", pid=" + pid + ", name=" + name + ", email=" + email + ", password="
//                + password + "]";
//    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", pid=" + pid + ", name='" + name + '\'' + ", email='" + email + '\''
                + ", password='" + password + '\'' + ", roles=" + roles + '}';
    }
}
