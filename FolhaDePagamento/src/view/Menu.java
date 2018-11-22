package view;

public class Menu {

    public static void menuPrincipal() {

        System.out.println("[1]  Adicionar empregado");
        System.out.println("[2]  Remover empregado");
        System.out.println("[3]  Lancar cartao de ponto");
        System.out.println("[4]  Lancar resultado de venda");
        System.out.println("[5]  Lancar taxa de servico");
        System.out.println("[6]  Alterar detalhes do empregado");
        System.out.println("[7]  Rodar folha de pagamento");
        System.out.println("[8]  Undo/Redo");
        System.out.println("[9]  Alterar agenda de pagamento do empregado");
        System.out.println("[10] Criar nova agenda de pagamento");
        System.out.println("[11] Sair");

        System.out.println("\nDigite a opcao desejada:");
    }

    public static void menuTipoEmpregado() {

        System.out.println("Qual o tipo do novo empregado?");
        System.out.println("[1] Assalariado");
        System.out.println("[2] Horista");
        System.out.println("[3] Comissionado");

        System.out.println("\nDigite a opcao desejada:");
    }

    public static void menuSindicalizado() {

        System.out.println("O empregado pertence ao sindicato?");
        System.out.println("[1] Sim");
        System.out.println("[2] Nao");

        System.out.println("\nDigite a opcao desejada:");
    }

    public static void solicitarNome() {

        System.out.println("Digite o nome do novo empregado:");
    }

    public static void solicitarEndereco() {

        System.out.println("Digite o endereço:");
    }

    public static void solicitarSalarioHora() {

        System.out.println("Digite o valor a ser pago por hora:");
    }

    public static void solicitarSalarioMensal() {

        System.out.println("Digite o valor do salario a ser pago:");
    }

    public static void solicitarComissao() {

        System.out.println("Digite o percentual de comissao [0.0 ate 1.0]:");
    }

    public static void solicitarTaxaSindicato() {

        System.out.println("Digite o percentual da taxa sindical [0.0 ate 1.0]:");
    }

    public static void empregadoAdicionado() {

        System.out.println("\nNovo empregado adicionado!\n");
    }

}