package org.total.interview.server.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */

@Entity
@Table(
        name = "role"
)
public class Role {

    private Integer roleId;
    private String roleTitle;
    private Set<User> users;
//123
    public Role() {

    }

    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public Role(Integer roleId, String roleTitle, Set<User> users) {
        this.roleId = roleId;
        this.roleTitle = roleTitle;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "roleTitle", nullable = false)
    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
