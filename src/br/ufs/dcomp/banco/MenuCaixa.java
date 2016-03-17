/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco;

import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Fillipe Paz
 */
public class MenuCaixa {

    private  List<Conta> contas;
    private  List<Cliente> clientes;
    private  List<Banco> bancos;
    private  List<Agencia> agencias;

    public MenuCaixa(List contas, List clientes, List agencias, List bancos) {
        this.contas = new ArrayList<>();
        this.bancos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.agencias = new ArrayList<>();
        this.contas = contas;
        this.bancos = bancos;
        this.agencias = agencias;
        this.clientes = clientes;

    }


    public void menuPrincipal()
    {
    

        Scanner entrada = new Scanner(System.in);
        short opcao;
        int codigoBanco;
        int codigoAgencia;
        int codigoConta;
        int codigoBancoDestino;
        int codigoAgenciaDestino;
        int codigoContaDestino;
        double quantia;
        String codigoBoleto;
        

        
do {
            System.out.println("Digite o código do seu Banco");
            codigoBanco = entrada.nextInt();
            System.out.println("Digite o código da sua agência: ");
            codigoAgencia = entrada.nextInt();
            System.out.println("Digite o código da sua conta");
            codigoConta = entrada.nextInt();

            System.out.println("1 - Para Saque");
            System.out.println("2 - Para pagamento de contas");
            System.out.println("3 - Para gerar extrato da conta");
            System.out.println("4 - Para realizar transferência");
            System.out.println("5 - Para voltar ao menu principal");
            System.out.println("6 - Para sair");

            opcao = entrada.nextShort();

            switch (opcao) {
                case 1:

                    System.out.println("Digite a quantia");
                    quantia = entrada.nextDouble();
                    sacar(codigoAgencia, codigoBanco, codigoConta, quantia);

                    break;

                case 2:

                    System.out.println("Digite o código do boleto");
                    codigoBoleto = entrada.next();
                    System.out.println("Digite a quantia");
                    quantia = entrada.nextDouble();
                    realizarPagamento(codigoAgencia, codigoBanco, codigoConta, quantia, codigoBoleto);
                    break;

                case 3:

                    obterExtrato(codigoAgencia, codigoBanco, codigoConta);
                    break;

                case 4:

                    System.out.println("Digite o código do Banco de destino");
                    codigoBancoDestino = entrada.nextInt();
                    System.out.println("Digite o código da agência de destino: ");
                    codigoAgenciaDestino = entrada.nextInt();
                    System.out.println("Digite o código da conta de destino");
                    codigoContaDestino = entrada.nextInt();
                    System.out.println("Digite a quantia");
                    quantia = entrada.nextDouble();
                    transferencia(codigoAgencia, codigoBanco, codigoConta, quantia, codigoAgenciaDestino, codigoBancoDestino, codigoContaDestino);

                case 5:
                    return; // Ele retorna para AppBanco? O importante é não perder as referências.
                case 6:
                    System.exit(0);

            }
        } while (true);
    }

    public void sacar(int agenciaCod, int bancoCod, int contaCod, double quantia) {

        List<Conta> conta;

        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                if (conta.isEmpty()) {
                    System.out.println("A conta não existe");
                    break;
                } else {
                    if (conta.get(0).Sacar(quantia) == true) {
            System.out.println("Operação realizada com sucesso.");
        } else {
            System.out.println("Ocorreu um erro.");
        }
                }
            } else if (i == this.bancos.size() - 1) {
                System.out.println("Banco não encontrado.");
                break;
            }

        }

       
    }

    public void transferencia(int agenciaCod, int bancoCod, int contaCod, double quantia, int agenciaCodDestino, int bancoCodDestino, int contaCodDestino) {

        List<Conta> conta = new ArrayList<>();
        List<Conta> conta2 = new ArrayList<>();
        if (bancoCod != bancoCodDestino) {
            System.out.println("Transferência não pode ser realizada, pois os bancos são diferentes");
            return;
        }

        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                conta2 = bancos.get(i).encontrarConta(agenciaCodDestino, contaCodDestino);
                if (conta.isEmpty() || conta2.isEmpty()) {
                    System.out.println("Conta não localizada.");
                } else {
                    if (conta.get(0).transferir(conta2.get(0), quantia) == true);
                    System.out.println("Operação realizada com sucesso!");
                    break;
                }
            } else if (i == this.bancos.size() - 1) {
                System.out.println("Banco não encontrado.");
            }

        }

    }

    public void realizarPagamento(int agenciaCod, int bancoCod, int contaCod, double quantia, String codigo) {
        List<Conta> conta = new ArrayList<>();

        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                if (conta.isEmpty()) {
                    System.out.println("Conta não localizada.");
                } else {
                    conta.get(0).RealizarPagamento(quantia, codigo);
                }
            }

        }

        if (conta.get(0).RealizarPagamento(quantia, codigo) == true) {
            System.out.println("Operação realizada com sucesso!");
        } else {
            System.out.println("Ocorreu uma falha.");
        }
    }

    public void obterExtrato(int agenciaCod, int bancoCod, int contaCod) {
        Extrato extrato = new Extrato();
        List<Conta> conta = new ArrayList<>();
        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                if (conta.isEmpty()) {
                    System.out.println("Conta não localizada.");
                } else {
                    extrato = conta.get(0).gerarExtrato();
                }
            }

        }

        System.out.println("------------------------------------------");
        System.out.println("|                EXTRATO                 |");
        System.out.println("------------------------------------------");
        System.out.println("Cliente: " + conta.get(0).getCliente().get(0));
        System.out.println("Conta número: " + conta.get(0).getCodigo());

        for (int i = 0; i < extrato.getDatas().size(); i++) {
            System.out.println("\n\n");
            System.out.println("Operação: " + extrato.getOperacoes().get(i));
            System.out.println("Data: " + extrato.getDatas().get(i));
            System.out.println("Saldo anterior: " + extrato.getSaldoAnterior().get(i));
            System.out.println("Destino / Origem: " + extrato.getNomeDestino().get(i));
            System.out.println("Quantia: " + extrato.getQuantia().get(i));

            System.out.println("--------------------------------------");
            if (i == extrato.getDatas().size() - 1) {
                System.out.println("          Saldo: " + conta.get(0).getSaldo());
            }

        }

    }

}
