package com.gramola.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;

    private String bar;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "gramola_cookie")
    private String gramolaCookie;

    private String pwd;

    @Column(name = "creation_token_id")
    private String creationTokenId;

    // Constructores
    public User() {}

    public User(String email, String bar, String pwd, String clientId, String clientSecret) {
        this.email = email;
        this.bar = bar;
        this.pwd = pwd;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    // Getters y Setters (Necesarios para que funcione)
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBar() { return bar; }
    public void setBar(String bar) { this.bar = bar; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }

    public String getGramolaCookie() { return gramolaCookie; }
    public void setGramolaCookie(String gramolaCookie) { this.gramolaCookie = gramolaCookie; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public String getCreationTokenId() { return creationTokenId; }
    public void setCreationTokenId(String creationTokenId) { this.creationTokenId = creationTokenId; }
}