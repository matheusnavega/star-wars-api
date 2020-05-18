package com.api.starWars.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    private int status;
    private String error;
    private String campo;

    public StandardError(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public StandardError(int status, String error, String campo) {
        this.status = status;
        this.error = error;
        this.campo = campo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
