package br.ufs.dcomp.banco;

import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Neste menu estarão as operações inerentes a auditoria de um banco.
 * @author Carol
 */
public class MenuAuditoria {

     
    public static void main(String[] args){
        
        Scanner ler = new Scanner(System.in);
        AppBanco auditoria = new AppBanco();
        short opcao;
        int numeroBanco;
        int contadorBanco;
        int numeroAgencia;
        
        System.out.println(" Bem vindo ao menu de Gestão para Auditoria!\n O que gostaria de fazer?");
        System.out.println(" Digite:");
        System.out.println(" 1 - Para cadastrar um banco + " 
                         + " 2 - Para cadastrar uma agência."
                         + " 3 - Para verficar o Montante do Banco.\n"
                         + " 4 - Para verificar o Montante de uma Agência."
                         + " 5 - Para verificar o saldo total de um cliente no banco.\n"
                         + " 6 - Para verificar o saldo total de um cliente em uma agência.");
        opcao = ler.nextShort();
        switch(opcao){
            
            case 1 : 
                System.out.println(" Digite o nome do banco:");
                String nomeBanco = ler.next();
                System.out.println(" Digite a sigla do Banco");
                String siglaBanco = ler.next();
                auditoria.cadastrarBanco(siglaBanco, nomeBanco);
                System.out.println(" Banco cadastrado com sucesso!");
                break;
            case 2 :
                System.out.println(" Qual o número do banco para o qual pretende criar uma agência?");
                numeroBanco = ler.nextInt();
                for( int i = 0; i < auditoria.bancos.size();i++){
                    if(numeroBanco == auditoria.bancos.get(i).getCodigo())
                        auditoria.bancos.get(i).criarAgencia();
                }
                System.out.print(" Agência criada com sucesso!");
                break;
                
            case 3 :
                System.out.println(" Digite o número do banco para o qual gostaria de saber o montante:");
                numeroBanco = ler.nextInt();
                for( int i = 0; i < auditoria.bancos.size();i++){
                    if(numeroBanco == auditoria.bancos.get(i).getCodigo())
                        System.out.println(" Montante do banco:" + auditoria.bancos.get(i).getMontante());
                }
                System.out.println(" Gostaria de verificar o montante de uma agência deste banco?"
                                    + " Responda 1 para sim ou 0  para não. ");
                int resposta = ler.nextInt();
                if( resposta == 1){
                    System.out.println(" Digite o número da agência:");
                    numeroAgencia = ler.nextInt();
                    auditoria.bancos.get()
                    
                    }
                    
                break;
            case 4 :
                
                
            case 5 :
                
            case 6 :
            
        }
    }
}
