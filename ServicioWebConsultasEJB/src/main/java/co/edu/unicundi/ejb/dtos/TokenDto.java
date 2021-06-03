package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class TokenDto implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
