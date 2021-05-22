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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "detalle_consulta")
@NamedQueries({
    @NamedQuery(name = "DetalleConsulta.findAll", query = "SELECT d FROM DetalleConsulta d ORDER BY d.id"),
    @NamedQuery(name = "DetalleConsulta.count", query = "SELECT COUNT(d) FROM DetalleConsulta d")
})
public class DetalleConsulta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "diagnostico", nullable = false)
    @NotNull(message = "El diagnóstico es requerido")
    @Size(min = 3, max = 50, message = "El diagnóstico debe tener entre 3 y 50 caracteres")
    private String diagnostico;
    
    @NotNull(message = "El tratamiento es requerido")
    @Size(min = 3, max = 50, message = "El tratamiento debe tener entre 3 y 50 caracteres")
    @Column(name = "tratamiento", nullable = false)
    private String tratamiento;
    
    @NotNull(message = "La consulta es requerida")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @JsonBackReference
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
