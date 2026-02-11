package dev.braianz.DesafioItau.Controller;

import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import dev.braianz.DesafioItau.Service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
@Tag(name="Transaões",
    description = "Endpoint responsavel por manipular as transações em uma lista assincrona.")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(summary = "Cria Transação",
        description = "Recebe uma transação valida e adiciona na lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação registrada com sucesso", content = @Content),
        @ApiResponse(responseCode = "422", description = "Transação inválida ou fora do tempo permitido", content = @Content),
        @ApiResponse(responseCode = "400", description = "Requisição inválida ou erro no servidor", content = @Content)
    }
)
    public ResponseEntity<?> registrarTransacao(@RequestBody TransacaoRequest transacaoRequest){
        try {
            transacaoService.validarTransacao(transacaoRequest);
            transacaoService.registrarTransacao(transacaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Operation(summary = "Retorna todas as transações",
            description = "Retorna todas as transações registradas na lista")
    @ApiResponse(responseCode = "302", description = "Transações encontradas", content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TransacaoRequest.class))))
    public ResponseEntity<?> transacoes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(transacaoService.enviarDados());
    }

    @DeleteMapping
    @Operation(summary = "Deleta todas as transações",
            description = "Deleta todas as transações registradas na lista")
    @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso")
    public ResponseEntity<?> deletarTransacoes() {
        transacaoService.deletarDados();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
