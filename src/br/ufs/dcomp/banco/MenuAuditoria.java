package br.ufs.dcomp.banco;

import br.ufs.dcomp.banco.privado.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Neste menu estarão as operações inerentes a auditoria de um banco.
 *
 * @author Carolina Louzada e Fillipe Paz
 */
public class MenuAuditoria {

    private List<Banco> bancos;

    public MenuAuditoria(List bancos) {
        this.bancos = new ArrayList<>();
        this.bancos = bancos;
    }

    public int menuPrincipal() {

        Scanner ler = new Scanner(System.in);
        short opcao;
        int numeroBanco;
        int numeroAgencia;
        String nome;
        int idade;
        String rg = "000000";
        Cliente cliente;
        double limite;
        double saldo;
        int numeroConta;

        do {
            System.out.println("\n Bem vindo ao menu de Gestão para Auditoria!\n O que gostaria de fazer?");
            System.out.println(" Digite:");
            System.out.println(//" 1 - Para cadastrar um banco " 
                    " 1 - Para cadastrar uma agência\n"
                    + " 2 - Para verificar o Montante de um Banco ou de uma agência.\n"
                    + " 3 - Para verificar o saldo total de um cliente em um banco ou em uma agência.\n"
                    + " 4 - Para criar uma conta.\n"
                    + " 5 - Remover conta.\n"
                    + " 6 - Alterações cadastrais\n"
                    + " 7 - Atrelar cliente a uma conta\n"
                    + " 8 - Para voltar ao menu principal"
            );
            opcao = ler.nextShort();
            switch (opcao) {

                case 1:
                    System.out.println(" Qual o número do banco para o qual pretende criar uma agência?");
                    numeroBanco = ler.nextInt();
                    if (getBanco(numeroBanco) == null) {
                        System.out.println("Banco não encontrado");
                    } else {
                        getBanco(numeroBanco).criarAgencia();
                        System.out.print(" Agência criada com sucesso!\n");
                        System.out.println("O número da agência criada é: " + (getBanco(numeroBanco).getAgencia().size() - 1));
                    }
                    break;

                case 2:
                    System.out.println(" Digite o número do banco para o qual gostaria de saber o montante:");
                    numeroBanco = ler.nextInt();
                    if (getBanco(numeroBanco) == null) {
                        System.out.println("Banco não encontrado");
                    } else {

                        System.out.println(" Montante do banco: R$" + getBanco(numeroBanco).getMontante());

                    }

                    System.out.println(" Gostaria de verificar o montante de uma agência deste banco?"
                            + "\n 1-  Sim\n 0 - Não. ");
                    int resposta = ler.nextInt();
                    if (resposta == 1) {
                        System.out.println(" Digite o número da agência:");
                        numeroAgencia = ler.nextInt();

                        System.out.println(" Montante da agência especificada: \nR$ "
                                + getBanco(numeroBanco).getAgencia(numeroAgencia).getMontanteAgencia());

                    } else {
                        System.out.println(" Obrigada!"); // Usar return?
                    }
                    break;

                case 3:
                    System.out.println(" Digite o código do banco.");
                    numeroBanco = ler.nextInt();
                    if (getBanco(numeroBanco) == null) {
                        System.out.println("O banco especificado não existe");
                    } else {
                        System.out.println(" Digite o RG do cliente cujo saldo total deseja obter:");
                        rg = ler.next();
                        for (int i = 0; i < bancos.size(); i++) {
                            if (numeroBanco == bancos.get(i).getCodigo()) {
                                System.out.println(" Saldo do cliente no banco:"
                                        + bancos.get(i).getSaldoCliente(rg));
                            }
                        }
                    }
                    System.out.println(" Gostaria de obter o saldo do cliente em uma agência específica?");
                    System.out.println(" Digite: \n 1 - Sim \n 0 - Não.");
                    resposta = ler.nextInt();

                    if (resposta == 1) {
                        System.out.println(" Digite o código da agência:");
                        numeroAgencia = ler.nextInt();
                        if (getBanco(numeroBanco).getAgencia(numeroAgencia) == null) {
                            System.out.println("Agência não encontrada.");
                        } else {
                            System.out.println(" Saldo do cliente na agência: R$"
                                    + getBanco(numeroBanco).getAgencia(numeroAgencia).getSaldoCliente(rg));
                        }
                    }

                    break;

                case 4:
                    System.out.println("------ CRIAÇÃO DE CONTA ------");
                    System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                    numeroBanco = ler.nextInt();
                    while (getBanco(numeroBanco) == null) {
                        System.out.println("O banco não existe.");
                        System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                        numeroBanco = ler.nextInt();
                    }
                    ler.nextLine();
                    System.out.println("Digite o rg do cliente");
                    rg = ler.next();
                    ler.nextLine();
                    System.out.println("Digite a idade do cliente");
                    idade = ler.nextInt();
                    ler.nextLine();
                    System.out.println("Digite o nome do cliente");
                    nome = ler.nextLine();
                    ler.nextLine();
                    cliente = getBanco(numeroBanco).cadastrarCliente(nome, rg, idade);
                    System.out.println("Digite o número da agência");
                    numeroAgencia = ler.nextInt();
                    while (getBanco(numeroBanco).getAgencia(numeroAgencia) == null) {
                        System.out.println("A agência informada não existe.");
                        System.out.println("Digite o código da Agência na qual quer cadastrar o cliente");
                        numeroAgencia = ler.nextInt();
                    }
                    System.out.println("Digite um limite para a conta");
                    limite = ler.nextDouble();
                    System.out.println("Digite um saldo inicial para a conta");
                    saldo = ler.nextDouble();
                    if (getBanco(numeroBanco).getAgencia(numeroAgencia).criarConta(limite, saldo, cliente) == false);
                    System.out.println("Conta criada com sucesso!\n" + "O código da nova conta é: " + (getBanco(numeroBanco).getAgencia(numeroAgencia).getContas().size() - 1));
                    break;

                case 7:
                    System.out.println("------ ATRELAR CLIENTE A UMA CONTA ------");
                    System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                    numeroBanco = ler.nextInt();
                    while (getBanco(numeroBanco) == null) {
                        System.out.println("O banco não existe.");
                        System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                        numeroBanco = ler.nextInt();
                    }

                    System.out.println("Digite o número da agência");
                    numeroAgencia = ler.nextInt();
                    while (getBanco(numeroBanco).getAgencia(numeroAgencia) == null) {
                        System.out.println("A agência informada não existe.");
                        System.out.println("Digite o código da Agência na qual quer cadastrar o cliente");
                        numeroAgencia = ler.nextInt();
                    }
                    ler.nextLine();
                    System.out.println("Digite o RG do cliente");
                    rg = ler.next();
                    ler.nextLine();
                    System.out.println("Digite o nome do cliente");
                    nome = ler.nextLine();
                    System.out.println("Digite a idade do cliente");
                    idade = ler.nextInt();
                    System.out.println("Digite o número da conta à qual deseja atrelar o cliente");

                    numeroConta = ler.nextInt();
                    while (getBanco(numeroBanco).getAgencia(numeroAgencia).getConta(numeroConta) == null) {
                        System.out.println("A conta informada não existe.");
                        System.out.println("Digite o código da conta na qual quer atrelar o cliente");
                        numeroConta = ler.nextInt();
                    }
                    cliente = getBanco(numeroBanco).cadastrarCliente(nome, rg, idade);
                    if (getBanco(numeroBanco).getAgencia(numeroAgencia).atrelarAConta(numeroConta, cliente) == true);
                    System.out.println("Operação realizada com sucesso!");
                    break;
                case 8:
                    return 0;
                case 5:
                    System.out.println("------ REMOVER CONTA ------");
                    System.out.println("Digite o código do Banco do qual deseja remover a conta");
                    numeroBanco = ler.nextInt();
                    while (getBanco(numeroBanco) == null) {
                        System.out.println("O banco não existe.");
                        System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                        numeroBanco = ler.nextInt();
                    }
                    System.out.println("Digite o número da agência");
                    numeroAgencia = ler.nextInt();
                    while (getBanco(numeroBanco).getAgencia(numeroAgencia) == null) {
                        System.out.println("A agência informada não existe.");
                        System.out.println("Digite o código da Agência na qual quer cadastrar o cliente");
                        numeroAgencia = ler.nextInt();
                    }
                    System.out.println("Digite o número da conta que deseja remover: ");
                    numeroConta = ler.nextInt();
                    while (getBanco(numeroBanco).getAgencia(numeroAgencia).getConta(numeroConta) == null) {
                        System.out.println("A conta informada não existe.");
                        System.out.println("Digite o código da conta na qual quer atrelar o cliente");
                        numeroConta = ler.nextInt();
                    }
                    if (getBanco(numeroBanco).getAgencia(numeroAgencia).removerConta(numeroConta) == false) {
                        System.out.println("A conta está atrelada a mais de um cliente. Não pode ser removida.");
                    } else {
                        System.out.println("Conta removida com sucesso!");
                    }
                    break;

                case 6:
                    System.out.println("------ ALTERAÇÃO DE DADOS CADASTRAIS ------");
                    System.out.println("Digite o código do Banco no qual o cliente está cadastrado: ");
                    numeroBanco = ler.nextInt();
                    while (getBanco(numeroBanco) == null) {
                        System.out.println("O banco não existe.");
                        System.out.println("Digite o código do Banco no qual quer cadastrar o cliente");
                        numeroBanco = ler.nextInt();
                    }
                    ler.nextLine();
                    System.out.println("Digite o RG do cliente: ");
                    rg = ler.nextLine();
                    if (getBanco(numeroBanco).procurarCliente(rg) == null) {
                        System.out.println("Cliente não cadastrado");
                    } else {
                        cliente = getBanco(numeroBanco).procurarCliente(rg);
                        ler.nextLine();
                        System.out.println("Digite o novo nome para o Cliente: ");
                        nome = ler.nextLine();
                        ler.nextLine();
                        System.out.println("Digite o novo RG para o Cliente: ");
                        rg = ler.nextLine();
                        System.out.println("Digite a nova idade para o Cliente: ");
                        idade = ler.nextInt();
                        cliente.setNome(nome);
                        cliente.setRG(rg);
                        cliente.setIdade(idade);
                        System.out.println("Alterações realizadas com sucesso!");
                        break;
                    }

                default:
                    System.out.println("Opção inválida!");
                    break;

            }
        } while (true);

    }

    public Banco getBanco(int codigo) {
        for (int i = 0; i < this.bancos.size(); i++) {
            if (bancos.get(i).getCodigo() == codigo) {
                return bancos.get(i);
            }

        }
        return null;

    }

}
