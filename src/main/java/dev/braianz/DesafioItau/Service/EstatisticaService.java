package dev.braianz.DesafioItau.Service;

import dev.braianz.DesafioItau.DTO.EstatisticasDTO;
import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticasDTO enviarEstatisticas(){
        BigDecimal zero = BigDecimal.ZERO;
        EstatisticasDTO estatisticasDTO = new EstatisticasDTO(0,zero,zero,zero,zero);
        List<TransacaoRequest> transactions = transacaoService.enviarDados60s();
        if (transactions.isEmpty()){
            return estatisticasDTO;
        }
        return calcularDados(transactions);
    }

    private EstatisticasDTO calcularDados(List<TransacaoRequest> transactions){

        List<BigDecimal> valores = transactions.stream().map(TransacaoRequest::getValor).toList();

        Long count = (long) valores.size();

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal v : valores) {
            sum = sum.add(v);
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal max = valores.stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal min = valores.stream()
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return new EstatisticasDTO(count, sum, avg, min, max);
    }



}
