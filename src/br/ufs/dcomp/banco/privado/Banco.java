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
public class Banco {

    private static int codigo;
    private int codigoAgencias = 1; 
    private String sigla;
    private String nome;
    private double montante;
    private List<Agencia> agencias;

    public Banco(String sigla, String nome) {
        this.nome = nome;
        this.sigla = sigla;
        agencias = new ArrayList<>();
        incrementarCodigo();
    }

    private static void incrementarCodigo() {
        codigo++;
    }

    public double getMontante() {
        for (Agencia agencia : agencias) {
            montante = agencia.getMontanteAgencia();
        }
        return montante;
    }

    public double getSaldoCliente(String rg) {
        double saldoCliente = 0;
        if (Cliente.verificaRG(rg) == false) {
            return 0;
        }
        List<Conta> contas;
        contas = new ArrayList<>();
        for (Agencia agencia : agencias) {
            if (!agencia.buscarConta(rg).isEmpty()) {
                contas = agencia.buscarConta(rg);
                for (Conta conta : contas) {
                    saldoCliente = saldoCliente + conta.getSaldo();
                }
            }

        }
        return saldoCliente;
    }


    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public List encontrarConta(int codAgencia, int codConta) {
        List<Conta> contaBuscada;
        contaBuscada = new ArrayList<>();
        for (int i = 0; i < agencias.size(); i++) {
            if (agencias.get(i).getCodigo() == codAgencia) {
                contaBuscada = agencias.get(i).buscarConta(codConta);
            }
            if (i == agencias.size() - 1) {
                System.out.println("Agência não encontrada!");
            }
        }
        return contaBuscada; // Tratamento de informação necessário.
    }

    public Cliente encontrarCliente(String rg) //Impede recadastro de um cliente. Tratamento: Se getIdade = 0, logo não hÁ CLIENTE
    {
        Cliente clienteBusca;
        List<Conta> contaBusca;
        contaBusca = new ArrayList<>();
        for (int i = 0; i < agencias.size(); i++) {
            contaBusca = agencias.get(i).buscarConta(rg);

        }
        if (!contaBusca.isEmpty()) {
            clienteBusca = contaBusca.get(0).getCliente(0);
        }

        return clienteBusca; // rearranjo List
    }

}
