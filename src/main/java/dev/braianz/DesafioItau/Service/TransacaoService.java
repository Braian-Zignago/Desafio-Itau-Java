package dev.braianz.DesafioItau.Service;

import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import dev.braianz.DesafioItau.Repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

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

    public void registrarTransacao(TransacaoRequest transacaoRequest){
        transacaoRepository.salvarDados(transacaoRequest);
    }

    public List<TransacaoRequest> enviarDados(){
        return transacaoRepository.enviarDados();
    }

    public List<TransacaoRequest> enviarDados60s(){
        List<TransacaoRequest> transactions = enviarDados();

        return transactions.stream()
                .filter(t -> t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60))).toList();
    }

    public void deletarDados(){
        transacaoRepository.deletarDados();
    }
}
