package com.alura.waldir.demo.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_Creacion")
    private LocalDateTime fecha_Creacion;
    private Boolean status;
    private String autor;
    private String curso;
    private String respuestas;

    public Topico(DataRegisterTopico dataRegisterTopico) {
        this.titulo = dataRegisterTopico.titulo();
        this.mensaje= dataRegisterTopico.mensaje();
        this.autor= dataRegisterTopico.autor();
        this.curso= dataRegisterTopico.curso();
        this.fecha_Creacion = LocalDateTime.now();
        this.status = true;
        this.respuestas=null;
    }

    public void eliminarTopico() {
        this.status = false;
    }

    public void dataUpdate(DataupdateTopico dataupdateTopico){
        if(dataupdateTopico.titulo() != null && !dataupdateTopico.titulo().equals(this.titulo)){this.titulo = dataupdateTopico.titulo();}
        if(dataupdateTopico.mensaje() != null && !dataupdateTopico.mensaje().equals(this.mensaje)){this.mensaje = dataupdateTopico.mensaje();}
        if(dataupdateTopico.curso() != null && !dataupdateTopico.curso().equals(this.curso)){this.curso = dataupdateTopico.curso();}
    }
}
