package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "Medico.listarTodos", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.buscarPorCorreo", query = "SELECT m FROM Medico m WHERE m.correo = :correo")
})
public class Medico implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    @NotNull(message = "El nombre es requerido")
    @Size(min = 3, max = 25, message = "El nombre debe tener entre 3 y 25 caracteres")
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 25)
    @NotNull(message = "El apellido es requerido")
    @Size(min = 3, max = 25, message = "El apellido debe tener entre 3 y 25 caracteres")
    private String apellido;
    
    @Column(name = "correo", nullable = false, length = 60, unique = true)
    @NotNull(message = "El correo es requerido")
    @Size(min = 3, max = 60, message = "El correo debe tener entre 3 y 60 caracteres")
    private String correo;
    
    @NotNull(message = "Objeto direccion es requerido")
    @OneToOne(mappedBy = "medico", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Direccion direccion;

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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
