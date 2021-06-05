package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class ConsultaExamenDto implements Serializable {
    private ConsultaDto consulta;
    private ExamenDto examen;
    private String infoAdicional;

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }

    public ExamenDto getExamen() {
        return examen;
    }

    public void setExamen(ExamenDto examen) {
        this.examen = examen;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
