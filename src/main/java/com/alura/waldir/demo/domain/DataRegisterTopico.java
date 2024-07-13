package com.alura.waldir.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataRegisterTopico(
        @NotBlank
        @Valid
        String titulo,
        @NotBlank
        @Valid
        String mensaje,
        @NotBlank
        @Valid
        String autor,
        @NotBlank
        @Valid
        String curso
) {
}
