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
        // Construtores prontos para teste. Caso queira que o programa rode sem essas informações é só comentar o trecho
        
        Banco banco = new Banco("BB", "Banco do Brasil"); // Banco 0;
        Banco banco1 = new Banco("BR", "Bradesco"); // Banco 1;
        Banco banco2 = new Banco("UFS", "DCOMP"); // Banco 2;
        bancos.add(banco);
        bancos.add(banco1);
        bancos.add(banco2);
        banco.criarAgencia(); //Banco 0, agencia 0
        banco1.criarAgencia(); // Banco1, agencia 0
        banco1.criarAgencia(); // Banco1, agencia 1
        banco2.criarAgencia(); // Banco 2, agencia 0
        Cliente cliente = banco1.cadastrarCliente("Fillipe Almeida", "000000000", 20); //Banco 1
        banco1.getAgencia(0).criarConta(10000, 200, cliente); // Banco 1, agencia 0, conta 0 
        cliente = banco1.cadastrarCliente("Gustavo", "11111111111", 18);
        banco1.getAgencia(1).criarConta(5000, 100, cliente); // Banco1, agencia 1, conta 0;
        cliente = banco2.cadastrarCliente("Carol", "1234", 19);
        bancos.get(2).getAgencia(0).criarConta(15874, 1800, cliente); // Banco 2, agencia 0, conta 0;
        
        // Fim dos construtores

        
        do {
            System.out.println("----------- GESTÃO BANCÁRIA -----------");
            System.out.println("Digite:");
            System.out.println("1 - Para Gestão Auditoria ");
            System.out.println("2 - Para Caixa Eletrônico");
            System.out.println("3 - Para Criar um Banco");
            System.out.println("4 - Para sair");

            opcao = entrada.nextShort();
            if (bancos.isEmpty()) {
                opcao = 3;
                System.out.println("Não há bancos cadastrados.");
            }
            switch (opcao) {
                case 1:
                    MenuAuditoria auditoria = new MenuAuditoria(bancos);
                    auditoria.menuPrincipal();
                    
                    break;
                case 2:
                    MenuCaixa caixa = new MenuCaixa(bancos);
                    caixa.menuPrincipal();
                    break;
                case 3:
                    System.out.println("Cadastro - BANCO");
                    entrada.nextLine();
                    System.out.println("Digite o nome do novo banco");
                    nome = entrada.nextLine();
                    System.out.println("Digite a sigla do novo banco");
                    sigla = entrada.nextLine();

                    Banco banco3 = new Banco(sigla, nome);

                    bancos.add(banco3);
                    System.out.println("O código do banco criado é: " + banco3.getCodigo());
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);

    }
}

