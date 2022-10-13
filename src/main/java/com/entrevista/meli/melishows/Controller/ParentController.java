package com.entrevista.meli.melishows.Controller;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

/**
 * Clase abstracta de controladores para funciones genericas
 */
public abstract class ParentController {
    /**
     * Crea una instacia de ModelMapper
     * @return
     */
    protected ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
