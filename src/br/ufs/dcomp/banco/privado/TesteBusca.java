/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufs.dcomp.banco.privado;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Fillipe Paz
 */
public class TesteBusca {
    
    
    public List buscarCliente(String rg) {
        List<Cliente> clienteBusca;
        clienteBusca = new ArrayList<>();

        for (int i = 0; i < cliente.size(); i++) {

            if (cliente.get(i).verificaRG(rg) == false) {
                continue;

            } else {
                if (cliente.get(i).getRG().equals(rg)) {
                    clienteBusca.add(cliente.get(i))
                } else {
                    continue;
                } // Problemas nesse método

            }

        }
        return clienteBusca;
    } 

}
