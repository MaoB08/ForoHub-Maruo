package com.foroHubBackend.foroHubBackend.domain.topicos;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionesTopicos extends RuntimeException{

    @Autowired
    private TopicosRepository topicosRepository;
    public boolean validacionTituloyMensaje(@NotNull String titulo, @NotNull String mensaje) {
        if (topicosRepository.existsByTituloAndMensaje(titulo,mensaje)){
            return false;
        }
        return true;
    }
}
