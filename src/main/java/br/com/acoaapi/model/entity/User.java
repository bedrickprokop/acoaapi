package br.com.acoaapi.model.entity;

import br.com.acoaapi.helper.UserSerializerHelper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonSerialize(using = UserSerializerHelper.class)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "response.error.empty.name")
    @Length(min = 5, max = 20, message = "response.error.length.name")
    private String username;

    @Length(min = 5, max = 15, message = "response.error.length.password")
    private String password;

    @NotNull(message = "response.error.empty.token")
    private String token;

    /*@OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public User() {
    }

    public User(Long id, String username, String password, String token,/*List<UserRole> roles,*/ Account account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        //this.roles = roles;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /*public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }*/

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
