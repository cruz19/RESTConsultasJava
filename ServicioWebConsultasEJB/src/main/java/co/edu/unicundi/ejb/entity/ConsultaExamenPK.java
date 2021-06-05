package co.edu.unicundi.ejb.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Stiven cruz
 * @author Daniel Zambrano
 */
@Embeddable
public class ConsultaExamenPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "consulta_id")
    private Integer idConsulta;
    
    @Column(name = "examen_id")
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.idConsulta);
        hash = 43 * hash + Objects.hashCode(this.idExamen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsultaExamenPK other = (ConsultaExamenPK) obj;
        if (!Objects.equals(this.idConsulta, other.idConsulta)) {
            return false;
        }
        if (!Objects.equals(this.idExamen, other.idExamen)) {
            return false;
        }
        return true;
    }

}
