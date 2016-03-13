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
public class Agencia {

    private int codigo;
    private List<Conta> contas; // necessariamente uma agência precisa começar com uma conta?
                                // isso traz implicações diretas no construtor

    private void incrementoAgencia() {
        codigo++;
    }

    public Agencia() {
        incrementoAgencia();
        contas =new ArrayList<>();
        /* crio a conta para o pagamento de faturas?
        um possibilidade é criar 1 Cliente cliente nessa classe que só vai ser útil para esse fim
        - tópico: pagamento de contas
       */
        

    }

    public double getMontanteAgencia() {
        double montanteAgencia = 0;

        for (Conta conta : contas) { 
            montanteAgencia = montanteAgencia + conta.getSaldo();
        }
        return montanteAgencia;
    }

    public double getSaldoCliente(String rg) {
        double saldoNaAgencia = 0;

        for (Conta conta : contas) {
            if (conta.buscarCliente(rg) == true) {
                saldoNaAgencia = saldoNaAgencia + conta.getSaldo();
            }
        }

        return saldoNaAgencia;
    }

}
