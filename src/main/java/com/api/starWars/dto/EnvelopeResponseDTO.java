package com.api.starWars.dto;

public class EnvelopeResponseDTO<T> {

    private T dados;
    private String msg;

    public EnvelopeResponseDTO(T dados, String msg) {
        this.dados = dados;
        this.msg = msg;
    }

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
