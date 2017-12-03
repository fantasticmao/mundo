package com.mundo.data.aop;

import com.mundo.data.domain.AbstractDomain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User
 *
 * @author maodh
 * @since 2017/12/2
 */
@Entity
@Table(name = "lang_user")
public class User extends AbstractDomain<Integer> {
    private static final long serialVersionUID = 8813100826145776117L;

    private String username;
    private String password;

    protected User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}