package conta;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ContaController contas = new ContaController();

        int numero, agencia, tipo, aniversario, numeroDestino;
        String titular, opcao = "0";
        float saldo, limite, valor;
        boolean loopMenu = true;

        // Contas de exemplo
        ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 248, 2, "Lorian Farrell", 4000f, 500.0f);
        contas.cadastrar(cc1);

        ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 689, 1, "Erasmo Kokkinis", 6000f, 200.0f);
        contas.cadastrar(cc2);

        ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 888, 2, "Anteia Gounaris", 5000f, 11);
        contas.cadastrar(cp1);

        ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(),666, 1, "Elisa Poltergeist", 3000f, 10);
        contas.cadastrar(cp2);

        while (loopMenu) {
            // Menu principal
            System.out.println("\nGBANK ECONOMY");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Listar todas as Contas");
            System.out.println("3 - Buscar Conta por Numero");
            System.out.println("4 - Atualizar Dados da Conta");
            System.out.println("5 - Apagar Conta");
            System.out.println("6 - Sacar");
            System.out.println("7 - Depositar");
            System.out.println("8 - Transferir valores entre Contas");
            System.out.println("9 - Sair");

            System.out.print("Entre com a opção desejada: ");
            try {
                opcao = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nDigite valores inteiros!");
                scanner.nextLine();
                opcao = "0";
            }

            if (opcao.equals("9")) {
                System.out.println("\nGBANK ECONOMY - O seu futuro começa aqui!");
                sobre();
                scanner.close();
                System.exit(0);
            }

            switch (opcao) {
                case "1":
                    // Opção para criar uma nova conta
                    System.out.println( "Criar Conta\n\n");

                    System.out.println("Digite o Numero da Agência: ");
                    agencia = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o Nome do Titular: ");
                    titular = scanner.nextLine();

                    do {
                        System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP)");
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                    } while (tipo < 1 || tipo > 2);

                    System.out.println("Digite o Saldo da Conta (R$): ");
                    saldo = scanner.nextFloat();
                    scanner.nextLine();

                    switch (tipo) {
                        case 1:
                            System.out.println("Digite o Limite de Crédito (R$): ");
                            limite = scanner.nextFloat();
                            scanner.nextLine();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                            break;
                        case 2:
                            System.out.println("Digite o dia do Aniversario da Conta: ");
                            aniversario = scanner.nextInt();
                            scanner.nextLine();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                            break;
                    }

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "2":
                    // Opção para listar todas as contas
                    System.out.println("Listar todas as Contas\n\n");
                    contas.listarTodas();
                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "3":
                    // Opção para buscar conta por número
                    System.out.println("Consultar dados da Conta - por número\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = scanner.nextInt();
                    scanner.nextLine();

                    contas.procurarPorNumero(numero);

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "4":
                    // Opção para atualizar dados da conta
                    System.out.println("Atualizar dados da Conta\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = scanner.nextInt();
                    scanner.nextLine();

                    var buscaConta = contas.buscarNaCollection(numero);

                    if (buscaConta != null) {
                        tipo = buscaConta.getTipo();

                        System.out.println("Digite o número da Agência: ");
                        agencia = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Digite o Nome do Titular: ");
                        titular = scanner.nextLine();

                        System.out.println("Digite o Saldo da Conta (R$): ");
                        saldo = scanner.nextFloat();
                        scanner.nextLine();

                        switch (tipo) {
                            case 1:
                                System.out.println("Digite o Limite de Crédito (R$): ");
                                limite = scanner.nextFloat();
                                scanner.nextLine();

                                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

                                break;
                            case 2:
                                System.out.println("Digite o dia do Aniversario da Conta: ");
                                aniversario = scanner.nextInt();
                                scanner.nextLine();

                                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));

                                break;
                            default:
                                System.out.println("Tipo de conta inválido!");
                        }
                    } else {
                        System.out.println("A Conta não foi encontrada!");
                    }

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "5":
                    // Opção para apagar uma conta
                    System.out.println("Apagar a Conta\n\n");

                    System.out.println("Digite o número da conta: ");
                    numero = scanner.nextInt();
                    scanner.nextLine();

                    contas.deletar(numero);

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "6":
                    // Opção para sacar dinheiro
                    System.out.println("Saque\n\n");

                    int numeroConta = 0;

                    do {
                        System.out.println("Digite o Numero da conta: ");
                        numeroConta = scanner.nextInt();
                        scanner.nextLine();

                        if (!contas.numeroContaValido(numeroConta)) {
                            System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        }
                    } while (!contas.numeroContaValido(numeroConta));

                    do {
                        System.out.println("Digite o Valor do Saque (R$): ");
                        valor = scanner.nextFloat();
                        scanner.nextLine();
                    } while (valor <= 0);

                    contas.sacar(numeroConta, valor);

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "7":
                    // Opção para depositar dinheiro
                    System.out.println("Depósito\n\n");

                    do {
                        System.out.println("Digite o Numero da conta: ");
                        numeroConta = scanner.nextInt();
                        scanner.nextLine();

                        if (!contas.numeroContaValido(numeroConta)) {
                            System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        }
                    } while (!contas.numeroContaValido(numeroConta));

                    do {
                        System.out.println("Digite o Valor do Depósito (R$): ");
                        valor = scanner.nextFloat();
                        scanner.nextLine();

                        if (valor <= 0) {
                            System.out.println("Valor de depósito inválido. O valor deve ser maior que zero.");
                        }
                    } while (valor <= 0);
                    contas.depositar(numeroConta, valor);

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                case "8":
                    // Opção para transferir dinheiro entre contas
                    System.out.println( "Transferência entre Contas\n\n");

                    System.out.println("Digite o Número da Conta de Origem: ");
                    numero = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o Número da Conta de Destino: ");
                    numeroDestino = scanner.nextInt();
                    scanner.nextLine();

                    do {
                        System.out.println("Digite o valor da Transferência (R$): ");
                        valor = scanner.nextFloat();
                        scanner.nextLine();
                    } while (valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;

                default:
                    // Opção inválida
                    System.out.println("\nPor favor, digite uma opção válida!\n");

                    System.out.println("Deseja continuar? (S/N) ");
                    loopMenu = continueMenu(scanner.nextLine());

                    break;
            }
        }
    }

    // Método para exibir informações sobre o projeto
    public static void sobre() {
        System.out.println("Design by Giovani Reis");
    }

    // Método para determinar se o usuário deseja continuar no menu
    public static boolean continueMenu(String continuar) {
        if (continuar.equalsIgnoreCase("S")) {
            return true;
        } else {
            System.out.println("Programa finalizado!");
            return false;
        }
    }

    // Método para aguardar a entrada do usuário antes de continuar
    public static void keyPress() {
        try {
            System.out.println("\n\nPressione Enter para Continuar...");
        } catch (Exception e) {
            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }
}
