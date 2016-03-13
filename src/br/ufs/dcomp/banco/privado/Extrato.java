/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fillipe Paz
 */
public class Extrato {

    private List<String> datas;
    private List<String> operacoes;
    private List<Double> quantia;
    private List<Double> saldoAnterior;
    private List <String> nomeDestino;
    private final SimpleDateFormat formato;
    private Date minhaData;

    public Extrato() {
        datas = new ArrayList<>();
        formato = new SimpleDateFormat("dd/MM/yyyy");
        operacoes = new ArrayList<>();
        quantia = new ArrayList<>();
        saldoAnterior = new ArrayList<>();
        nomeDestino = new ArrayList<>();
    }

    private void setDate() {
        minhaData = new Date();
        String data = formato.format(minhaData);
        datas.add(data);
    }

    private void setOperacao(String operador) {
        operacoes.add(operador);
    }

    public void guardarTransferencia(Double saldoAnterior, Double quantia, Conta contaDestino) {
        
        setOperacao("Transferência (-)");
        this.saldoAnterior.add(saldoAnterior);
        this.quantia.add(quantia);
        this.nomeDestino.add(contaDestino.getCliente().getNome());
        setDate();
    }

    public void guardarSaque(Double saldoAnterior, Double quantia) {
        setOperacao("Saque (-)");
        this.saldoAnterior.add(saldoAnterior);
        this.quantia.add(quantia);
        setDate();
    }

 
    
    

}
