
package br.ufs.dcomp.banco.privado;

import java.util.ArrayList;
/**
 *@since 11/03/2016
 * @author Carol
 */
public class Cliente {
    
   // declação de atributos
     private String rg;
     private String nome;
     private double saldo;
     private double limite;
     private short idade;
    //construtor com inicialização de dados para ser usado na criação da conta
    public Cliente( String rg, String nome, double saldo, double limite, short idade){
        this.rg = rg;
        this.nome = nome;
        this.saldo = saldo;
        this.limite = limite;
        this.idade = idade;   
    }
    //construtor de teste ou outros fins
    public Cliente(){
        this.rg = rg;
        this.nome = nome;
        this.saldo = saldo;
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
    public double getSaldoCliente(){
        return this.saldo;
    }
    public double getLimite(){
        return this.limite;
    }
    public short getIdade(){
        return this.idade;
    }
    protected boolean verificaRG(){
        
            
        
    }

}
