/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Fillipe Paz
 */
public class Conta {

    private List<Cliente> cliente;
    private Extrato extrato;
    private double saldo;
    private double limite;
    private static int codigo; // Precisa ser modificado de modo a zerar de agência para agência.

    public Conta(double saldo, Cliente cliente, double limite) {

        this.cliente = new ArrayList<>();
        IncrementarCodigo();
        extrato = new Extrato();
        this.saldo = saldo;
        this.cliente.add(cliente); // Conta conjunta possível
        this.limite = limite;
    }

    private static void IncrementarCodigo() {
        codigo++;
    }

    public boolean Sacar(double quantia) {

        if (quantia > (limite + saldo)) {
            System.out.println("Valor solicitado ultrapassa limite disponível.");
            return false;
        } else {
            if (saldo <= 0) {
                limite = limite - quantia;
            } else if (quantia > saldo) {
                quantia = quantia - saldo;
                saldo = 0;
                limite = limite - quantia;
            } else if (saldo >= quantia) {
                saldo = saldo - quantia;
            }

            extrato.guardarSaque(this.saldo + this.limite, quantia);

            return true;

        }
    }

    public boolean RealizarPagamento(Double quantia, String codigo) {

        if (quantia > (limite + saldo)) {
            System.out.println("Valor solicitado ultrapassa limite disponível.");
            return false;
        } else {
            extrato.guardarPagamento(saldo + limite, quantia, codigo);
            if (saldo <= 0) {
                limite = limite - quantia;
            } else if (quantia > saldo) {
                quantia = quantia - saldo;
                saldo = 0;
                limite = limite - quantia;
            } else if (saldo >= quantia) {
                saldo = saldo - quantia;
            }

        }

        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean transferir(Conta contaDestino, double quantia) {
        // precisa refinar os critérios e impedir a transferencia entre bancos diferentes
            if (quantia > (saldo + limite)) {
                System.out.println("A transferência não pode ser concluída, pois a quantia ultrapassa o valor disponível para o cliente");
            } else {
                extrato.guardarTransferencia(saldo, quantia, contaDestino);
                contaDestino.extrato.guardarTransferenciaContaDestino(quantia, contaDestino, cliente.get(0).getNome());
                contaDestino.setSaldo(contaDestino.getSaldo()+quantia);
                this.saldo = this.saldo - quantia;
                

            }
    return true;    
    }

        

    public Extrato gerarExtrato() {
        //Dúvida: Carol, fazer o print das informações no método ou enviá-lo para o AppBanco? Estou em
        //dúvida se é possível exibir lá sem a necessidade de instanciar um objeto do tipo Extrato.
        return extrato;

    }

    public List buscarCliente(String rg) {

        List<Cliente> clienteBusca;
        clienteBusca = new ArrayList<>();

        for (int i = 0; i < cliente.size(); i++) {

            if (Cliente.verificaRG(rg) == false) {
                break;

            } else {
                if (cliente.get(i).getRG().equals(rg)) {
                    clienteBusca.add(cliente.get(i));
                } else {
                    continue;
                } // O acoplamento foi afetado com essa nova solução.

            }

        }
        return clienteBusca; // Se a lista for size 0, então o cliente não se encontra no banco.
    }

    public Cliente getCliente(int i) {
        return cliente.get(i);
    }

    public int getCodigo() {
        return codigo;
    }

    public Extrato getExtrato() {
        return extrato;
    }

    public double getLimite() {
        return limite;
    }
    
    public void setSaldo(double saldo)
    {
        this.saldo = saldo; // Foi necessário para inserir informações no extrato de um cliente externo após 
                            // transferência.
    }

}
