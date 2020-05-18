package com.api.starWars.dto;

/**
 * Representa um envelope para retorno das APIs REST
 * @param <T> O tipo da resposta envelopada
 */
public class EnvelopeResponseDTO<T> {

    private T dados;
    private String msg;

    /**
     * Constroi o envelope a partir dos dados informados
     * @param dados Os dados a serem encapsulados
     * @param msg para ser exibida
     */
    public EnvelopeResponseDTO(T dados, String msg) {
        this.dados = dados;
        this.msg = msg;
    }

    /**
     * Constroi o envelope a partir dos dados informados
     * @param msg para ser exibida
     */
    public EnvelopeResponseDTO(String msg) {
        this.msg = msg;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
