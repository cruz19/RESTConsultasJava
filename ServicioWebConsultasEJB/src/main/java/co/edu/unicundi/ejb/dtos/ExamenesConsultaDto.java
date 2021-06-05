package co.edu.unicundi.ejb.dtos;

import java.util.List;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class ExamenesConsultaDto {
    private ConsultaDto consulta;
    private List<ExamenInfoDto> examenes;

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }

    public List<ExamenInfoDto> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<ExamenInfoDto> examenes) {
        this.examenes = examenes;
    }
}


