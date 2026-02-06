package dev.braianz.DesafioItau.Controller;

import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import dev.braianz.DesafioItau.Service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> registrarTransacao(@RequestBody TransacaoRequest transacaoRequest){
        try {
            transacaoService.validarTransacao(transacaoRequest);
            transacaoService.registrarTransacao(transacaoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> transacoes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(transacaoService.enviarDados());
    }

    @DeleteMapping
    public ResponseEntity<?> deletarTransacoes() {
        transacaoService.deletarDados();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
