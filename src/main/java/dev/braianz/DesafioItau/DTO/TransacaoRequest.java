package dev.braianz.DesafioItau.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Na sua classe TransacaoRequest
@Schema(description = "Dados para criação de uma nova transação")
public class TransacaoRequest {

    @Schema(example = "150.50", description = "Valor da transação (deve ser positivo)")
    private BigDecimal valor;

    @Schema(example = "2026-03-10T20:48:12-03:00", description = "Data e hora no padrão ISO 8601")
    private OffsetDateTime dataHora;
}