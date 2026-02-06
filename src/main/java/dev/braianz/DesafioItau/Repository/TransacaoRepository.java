package dev.braianz.DesafioItau.Repository;

import dev.braianz.DesafioItau.DTO.TransacaoRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    private List<TransacaoRequest> transacoesSalvas = new ArrayList<>();

    public void salvarDados(TransacaoRequest transacaoRequest){
        transacoesSalvas.add(transacaoRequest);
    }

    public List<TransacaoRequest> enviarDados(){
        return transacoesSalvas;
    }

    public void deletarDados(){
        transacoesSalvas.clear();
    }

}
