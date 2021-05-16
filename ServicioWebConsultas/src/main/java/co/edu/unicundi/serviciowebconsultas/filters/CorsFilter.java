package co.edu.unicundi.serviciowebconsultas.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * @author Steven Cruz
 * @author Daniel Cruz
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres) throws IOException {
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,Authorization,content-type");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209500");
    }

}