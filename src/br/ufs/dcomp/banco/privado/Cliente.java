
package br.ufs.dcomp.banco.privado;

import java.util.ArrayList;

/** Classe para captar informações básicas de um cliente de um
 *@since 11/03/2016
 * @author Carol e Fillipe Paz 
 */

public class Cliente {
   
   //declaração de atributos do Cliente
     private String rg; /* Pagamento (boleto: com data e valor), contas conjuntas, classe teste */  
     private String nome;
     private short idade;
     
    /** Construtor com inicialização de dados para ser como teste. */
    public Cliente( String rg, String nome, double saldo, short idade){
        this.idade = idade;
        this.rg = rg;
        this.nome = nome;
        this.idade = idade;   
    }
    /** Construtor de teste para ser inicializado via usuário. */
    public Cliente(){
        this.idade = idade;
        this.rg = rg;
        this.nome = nome;
        this.idade = idade; 
    }
    //métodos acessores
    public String getRG(){
        return this.rg;
    }
    public String getNome(){
        return this.nome;
    }
    
    public short getIdade(){
        return this.idade;
    }
    // métodos modificadores
    public void setRG( String rg){
        this.rg = rg;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setIdade( short idade){
        this.idade = idade;
    }
    /** Verifica e valida o número de RG digitado pelo usuário.
     * @param  String rg
     * @return boolean valorRG - Retorna true caso o usuário tenha digitado somente números.
    */
    protected static boolean verificaRG(String rg){
        boolean valorRG = true;
        for (char c: rg.toCharArray()){
            if( c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' 
               || c == '6' || c == '7' || c == '8' || c == '9')
            {
               valorRG = true; 
            } else{
                System.out.println(" RG inválido!");
                valorRG = false;
            }
        }
           return valorRG;
    }
    /** Verifica a idade do usuário.
      @param short idade
      @return boolean valorIdade - retorna true se o cliente for maior de 16 anos. */
    protected boolean verificaIdade( short idade){
        boolean valorIdade = true;
        if( idade < 16){
            System.out.println(" Criação de contas somente para maior de 16 anos!");
            valorIdade = false;
        } else
            valorIdade = true;
        return valorIdade;
    }
}
