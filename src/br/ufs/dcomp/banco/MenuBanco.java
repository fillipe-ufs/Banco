package br.ufs.dcomp.banco;

import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Neste menu estarão as operações inerentes ao Banco.
 * @author Fillipe Paz e 
 */
public class MenuBanco {
    public static void main(String[] args){
        
        Scanner ler = new Scanner(System.in);
        Agencia agencia;
        List<Banco> bancos = new ArrayList<>();;
        short opcao;
        
        System.out.println(" Bem vindo ao menu de Gestão do Banco!\n O que gostaria de fazer?");
        System.out.println(" Digite:");
        System.out.println(" 1 - Para criar um Banco:\n"
                         + " 2 - Para criar uma agência:\n"
                         + " 3 - Para verficar o Montante total do banco:\n"
                         + " 4 - Para verificar o saldo total de um cliente no banco:\n"
                         + " 5 - Para buscar uma conta específica em todo o banco: ");
        opcao = ler.nextShort();
        
        switch(opcao){
            case 1 :    
                
                System.out.println(" Qual o nome do Banco?");
                String numeroBanco = ler.next();
                System.out.println("Qual a sigla referente ao banco?");
                String sigla = ler.next();
                for( int i = 0; i < bancos.size(); i++){
                    Banco banco = new Banco(numeroBanco, sigla);
                }
                
            case 2 :
                bancos.get(0).criarAgencia();
                System.out.println(" Agência criada com sucesso!");
                   
            case 3 : 
                System.out.println(" O Banco possui o seguinte Montante: " + bancos.get(0).getMontante());
            
            case 4 : 
                System.out.println(" Digite o RG do cliente:");
                String rg = ler.next();
                System.out.println(" Saldo total do cliente com RG de número " + rg
                        + " é R$ " + bancos.get(0).getSaldoCliente(rg));
                
            case 5 : 
                System.out.println(" Para buscar uma conta específica precisamos das seguintes informações:");
                System.out.println(" Digite o código da agência onde o cliente se encontra: ");
                int codigoAgencia = ler.nextInt();
                System.out.println(" Digite o código da conta do cliente:");
                int codigoConta = ler.nextInt();
                System.out.println(" Situação da conta: " 
                        + bancos.get(0).encontrarConta(codigoAgencia, codigoConta));
        }
        
    }
    
    
    
    
}
