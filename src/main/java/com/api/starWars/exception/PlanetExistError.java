package com.api.starWars.exception;

/**
 * Exception referente a erro de já existir um planeta com o mesmo nome
 */
public class PlanetExistError extends RuntimeException {

    public PlanetExistError() {
        super();
    }

}
