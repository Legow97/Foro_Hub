package com.alura.waldir.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_Creacion")
    private LocalDateTime fecha_Creacion;
    private Integer status;
    private String autor;
    private String curso;
    private String respuestas;

    public Topico(DataRegisterTopico dataRegisterTopico) {
        this.titulo = dataRegisterTopico.titulo();
        this.mensaje= dataRegisterTopico.mensaje();
        this.autor= dataRegisterTopico.autor();
        this.curso= dataRegisterTopico.curso();
        this.fecha_Creacion = LocalDateTime.now();
        this.status = 1;
        this.respuestas=null;
    }
}
