
package br.ufs.dcomp.banco.privado;

import java.util.ArrayList;

/**
 *@since 11/03/2016
>>>>>>> 
 * @author Carol
 */

public class Cliente {
   
   // declaração de atributos
     private String rg;
     private String nome;
     private double limite;
     private short idade;
    //construtor com inicialização de dados para ser usado na criação da conta
    public Cliente( String rg, String nome, double saldo, double limite, short idade){
        this.rg = rg;
        this.nome = nome;
        this.limite = limite;
        this.idade = idade;   
    }
    //construtor de teste ou outros fins
    public Cliente(){
        this.rg = rg;
        this.nome = nome;
        this.limite = limite;
        this.idade = idade; 
    }
    //métodos acessores
    public String getRG(){
        return this.rg;
    }
    public String getNome(){
        return this.nome;
    }
    public double getLimite(){
        return this.limite;
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
    public void setLimite( double limite){
        this.limite = limite;
    }
    public void setIdade( short idade){
        this.idade = idade;
    }
    protected boolean verificaRG(String rg){
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
