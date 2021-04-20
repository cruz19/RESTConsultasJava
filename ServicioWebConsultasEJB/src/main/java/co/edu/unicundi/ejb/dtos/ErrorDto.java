package co.edu.unicundi.ejb.dtos;

import java.io.Serializable;

/**
 * @author Stiven Cruz
 * @author Daniel Zambrano
 * @param <T>
 */
public class ErrorDto<T> implements Serializable {
    private T message;
    private String date;
    private String url;

    public ErrorDto(T message, String date, String url) {
        this.message = message;
        this.date = date;
        this.url = url;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
