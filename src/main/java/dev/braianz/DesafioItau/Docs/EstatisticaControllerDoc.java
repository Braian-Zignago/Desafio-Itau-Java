package dev.braianz.DesafioItau.Docs;

import dev.braianz.DesafioItau.DTO.EstatisticasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name="Estatisticas",
        description = "Endpoint responsavel por enviar estatisticas até 60 segundo atras das transações em uma lista assincrona.")
public interface EstatisticaControllerDoc {

    @Operation(summary = "Envia Estatiticas",
            description = "Envia estatisticas dos ultimos 60 segundos das transações registradas na lista")
    @ApiResponse(responseCode = "200", description = "Estatisticas encontradas", content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = EstatisticasDTO.class))))
    public ResponseEntity<?> estatisticasTransacoes();
}
