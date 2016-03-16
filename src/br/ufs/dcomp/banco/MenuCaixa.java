/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufs.dcomp.banco;
import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Fillipe Paz
 */
public class MenuCaixa {
    
    public void sacar (Conta conta, double quantia)
    {
         if(conta.Sacar(quantia) == true)
             System.out.println("Operação realizada com sucesso.");
    }
    
    public void transferencia (Conta contaOrigem, Conta contaDestino, double quantia)
    {
        contaOrigem.transferir(contaDestino, quantia);
    }
    
    public void realizarPagamento (double quantia, String codigo, Conta conta)
    {
        if(conta.RealizarPagamento(quantia, codigo)==true);
        System.out.println("Operação realizada com sucesso!");
    }
    
    public void obterExtrato(Conta conta)
    {
        Extrato extrato = new Extrato();
        extrato = conta.gerarExtrato();
        //falta terminar
        
     }
    

}
