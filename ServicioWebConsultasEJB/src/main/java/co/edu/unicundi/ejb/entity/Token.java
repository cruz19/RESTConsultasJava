package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "tokens")
@NamedQueries({
    @NamedQuery(name = "Token.buscarPorToken", query = "SELECT COUNT(t) FROM Token t WHERE t.JwtToken=:token"),
    @NamedQuery(name = "Token.buscar", query = "SELECT COUNT(t) FROM Token t WHERE t.JwtToken=:token AND t.usuario.id=:usuario_id"),
    @NamedQuery(name = "Token.eliminarToken", query = "DELETE FROM Token t WHERE t.JwtToken=:token AND t.usuario.id=:usuario_id")
})
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "jwt_token")
    private String JwtToken;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String JwtToken) {
        this.JwtToken = JwtToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
