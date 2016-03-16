/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufs.dcomp.banco;
import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Fillipe Paz
 */
public class MenuAuditoria {

    public Banco criarBanco(String nome, String sigla)
    {
        Banco banco = new Banco (nome, sigla);
        return banco;
    }
    public double obterMontante(Banco banco)
    {
        return banco.getMontante();
    }
    
    public double obterMontanteAgencia(Banco banco, int codigo)
    {
        List <Agencia> agencia = new ArrayList<>();
       agencia = banco.getAgencia();
       return agencia.get(codigo).getMontanteAgencia();
    }
}
