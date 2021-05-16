package co.edu.unicundi.ejb.entity;

import co.edu.unicundi.ejb.entity.DetalleConsulta;
import co.edu.unicundi.ejb.entity.Medico;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-16T14:36:51")
@StaticMetamodel(Consulta.class)
public class Consulta_ { 

    public static volatile SingularAttribute<Consulta, Date> fecha;
    public static volatile ListAttribute<Consulta, DetalleConsulta> detallesConsulta;
    public static volatile SingularAttribute<Consulta, Medico> medico;
    public static volatile SingularAttribute<Consulta, Integer> id;

}