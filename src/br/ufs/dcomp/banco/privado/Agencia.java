/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe que molda operações da classe Agencia
 *
 * @author Fillipe Paz
 */
public class Agencia {

    private int codigoConta;
    private int codigo; // Será gerado na classe banco;
    private List<Conta> contas;

    /**
     * Construtor que inicializa o código da agência através da classe Banco.
     *
     * @param codigo
     */
    protected Agencia(int codigo) {

        contas = new ArrayList<>();
        this.codigo = codigo;
        codigoConta = 0;
    }

    /**
     * Método para captar o Montante de somente uma agência. O montante é dado
     * pela soma dos saldos das diversas contas de uma agência.
     *
     * @return double montanteAgencia
     */
    public double getMontanteAgencia() {
        double montanteAgencia = 0;

        for (int i = 0; i < contas.size(); i++) {
            montanteAgencia = montanteAgencia + contas.get(i).getSaldo();
        }

        return montanteAgencia;
    }

    /**
     * Método para captar o saldo de um cliente em uma agência de acordo com o
     * RG.
     *
     * @param rg - String
     * @return double saldoNaAgencia
     */
    public double getSaldoCliente(String rg) {
        double saldoNaAgencia = 0;

        for (int i = 0; i < contas.size(); i++) {

            if (!contas.get(i).buscarCliente(rg).isEmpty()) {
                saldoNaAgencia = saldoNaAgencia + contas.get(i).getSaldo();
            }

        }

        return saldoNaAgencia;
    }

    /**
     * Método para buscar contas através do rg do cliente.
     *
     * @param rg
     * @return List - contasBusca
     */
    public List buscarConta(String rg) {
        List<Conta> contasBusca;
        contasBusca = new ArrayList<>();

        for (int i = 0; i < contas.size(); i++) {
            if (!contas.get(i).buscarCliente(rg).isEmpty()) {

                contasBusca.add(contas.get(i));
            }

        }

        return contasBusca;
    }

    /**
     * Método para buscar conta de uma cliente a partir do código da conta em
     * específico.
     *
     * @param codigo
     * @return List - contas
     */
    protected List buscarConta(int codigo) { // Saber a agência onde está a conta é fundamental.
        List<Conta> contas = new ArrayList<>();
        for (int i = 0; i < this.contas.size(); i++) {
            if (codigo == this.contas.get(i).getCodigo()) {
                contas.add(this.contas.get(i));
                return contas;
            }
        }
        return contas;
        // Validação necessária no módulo principal. Se a lista estiver vazia, logo a conta buscada não existe.
    }

    public int getCodigo() {
        return codigo;
    }

    public <Conta> List getContas() {
        return contas;
    }

    public Conta getConta(int codigo) {

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getCodigo() == codigo) {
                return contas.get(i);
            }
        }

        return null;
    }

    /**
     * Método para criar uma conta através da classe agência, que reparassará os
     * parâmetros para a classe Conta. Para cada conta criada um código da conta
     * na agência é incrementado.
     *
     * @param cliente Cliente
     * @param limite double
     * @param saldo double
     * @return boolean
     */
    public boolean criarConta(double limite, double saldo, Cliente cliente) {
        Conta conta = new Conta(saldo, limite, codigoConta, cliente);
        contas.add(conta);
        incrementarCodigoConta();
        return false;
    }

    private void incrementarCodigoConta() {
        codigoConta++;
    }

    /**
     * Método para relacionar dois clientes numas mesma conta.
     *
     * @param codigo
     * @param cliente
     * @return boolean
     */
    public boolean atrelarAConta(int codigo, Cliente cliente) {
        List<Conta> conta;
        conta = buscarConta(codigo);
        if (conta.isEmpty()) {
            return false;
        } else {
            conta.get(0).adicionarCliente(cliente);
            return true;
        }

    }

    /**
     * Método para remover uma conta através do código.
     *
     * @param codigo - int
     * @return boolean
     */
    public boolean removerConta(int codigo) {
        Conta conta;

        conta = getConta(codigo);
        if (conta.getCliente().size() != 1) {
            return false;
        } else {
            contas.remove(conta);
        }
        return true;
    }

}
