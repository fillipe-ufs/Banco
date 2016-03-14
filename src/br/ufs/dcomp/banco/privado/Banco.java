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

    private int codigo;
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

    private  void incrementarCodigo() {
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

    protected void setMontante(double valor) {
        this.montante = getMontante() + valor;

    }
  
    public String getSigla()
    {
        return sigla;
    }
    
     public String getNome()
    {
        return nome;
    }
     
     

}
