package br.ufs.dcomp.banco.privado;

import java.util.List;
import java.util.ArrayList;

/**
 * Molda as operações da classe Banco.
 *
 * @author Fillipe Paz
 */
public class Banco {

    // declaração de atributos e lista de agências.
    private static int codigoBancos;
    private int codigoAgencias;
    private int codigo;
    private String sigla;
    private List<Cliente> clientes;
    private String nome;
    private double montante;
    private List<Agencia> agencias;

    /**
     * Construtor que recebe um nome e uma sigla para cada banco cadastrado.
     */
    public Banco(String sigla, String nome) {
        this.nome = nome;
        this.sigla = sigla;
        agencias = new ArrayList<>();
        codigo = codigoBancos;
        clientes = new ArrayList<>();
        incrementarCodigoBancos();
        codigoAgencias = 0;
    }

    /**
     * Método sem retorno ou parâmetro, apenas para incrementar o código para
     * cada agência criada em um banco.
     */
    private void incrementarCodigoAgencia() {
        codigoAgencias++;
    }

    /**
     * Método sem retorno ou parâmetro, apenas para incrementar o código para
     * cada banco criado.
     */
    private void incrementarCodigoBancos() {
        codigoBancos++;
    }

    /**
     * Método para retornar montante total de um banco através da soma dos
     * montantes das agências de cada banco.
     *
     * @return double montante
     */
    public double getMontante() {
        for (int i = 0; i < agencias.size(); i++) {
            montante = montante + agencias.get(i).getMontanteAgencia();
        }

        return montante;
    }

    /**
     * Método que retorna o saldo total do cliente de todas as agências daquele
     * banco através do número do RG.
     *
     * @param rg - tipo String
     * @return double - saldoCliente
     */
    public double getSaldoCliente(String rg) {
        double saldoCliente = 0;
        if (Cliente.verificaRG(rg) == false) {

        }
        List<Conta> contas;
        contas = new ArrayList<>();
        for (int i = 0; i < agencias.size(); i++) {
            if (!agencias.get(i).buscarConta(rg).isEmpty()) {
                contas = agencias.get(i).buscarConta(rg);
                for (int j = 0; j < contas.size(); j++) {
                    saldoCliente = saldoCliente + contas.get(j).getSaldo();

                }

            }

        }
        return saldoCliente;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Método para encontrar contas determinadas de uma agência em específico.
     *
     * @param codAgencia - tipo int
     * @param codConta - int
     * @return List - contabuscada
     */
    public List encontrarConta(int codAgencia, int codConta) {
        List<Conta> contaBuscada;
        contaBuscada = new ArrayList<>();
        for (int i = 0; i < agencias.size(); i++) {
            if (agencias.get(i).getCodigo() == codAgencia) {
                contaBuscada = agencias.get(i).buscarConta(codConta);
                break;
            }
            if (i == agencias.size() - 1) {
                System.out.println("Agência não encontrada!");
            }
        }

        return contaBuscada;
    }

    /**
     * Método que adiciona uma nova agência ao Banco.
     */
    public void criarAgencia() {
        Agencia agencia = new Agencia(codigoAgencias);
        this.agencias.add(agencia);
        incrementarCodigoAgencia();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List getAgencia() {
        return agencias;
    }

    public Agencia getAgencia(int codigo) {

        for (int i = 0; i < agencias.size(); i++) {
            if (agencias.get(i).getCodigo() == codigo) {
                return agencias.get(i);
            }
        }

        return null;
    }

    public List getClientes() {
        return clientes;
    }

    /**
     * Método que procura um objeto cliente na lista pelo RG.
     *
     * @param rg - tipo String
     * @return null
     */
    public Cliente procurarCliente(String rg) {
        for (int i = 0; i < getClientes().size(); i++) {

            if (clientes.get(i).getRG().equals(rg) == true) {
                return clientes.get(i);
            }
        }
        return null;
    }

    /**
     * Método para cadastrar um cliente. Se o cliente já estiver cadastrado pelo
     * rg, ele chama o procurarCliente.
     *
     * @param nome - tipo String
     * @param rg - tipo String
     * @param idade - int
     * @return Cliente - retorna o objeto cliente
     */
    public Cliente cadastrarCliente(String nome, String rg, int idade) {

        Cliente cliente;
        if (procurarCliente(rg) == null) {
            cliente = new Cliente(rg, nome, idade);
            clientes.add(cliente);
            return cliente;

        } else {
            return procurarCliente(rg);
        }

    }

}
