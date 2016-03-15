/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.util.List;

/**
 *
 * @author Fillipe Paz
 */
public class Conta {

    private Cliente cliente;
    private Extrato extrato;
    private double saldo;
    private static int codigo;

    public Conta(double saldo, Cliente cliente) {

        this.cliente = new Cliente();
        IncrementarCodigo();
        extrato = new Extrato();
        this.saldo = saldo;
    }

    private static void IncrementarCodigo() {
        codigo++;
    }

    public boolean Sacar(double quantia) {
        if (quantia > saldo + cliente.getLimite()) {
            System.out.println("A quantia não pode ser sacada, pois ultrapassa o valor disponível para o cliente");
        } else {
            extrato.guardarSaque(saldo, quantia);
            saldo = saldo - quantia;
        }
        return true;

    }

    public double getSaldo() {
        return saldo;
    }
    
    
    public boolean transferir(Conta contaDestino, double quantia) {
        if (quantia > (saldo + cliente.getLimite())) {
            System.out.println("A transferência não pode ser concluída, pois a quantia ultrapassa o valor disponível para o cliente");
        } else {
            extrato.guardarTransferencia(saldo, quantia, contaDestino);
            contaDestino.saldo = contaDestino.saldo + quantia;
            this.saldo = this.saldo-quantia;
            contaDestino.getExtrato().guardarTransferenciaContaDestino(quantia, contaDestino, cliente.getNome());
            
        }

        return true;

    }


    public Extrato gerarExtrato() {
        //Dúvida: Carol, fazer o print das informações no método ou enviá-lo para o AppBanco? Estou em
        //dúvida se é possível exibir lá sem a necessidade de instanciar um objeto do tipo Extrato.
        return extrato;

    }

    public boolean buscarCliente(String rg) {
        if (cliente.verificaRG(rg) == false) {
            return false;
        } else {
            if (cliente.getRG().equals(rg)) {
                return true;
            } else {
                return false;
            }

        }
    }
    
    
    
    public Cliente getCliente()
    {
        return cliente;
    }
    
    public int getCodigo()
    {
        return codigo;
    }

    public Extrato getExtrato()
    {
        return extrato;
    }
    
    private void setSaldo(Double valor)
    {
        this.saldo = this.saldo - valor;
    }
}
