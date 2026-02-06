package dev.braianz.DesafioItau.Service;

import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    public void validarTransacao(TransacaoRequest transacaoRequest){

        //DATA E HORA PRESENTES
        if (transacaoRequest.getDataHora() == null){
            throw new IllegalArgumentException("Erro: Isso não é uma transação valida, transação sem data e hora");
        }
        if (transacaoRequest.getValor() == null){
            throw new IllegalArgumentException("Erro: Isso não é uma transação valida, transação sem valor");
        }

        //VALOR MAIOR OU IGUAL A 0
        if (transacaoRequest.getValor().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Erro: Isso não é uma transação valida, transação com valor negativo");
        }
        //DATA ANTERIOR OU IGUAL A DATA DE HOJE
        if (transacaoRequest.getDataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Erro: Isso não é uma transação valida, transação com data futura");
        }

    }
}
