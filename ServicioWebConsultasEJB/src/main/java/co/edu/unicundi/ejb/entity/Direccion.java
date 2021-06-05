package co.edu.unicundi.ejb.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "direccion")
@NamedQueries({})
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @JsonIgnore
    private Integer id;
    
    @Column(name = "direccion_detallada", nullable = false, length = 25)
    @NotNull(message = "La dirección detalllada es requerida")
    @Size(min = 3, max = 25, message = "La dirección detallada debe tener entre 3 y 25 caracteres")
    private String direccionDetallada;
    
    @Column(name = "barrio", nullable = false, length = 25)
    @NotNull(message = "El barrio es requerido")
    @Size(min = 3, max = 25, message = "El barrio debe tener entre 3 y 25 caracteres")
    private String barrio;
    
    @Column(name = "codigo_postal", nullable = false, length = 6)
    @NotNull(message = "El código postal es requerido")
    @Size(max = 6, message = "El código postal no puede tener más de 6 caracteres")
    private String codigoPostal;
   
    @OneToOne
    @MapsId
    @JsonIgnore
    private Medico medico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccionDetallada() {
        return direccionDetallada;
    }

    public void setDireccionDetallada(String direccionDetallada) {
        this.direccionDetallada = direccionDetallada;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}