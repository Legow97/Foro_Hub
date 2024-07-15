package com.alura.waldir.demo.domain;

import java.time.LocalDateTime;

public record DataListTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        LocalDateTime fecha_Creacion

) {
    public DataListTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje().toString(),topico.getCurso().toString(), topico.getFecha_Creacion());
    }
}
