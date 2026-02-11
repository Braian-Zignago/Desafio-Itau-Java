package dev.braianz.DesafioItau.Controller;

import dev.braianz.DesafioItau.DTO.EstatisticasDTO;
import dev.braianz.DesafioItau.Docs.EstatisticaControllerDoc;
import dev.braianz.DesafioItau.Service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController implements EstatisticaControllerDoc {

    private final EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<?> estatisticasTransacoes(){
        EstatisticasDTO estatisticasDTO = estatisticaService.enviarEstatisticas();
        return ResponseEntity.status(HttpStatus.OK).body(estatisticasDTO);
    }
}
