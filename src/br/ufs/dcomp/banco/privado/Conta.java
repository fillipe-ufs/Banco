package br.ufs.dcomp.banco.privado;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe que molda as contas associadas aos clientes de um banco.
 *
 * @author Fillipe Paz
 */
public class Conta {

    // Declaração de atributos e instanciação de objetos.

    private List<Cliente> cliente; // Somente objetos do tipo Cliente podem adicionados à lista de clientes.
    private Extrato extrato; // criação de um atributo do tipo extrato para cada conta
    private double saldo;
    private double limite;
    private int codigo; // Precisa ser modificado de modo a zerar de agência para agência.

    /**
     * Construtor inicializado para instanciação de uma conta associada a um cliente.
     */
    protected Conta(double saldo, Cliente cliente, double limite, int codigo) {

        this.cliente = new ArrayList<>(); // O cliente vai para a lista do tipo Cliente.

        extrato = new Extrato();
        this.saldo = saldo;
        this.cliente.add(cliente); // Conta conjunta possível
        this.limite = limite;
        this.codigo = codigo;
    }

    /**
     * Método para sacar uma quantia da conta. Caso o valor pedido seja maior
     * que o existente na conta, não é permitido o saque.
     *
     * @param double quantia
     * @return boolean true ou false
     */
    public boolean Sacar(double quantia) {

        if (quantia > (limite + saldo)) {
            System.out.println("Valor solicitado ultrapassa limite disponível.");
            return false;
        } else {
            if (saldo <= 0) {
                limite = limite - quantia;
            } else if (quantia > saldo) {
                quantia = quantia - saldo;
                saldo = 0;
                limite = limite - quantia;
            } else if (saldo >= quantia) {
                saldo = saldo - quantia;
            }

            extrato.guardarSaque(this.saldo + this.limite, quantia); // chama-se o método guardarSaque para guardar o valor do saldo inicial e final
            return true;                                             // em extrato

        }
    }

    /**
     * Método para realizar pagamentos. O pagamento é realizado a partir da
     * conta. Através de um código de pagamento o valor é retirado da conta.
     * Caso o valor existente seja menor que o valor do pagamento uma mensagem é
     * retornada.
     *
     * @param Double quantia
     * @param String codigo
     * @return boolean true ou false
     */
    public boolean RealizarPagamento(Double quantia, String codigo) {

        if (quantia > (limite + saldo)) {
            System.out.println("Valor solicitado ultrapassa limite disponível.");
            return false;
        } else {
            extrato.guardarPagamento(saldo + limite, quantia, codigo);
            if (saldo <= 0) {
                limite = limite - quantia;
            } else if (quantia > saldo) {
                quantia = quantia - saldo;
                saldo = 0;
                limite = limite - quantia;
            } else if (saldo >= quantia) {
                saldo = saldo - quantia;
            }

        }

        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * Método para transferência de valores entre contas.
     *
     * @param Conta contaDestino - Conta cuja quantia será enviada.
     * @param double quantia
     */
    public boolean transferir(Conta contaDestino, double quantia) {

        if (quantia > (saldo + limite)) {
            System.out.println("A transferência não pode ser concluída, pois a quantia ultrapassa o valor disponível para o cliente");
        } else {
            extrato.guardarTransferencia(saldo, quantia, contaDestino); // extrato capta o saldo da conta, o valor da transferencia e o número da conta de destino
            contaDestino.extrato.guardarTransferenciaContaDestino(quantia, contaDestino, cliente.get(0).getNome()); // informações copiadas para o extrato da conta de destino
            contaDestino.setSaldo(contaDestino.getSaldo() + quantia); // modificação do saldo da conta destinada
            this.saldo = this.saldo - quantia;

        }
        return true;
    }

    /**
     * Metodo para geração de um extrato das operações de uma conta.
     *
     * @ return Extrato extrato
     */
    public Extrato gerarExtrato() {
        //Dúvida: Carol, fazer o print das informações no método ou enviá-lo para o AppBanco? Estou em
        //dúvida se é possível exibir lá sem a necessidade de instanciar um objeto do tipo Extrato.
        return extrato;

    }

    /**
     * Método para buscar um cliente a partir de seu RG. Serve para qualquer
     * cliente, de qualquer agência e considera que um cliente pode ter mais de
     * uma conta.
     *
     * @param String rg
     * @return List clienteBusca
     */
    public List buscarCliente(String rg) {

        List<Cliente> clienteBusca; // nomeia um arrayList do tipo Cliente para captar o cliente cujo RG confere.
        clienteBusca = new ArrayList<>();

        for (int i = 0; i < cliente.size(); i++) {

            if (Cliente.verificaRG(rg) == false) {
                break;

            } else {
                if (cliente.get(i).getRG().equals(rg)) {
                    clienteBusca.add(cliente.get(i));
                } else {
                    continue;
                } // O acoplamento foi afetado com essa nova solução.

            }

        }
        return clienteBusca; // Se a lista for size 0, então o cliente não se encontra no banco.
    }

    public List getCliente() {
        return cliente;
    }
    
    public void adicionarCliente(Cliente cliente)
    {
        this.cliente.add(cliente);
    }

    public int getCodigo() {
        return codigo;
    }

    public Extrato getExtrato() {
        return extrato;
    }

    public double getLimite() {
        return limite;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo; // Foi necessário para inserir informações no extrato de um cliente externo após 
        // transferência.
    }

}
