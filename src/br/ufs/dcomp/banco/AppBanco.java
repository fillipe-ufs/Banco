/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fillipe Almeida e Carolina Louzada
 */
package br.ufs.dcomp.banco;

import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Banco> bancos = new ArrayList<>();
        short opcao = 0;
        String sigla;
        String nome;
        Scanner entrada = new Scanner(System.in);
        Banco banco = new Banco("Banco do Brasil", "BB");
        Banco banco1 = new Banco("Bradesco", "BR");
        bancos.add(banco);
        banco.criarAgencia();
        banco1.criarAgencia();
        banco1.criarAgencia();
        Cliente cliente = banco1.cadastrarCliente("Fillipe Almeida", "000000000", 20);
        banco1.getAgencia(0).criarConta(10000, 200, cliente);
        Cliente cliente2 = banco1.cadastrarCliente("Gustavo", "11111111111", 18);
        banco1.getAgencia(1).criarConta(5000, 100, cliente2);

        /*List <Conta> conta1 = new ArrayList<>();
         List <Cliente> cliente1 = new ArrayList<>();
         List <Banco> banco1 = new ArrayList<>();
         List <Agencia> agencia1 = new ArrayList<>(); // Podem ser usados construtores também. */
        do {
            System.out.println("Digite:");
            System.out.println("1 - Para Gestão Auditoria ");
            System.out.println("2 - Para Caixa Eletrônico");
            System.out.println("3 - Para Criar um Banco");

            opcao = entrada.nextShort();
            if (bancos.isEmpty()) {
                opcao = 3;
                System.out.println("Não há bancos cadastrados.");
            }
            switch (opcao) {
                case 1:
                    MenuAuditoria auditoria = new MenuAuditoria(bancos);
                    
                    break;
                case 2:
                    MenuCaixa caixa = new MenuCaixa(bancos);
                    caixa.menuPrincipal();
                    break;
                case 3:
                    System.out.println("Cadastro - BANCO");
                    System.out.println("Digite o nome do novo banco");
                    nome = entrada.nextLine();
                    System.out.println("Digite a sigla do novo banco");
                    sigla = entrada.nextLine();

                    Banco banco3 = new Banco(sigla, nome);

                    bancos.add(banco);
            }
        } while (true);

    }
}
