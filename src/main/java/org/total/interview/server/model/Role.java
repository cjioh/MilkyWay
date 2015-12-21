package org.total.interview.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@Table(
        name = "Role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "roleId"),
                @UniqueConstraint(columnNames = "roleType")
        }
)
public class Role implements Serializable {

    private long roleId;
    private RoleType roleType;
    private Set<User> users;

    public Role() {}

    public Role(RoleType roleType) {
        this.roleType = roleType;
        this.users = new HashSet<User>();
    }

    public Role(long roleId, RoleType roleType, Set<User> users) {
        this.roleId = roleId;
        this.roleType = roleType;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId", unique = true, nullable = false)
    public long getRoleId() {
        return roleId;
    }

    @XmlElement
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "roleType", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    public RoleType getRoleType() {
        return roleType;
    }

    @XmlElement
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.ALL)
    @XmlTransient
    public Set<User> getUsers() {
        if (this.users == null) {
            this.users = new HashSet<User>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleType='" + roleType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (roleType != null ? !roleType.equals(role.roleType) : role.roleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }
}
