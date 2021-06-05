package co.edu.unicundi.ejb.exceptions;

/**
 * @author Daniel Zambrano
 * @author Steven Cruz
 */
public class JwtTokenException extends Exception {
    public JwtTokenException(String message) {
        super(message);
    }
}

