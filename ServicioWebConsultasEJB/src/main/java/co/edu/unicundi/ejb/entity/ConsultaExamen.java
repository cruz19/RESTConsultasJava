package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Entity
@Table(name = "consulta_examen")
@NamedQueries({
    @NamedQuery(name = "ConsultaExamen.listar", query = "SELECT e FROM ConsultaExamen e"),
    @NamedQuery(name = "ConsultaExamen.contar", query = "SELECT COUNT(e) FROM ConsultaExamen e"),
    @NamedQuery(name = "ConsultaExamen.buscarPorConsulta", query = "SELECT e FROM ConsultaExamen e WHERE e.consulta.id = :idConsulta"),
    @NamedQuery(name = "ConsultaExamen.buscarPorExamen", query = "SELECT e FROM ConsultaExamen e WHERE e.examen.id = :idExamen"),
    @NamedQuery(name = "ConsultaExamen.buscar", query = "SELECT COUNT(e) FROM ConsultaExamen e WHERE e.consulta.id = :idConsulta AND e.examen.id = :idExamen"),
    @NamedQuery(name = "ConsultaExamen.eliminar", query = "DELETE FROM ConsultaExamen e WHERE e.consulta.id = :idConsulta AND e.examen.id = :idExamen")
})
public class ConsultaExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ConsultaExamenPK id;
    
    @ManyToOne
    @MapsId("idConsulta")
    private Consulta consulta;
    
    @ManyToOne
    @MapsId("idExamen")
    @NotNull(message = "El examen es requerido")
    private Examen examen;
   
    @Column(name = "info_adicional")
    private String infoAdicional;

    public ConsultaExamenPK getId() {
        return id;
    }

    public void setId(ConsultaExamenPK id) {
        this.id = id;
    }
    
    @JsonBackReference
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    @JsonBackReference
    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
    
}
