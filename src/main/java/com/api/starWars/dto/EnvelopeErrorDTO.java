package com.api.starWars.dto;

import java.io.Serializable;

/**
 * Representa um envelope para retorno de erros das APIs REST
 */
public class EnvelopeErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int status;
    private String error;
    private String campo;


    /**
     * Constroi o envelope a partir dos dados informados
     * @param status status HTTP do retorno
     * @param error mensagem de erro
     */
    public EnvelopeErrorDTO(int status, String error) {
        this.status = status;
        this.error = error;
    }

    /**
     * Constroi o envelope a partir dos dados informados
     * @param status status HTTP do retorno
     * @param error mensagem de erro
     * @param campo campo em que o erro ocorreu
     */
    public EnvelopeErrorDTO(int status, String error, String campo) {
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
