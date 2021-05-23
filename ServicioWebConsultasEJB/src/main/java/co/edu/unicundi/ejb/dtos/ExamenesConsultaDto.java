package co.edu.unicundi.ejb.dtos;

import java.util.List;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class ExamenesConsultaDto {
    private ConsultaDto consulta;
    private List<ExamenDto> examenes;

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }

    public List<ExamenDto> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenDto> examenes) {
        this.examenes = examenes;
    }
}


