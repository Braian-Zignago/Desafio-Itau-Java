package dev.braianz.DesafioItau.Controller;

import dev.braianz.DesafioItau.DTO.EstatisticasDTO;
import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import dev.braianz.DesafioItau.Service.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
@Tag(name="Estatisticas",
        description = "Endpoint responsavel por enviar estatisticas até 60 segundo atras das transações em uma lista assincrona.")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(summary = "Envia Estatiticas",
            description = "Envia estatisticas dos ultimos 60 segundos das transações registradas na lista")
    @ApiResponse(responseCode = "200", description = "Estatisticas encontradas", content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = EstatisticasDTO.class))))
    public ResponseEntity<?> estatisticasTransacoes(){
        EstatisticasDTO estatisticasDTO = estatisticaService.enviarEstatisticas();
        return ResponseEntity.status(HttpStatus.OK).body(estatisticasDTO);
    }
}
