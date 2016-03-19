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
 * Classe que modelo o menu de operações bancárias.
 * @author Fillipe Paz e Carolina Louzada
 */
public class MenuCaixa {

    private List<Banco> bancos;

    public MenuCaixa(List bancos) {

        this.bancos = new ArrayList<>();
        this.bancos = bancos;

    }

    public int menuPrincipal() {

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

        System.out.println("Digite o código do seu Banco");
        codigoBanco = entrada.nextInt();
        if (getBanco(codigoBanco) == null) {
            System.out.println("O banco não exite");
            return 0;
        }
        System.out.println("Digite o código da sua agência: ");
        codigoAgencia = entrada.nextInt();
        System.out.println("Digite o código da sua conta");
        codigoConta = entrada.nextInt();

        do {
            System.out.println("----- CAIXA ELETRÔNICO - " + getBanco(codigoBanco).getNome().toUpperCase() + "-----");
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
                    System.out.println("Deseja realizar outra operação");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    opcao = entrada.nextShort();
                    break;

                case 2:

                    System.out.println("Digite o código do boleto");
                    codigoBoleto = entrada.next();
                    System.out.println("Digite a quantia");
                    quantia = entrada.nextDouble();
                    realizarPagamento(codigoAgencia, codigoBanco, codigoConta, quantia, codigoBoleto);
                    System.out.println("Deseja realizar outra operação");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    opcao = entrada.nextShort();
                    break;

                case 3:

                    obterExtrato(codigoAgencia, codigoBanco, codigoConta);
                    System.out.println("Deseja realizar outra operação");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    opcao = entrada.nextShort();
                    break;

                case 4:

                    System.out.println("Digite o código do Banco de destino");
                    codigoBancoDestino = entrada.nextInt();
                    if (getBanco(codigoBanco) == null) {
                        System.out.println("O banco não exite");
                        return 0;
                    }
                    System.out.println("Digite o código da agência de destino: ");
                    codigoAgenciaDestino = entrada.nextInt();
                    if (getBanco(codigoBanco).getAgencia(codigoAgenciaDestino) == null) {
                        System.out.println("A agência informada não existe.");
                        return 0;
                    }
                    System.out.println("Digite o código da conta de destino");
                    codigoContaDestino = entrada.nextInt();
                    if (getBanco(codigoBanco).getAgencia(codigoAgenciaDestino).getConta(codigoConta) == null) {
                        System.out.println("A conta informada não existe.");
                        return 0;
                    }
                    System.out.println("Digite a quantia");
                    quantia = entrada.nextDouble();
                    transfere(codigoAgencia, codigoBanco, codigoConta, quantia, codigoAgenciaDestino, codigoBancoDestino, codigoContaDestino);
                    System.out.println("Deseja realizar outra operação");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    opcao = entrada.nextShort();

                case 5:
                    opcao = 0;
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        return 0;
    }
   /**
    * Método criado para pegar todas as informações de um cliente pelo menu para que este possa sacar.
    * @param agenciaCod
    * @param bancoCod
    * @param contaCod
    * @param quantia
    * 
    */
    public void sacar(int agenciaCod, int bancoCod, int contaCod, double quantia) {

        List<Conta> conta;

        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                if (conta.isEmpty()) {
                    System.out.println("A conta não existe");
                    break;
                } else {
                    if (conta.get(0).sacar(quantia) == true) {
                        System.out.println("Operação realizada com sucesso.");
                        break;
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
    /**  Método para captar todas as informações específicas para que  haja uma tranferencia bancaria.
     * @param agenciaCod
     * @param contaCod
     * @param quantia
     * @param agenciaCodDestino
     * @param bancoCodDestino
     * @param contaCodDestino
     */
    public void transfere(int agenciaCod, int bancoCod, int contaCod, double quantia, int agenciaCodDestino, int bancoCodDestino, int contaCodDestino) {

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
                if (conta2.isEmpty()) {
                    System.out.println("Conta de destino não localizada");
                    return;
                }
                if (conta.isEmpty()) {
                    System.out.println("Conta de origem não localizada.");
                    return;
                }
                if (conta.get(0).transferir(conta2.get(0), quantia) == true);
                System.out.println("Operação realizada com sucesso!");
                break;

            } 

        }
        

    }
    /** Método para captar informações  no menu para realizar pagamento.
     @param agenciaCod
     @param bancoCod
     @param contaCod
     @param quantia
     @param codigo*/
    public void realizarPagamento(int agenciaCod, int bancoCod, int contaCod, double quantia, String codigo) {
        List<Conta> conta = new ArrayList<>();

        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == bancoCod) {
                conta = bancos.get(i).encontrarConta(agenciaCod, contaCod);
                if (conta.isEmpty()) {
                    System.out.println("Conta não localizada.");
                } else {
                    if (conta.get(0).realizarPagamento(quantia, codigo) == true) {
                        System.out.println("Operação realizada com sucesso!");
                    } else {
                        System.out.println("Ocorreu uma falha.");
                    }
                }
            }

        }

    }
    /** 
     * Método para obter extrato através das informaçoes passadas no menu.
     * @param agenciaCod
     * @param bancoCod
     * @param contaCod
     */
    public void obterExtrato(int agenciaCod, int bancoCod, int contaCod) {
        Extrato extrato = new Extrato();
        List<Conta> conta = new ArrayList<>();

        conta = getBanco(bancoCod).encontrarConta(agenciaCod, contaCod);
        if (conta.isEmpty()) {
            System.out.println("Conta não localizada.");
            return;
        } else {
            extrato = conta.get(0).gerarExtrato();
        }

        List<Cliente> cliente;
        cliente = conta.get(0).getCliente();
        System.out.println("------------------------------------------");
        System.out.println("|                EXTRATO                 |");
        System.out.println("------------------------------------------");
        System.out.println("Cliente: " + cliente.get(0).getNome());
        System.out.println("Conta número: " + conta.get(0).getCodigo());
        System.out.println("Limite da conta: R$ " + conta.get(0).getLimite());
        System.out.println("Saldo Atual: R$ " + conta.get(0).getSaldo());
        System.out.println("--------------------------------------");
        for (int i = 0; i < extrato.getDatas().size(); i++) {

            System.out.println("Operação: " + extrato.getOperacoes().get(i));
            System.out.println("Data: " + extrato.getDatas().get(i));
            System.out.println("Saldo anterior: R$ " + extrato.getSaldoAnterior().get(i));
            System.out.println("Destino / Origem: " + extrato.getNomeDestino().get(i));
            System.out.println("Quantia: R$ " + extrato.getQuantia().get(i));

            System.out.println("--------------------------------------");
            if (i == extrato.getDatas().size() - 1) {
                System.out.println("          Saldo Atual: R$ " + conta.get(0).getSaldo());
            }

        }

    }

    public Banco getBanco(int codigo) {
        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == codigo) {
                return bancos.get(i);
            }

        }
        return null;

    }
}
