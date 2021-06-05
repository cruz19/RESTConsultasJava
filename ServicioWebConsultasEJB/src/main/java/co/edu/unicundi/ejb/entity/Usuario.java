package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.listar", query = "SELECT u FROM Usuario u ORDER BY u.id"),
    @NamedQuery(name = "Usuario.contar", query = "SELECT COUNT(u) FROM Usuario u"),
    @NamedQuery(name = "Usuario.eliminar", query = "DELETE FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.buscarPorEmail", query = "SELECT COUNT(u) FROM Usuario u WHERE u.correo = :email"),
    @NamedQuery(name = "Usuario.buscarPorUsername", query = "SELECT COUNT(u) FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.contrasena = :contrasena")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre")
    @NotNull(message = "El nombre es requerido")
    @Size(min = 3, max = 25, message = "El nombre debe tener entre 3 y 25 caracteres")
    private String nombre;
    
    @Column(name = "apellido")
    @NotNull(message = "El apellido es requerido")
    @Size(min = 3, max = 25, message = "El apellido debe tener entre 3 y 25 caracteres")
    private String apellido;
    
    @Column(name = "correo")
    @NotNull(message = "El correo es requerido")
    @Size(min = 3, max = 50, message = "El correo debe tener entre 3 y 60 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "El correo no es válido")
    private String correo;
    
    @Column(name = "username")
    @NotNull(message = "El username es requerido")
    @Size(min = 3, max = 25, message = "El username debe tener entre 3 y 25 caracteres")
    private String username;
    
    @Column(name = "contrasena")
    @NotNull(message = "La contraseña es requerida")
    @Size(min = 4, message = "La contraseña debe tener más de 4 caracteres")
    private String contrasena;
    
    @NotNull(message = "El rol es requerido")
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    
    @Column(name = "estado")
    private Boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
