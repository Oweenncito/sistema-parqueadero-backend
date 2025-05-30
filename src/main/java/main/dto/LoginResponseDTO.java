package main.dto;

import main.model.Usuario;

public class LoginResponseDTO {
    private Usuario user;
    private String token;

    public LoginResponseDTO(Usuario user, String token) {
        this.user = user;
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
