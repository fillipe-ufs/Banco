/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe que molda operações da classe Agência
 *
 * @author Fillipe Paz
 */
public class Agencia {

    private int codigoConta = 1;
    private int codigo; // Será gerado na classe banco;
    private List<Conta> contas; // Uma lista com vários objetos contas do tipo Conta foi declarada
    // isso traz implicações diretas no construtor

    /**
     * Construtor que inicializa o código da agência através da classe Banco.
     * @param codigo
     */
    protected Agencia(int codigo) {

        contas = new ArrayList<>(); // Um arraylist com objetos do tipo Conta
        this.codigo = codigo;
    }

    /**
     * Método para captar o Montante de somente uma agência. O montante é dado
     * pela soma dos saldos das diversas contas de uma agência.
     *
     * @return double montanteAgencia
     */
    public double getMontanteAgencia() {
        double montanteAgencia = 0;

        for (Conta conta : contas) {
            montanteAgencia = montanteAgencia + conta.getSaldo();
        }
        return montanteAgencia;
    }

    /**
     * Método para captar o saldo de um cliente em uma agência de acordo com o
     * RG.
     *
     * @ param String rg
     * @return double saldoNaAgencia
     */
    public double getSaldoCliente(String rg) {
        double saldoNaAgencia = 0;

        for (Conta conta : contas) {
            if (!conta.buscarCliente(rg).isEmpty()) { // rearranjo List
                saldoNaAgencia = saldoNaAgencia + conta.getSaldo();

            }
        }

        return saldoNaAgencia;
    }

    /**
     * Método para buscar contas através do rg do cliente.
     *
     * @param  rg
     * @return  contasBusca
     */
    public List buscarConta(String rg) {
        List<Conta> contasBusca;
        contasBusca = new ArrayList<>();
        for (Conta conta : this.contas) {
            if (!conta.buscarCliente(rg).isEmpty()) {
                
                contasBusca.add(conta);
            }
        }
        return contasBusca;

    }

    /**
     * Método para buscar conta de uma cliente a partir do código da conta em
     * específico.
     */
    protected List  buscarConta(int codigo) { // Saber a agência onde está a conta é fundamental.
        List <Conta> contas = new ArrayList<>();
        for (int i = 0; i < this.contas.size(); i++) {
            if (codigo == this.contas.get(i).getCodigo()) {
                 contas.add(this.contas.get(i));
            }
        }
        return contas;
         // Validação necessária no módulo principal. Se a lista estiver vazia, logo a conta buscada não existe.
    }

    public int getCodigo() {
        return codigo;
    }

    protected List getContas() {
        return contas;
    }

    /**
     * Método para criar uma conta através da classe agência, que reparassará os
     * parâmetros para a classe Conta. Para cada conta criada um código da conta
     * na agência é incrementado.
     *
     * @param Cliente cliente
     * @param double limite
     * @param double saldo
     */
    public void criarConta(Cliente cliente, double limite, double saldo) {
        Conta conta = new Conta(saldo, cliente, limite, codigo);
        contas.add(conta);
        this.codigoConta++;
    }

    /**
     * Método para cadastrar um cliente e atrelá-lo a uma conta. O parâmetro código refere-se à conta.
     *
     * @param  rg 
     * @param  nome
     * @param  idade
     * @param  codigo
     */
    

    public void cadastroCliente(String rg, String nome, short idade, int codigo) {
        List conta;
        Cliente cliente = new Cliente(rg, nome, idade);
        conta = buscarConta(codigo);
        if (buscarConta(codigo).isEmpty()) {
            System.out.println("Conta não encontrada");
        } else {
            this.contas.get(this.contas.indexOf(conta.get(0))).adicionarCliente(cliente);
            System.out.println("Operação realizada com sucesso");
        }

    }
    
    /**
     * Método para cadastrar um cliente e atrelá-lo a uma conta. O parâmetro código refere-se à conta.
     *
     * @param  cliente
     * @param codigo
     */
    
    public void cadastroCliente(Cliente cliente, int codigo) {
        List conta;
        conta = buscarConta(codigo);
        if (buscarConta(codigo).isEmpty()) {
            System.out.println("Conta não encontrada");
        } else {
            this.contas.get(this.contas.indexOf(conta.get(0))).adicionarCliente(cliente);
            System.out.println("Operação realizada com sucesso");
        }

    }
}
