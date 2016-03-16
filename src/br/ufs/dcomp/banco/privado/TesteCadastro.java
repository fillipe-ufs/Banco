/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.banco.privado;

import java.util.Scanner;

/**
 *
 * @author Fillipe Paz
 */
public class TesteCadastro {

    public static void main(String[] args) {

        Cliente clienteA[] = new Cliente [2];
        Scanner entrada = new Scanner(System.in);
        String nome;
        String rg;
        short idade;
        double saldo;
        double limite;
        for (int i = 0; i < 2; i++) {
            
            System.out.println("Digite o nome do cliente");
            nome = entrada.nextLine();
            System.out.println("Digite o RG do cliente");
            rg = entrada.next();
            System.out.println("Digite a idade do cliente:");
            idade = entrada.nextShort();
            System.out.println("Digite o saldo do cliente:");
            saldo = entrada.nextDouble();
            System.out.println("Digite o limite do cliente");
            limite = entrada.nextDouble();
            clienteA[i] = new Cliente(rg, nome, saldo, limite, idade);
            
        }

    }
}
