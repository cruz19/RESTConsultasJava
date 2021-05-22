package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Embeddable
public class ConsultaExamenPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "id_consulta")
    private Integer idConsulta;
    
    @Column(name = "id_examen")
    private Integer idExamen;

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }
    
}
