package org.total.interview.server.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */

@Entity
@Table(
        name = "Role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "roleId"),
                @UniqueConstraint(columnNames = "roleTitle")
        }
)
public class Role {

    private long roleId;
    private String roleTitle;
    private Set<User> users;

    public Role() {

    }

    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public Role(long roleId, String roleTitle, Set<User> users) {
        this.roleId = roleId;
        this.roleTitle = roleTitle;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId", unique = true, nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "roleTitle", unique = true, nullable = false)
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

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleTitle='" + roleTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (roleTitle != null ? !roleTitle.equals(role.roleTitle) : role.roleTitle != null) return false;
        if (users != null ? !users.equals(role.users) : role.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (roleTitle != null ? roleTitle.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
