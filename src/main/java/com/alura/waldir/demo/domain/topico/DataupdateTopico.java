package com.alura.waldir.demo.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataupdateTopico(
        @NotNull
        Long id,
        @NotBlank
        @Valid
        String titulo,
        @NotBlank
        @Valid
        String mensaje,
        @NotBlank
        @Valid
        String curso
) {
}
