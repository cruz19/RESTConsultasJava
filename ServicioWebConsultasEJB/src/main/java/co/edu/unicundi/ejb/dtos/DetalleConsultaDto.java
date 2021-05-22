package co.edu.unicundi.ejb.dtos;

/**
 * @author Steven Cruz
 * @author Daniel Zambrano
 */
public class DetalleConsultaDto {
    private Integer id;
    private String diagnostico;
    private String tratamiento;
    private ConsultaDto consulta;

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

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }
}
