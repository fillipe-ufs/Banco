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


    // criar lists aqui para captar o método criar banco
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        short opcao = 0;
        Scanner entrada = new Scanner(System.in);
        List <Conta> conta1 = new ArrayList<>();
        List <Cliente> cliente1 = new ArrayList<>();
        List <Banco> banco1 = new ArrayList<>();
        List <Agencia> agencia1 = new ArrayList<>(); // Podem ser usados construtores também.
        
        
        System.out.println("Digite:");
        System.out.println("1 - Para gestão auditoria ");
        System.out.println("2 - Para gestão de agência");
        System.out.println("3 - Para Caixa Eletrônico");
        System.out.println("4 - Para gestão de Banco");
        
        opcao = entrada.nextShort();
        
        switch(opcao)
        {
            case 1:
                MenuAuditoria auditoria = new MenuAuditoria(banco1, agencia1, conta1);
                break;
            case 2: 
                MenuAgencia agencia = new MenuAgencia();
                break;
            case 4:
                
                MenuBanco banco = new MenuBanco(banco1, agencia1, conta1);
                break;
            case 3: 
                MenuCaixa caixa = new MenuCaixa(conta1, cliente1, agencia1, banco1);
                
                break;
        }
    }
    
    public void cadastrarBanco(String sigla, String nome){
        Banco banco = new Banco(sigla, nome);
        bancos = new ArrayList<>();
        bancos.add(banco);
    }
}
