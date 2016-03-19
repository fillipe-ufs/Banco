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
 * Classe que molda as operações relacionadas ao extrato.
 * @author Fillipe Paz
 */
public class Extrato {

    private List<String> datas;
    private List<String> operacoes;
    private List<Double> quantia;
    private List<Double> saldoAnterior;
    private List<String> nomeDestino;
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

    private String getDate() {
        minhaData = new Date();
        String data = formato.format(minhaData);
        return data;
    }

    private String setOperacao(String operador) {
        return operador;
    }
    /** Método para guardar dados de transferencias bancarias.
     * @param saldoAnterior - double
     * @param quantia - double
     * @param contaDestino - Conta
     */
    public void guardarTransferencia(Double saldoAnterior, Double quantia, Conta contaDestino) {

        List <Cliente> cliente = new ArrayList<>();
        this.operacoes.add(setOperacao("Transferência (-)"));
        this.saldoAnterior.add(saldoAnterior);
        this.quantia.add(quantia);
        cliente = contaDestino.getCliente();
        this.nomeDestino.add(cliente.get(0).getNome());
        this.datas.add(getDate());
    }
    /** Metodo para guardar dados de Saques.
     * @param saldoAnterior - double
     * @param quantia - double
     */
    public void guardarSaque(Double saldoAnterior, Double quantia) {
        this.operacoes.add(setOperacao("Saque (-)"));
        this.saldoAnterior.add(saldoAnterior);
        this.quantia.add(quantia);
        this.nomeDestino.add("Saque"); // Não precisa exibir
        this.datas.add(getDate());
    }
    /** Metodo para receber depósito.
     * @param contaDestino
     * @param quantia
     */
    public void receberDeposito(Conta contaDestino, Double quantia) {
        this.operacoes.add(setOperacao("Depósito ATM (+)"));
        this.saldoAnterior.add(contaDestino.getSaldo());
        this.quantia.add(quantia);
        this.nomeDestino.add("Depósito ATM"); // Não precisa exibir
        this.datas.add(getDate());

    }
    /** Metodo para guardar informações no extrato referente a conta de destino.
     * @param quantia - double
     * @param contaDestino -  - String
     * @param nomeContaOrigem
     */
    public void guardarTransferenciaContaDestino(Double quantia, Conta contaDestino, String nomeContaOrigem) {

        
        this.operacoes.add(setOperacao("Transferência (+)"));
        this.saldoAnterior.add(contaDestino.getSaldo());
        this.quantia.add(quantia);
        this.nomeDestino.add(nomeContaOrigem);
        this.datas.add(getDate());
        
       
    }
    /** Metodo para guardar informações de pagamento.
     * @param saldoAnterior - double
     * @param quantia - double
     * @param codigo - codigo do  - String
     */
    public void guardarPagamento(Double saldoAnterior, Double quantia, String codigo) {
        this.operacoes.add(setOperacao("Pagamento (-)"));
        this.saldoAnterior.add(saldoAnterior);
        this.quantia.add(quantia);
        this.nomeDestino.add(codigo); // código do boleto.
        this.datas.add(getDate());
    }
    
    public List getOperacoes()
    {
        return operacoes;
    }
    
    public List getNomeDestino()
    {
        return nomeDestino;
    }
    
    public List getDatas()
    {
        return datas;
    }
    
    public List getQuantia()
    {
        return quantia;
    }
    
    public List getSaldoAnterior()
    {
        return saldoAnterior;
    }
    
    
    

}
