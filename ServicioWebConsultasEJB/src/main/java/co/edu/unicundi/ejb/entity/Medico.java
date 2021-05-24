package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "Medico.listar", query = "SELECT m FROM Medico m ORDER BY m.id"),
    @NamedQuery(name = "Medico.contar", query = "SELECT COUNT(m) FROM Medico m"),
    @NamedQuery(name = "Medico.buscarPorEmail", query = "SELECT COUNT(m) FROM Medico m WHERE (:id = -1 OR m.id != :id) AND m.correo = :email"),
    @NamedQuery(name = "Medico.eliminar", query = "DELETE FROM Medico m WHERE m.id = :id")
})
public class Medico implements Serializable{
    private static final long serialVersionUID = 1L;
    
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
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "El correo no es válido")
    private String correo;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento no puede ser actual ni futura")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    
    @NotNull(message = "El objeto dirección es requerido")
    @OneToOne(mappedBy = "medico", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Direccion direccion;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> consultas;
    
    @Transient
    private String fechaNacimientoStr;

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

    @JsonIgnore
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @JsonManagedReference
    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getFechaNacimientoStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(fechaNacimiento);
    }

    public void setFechaNacimientoStr(String fechaNacimientoStr) {
        this.fechaNacimientoStr = fechaNacimientoStr;
    }
}
