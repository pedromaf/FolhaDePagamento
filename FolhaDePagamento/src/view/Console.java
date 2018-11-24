package view;

public class Console {


    //MENUS
    public static void menuPrincipal() {

        System.out.println("\n[0]  Relatorio de empregados (VERIFICAR DADOS)");
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

        solicitarOpcao();
    }

    public static void menuTipoEmpregado() {

        System.out.println("\nQual o tipo do novo empregado?");
        System.out.println("[1] Assalariado");
        System.out.println("[2] Horista");
        System.out.println("[3] Comissionado");

        solicitarOpcao();
    }

    public static void menuSindicalizado() {

        System.out.println("\nO empregado pertence ao sindicato?");
        System.out.println("[1] Sim");
        System.out.println("[2] Nao");

        solicitarOpcao();
    }

    public static void menuRegistrarNoCartaoDePonto() {

        System.out.println("\nO empregado esta entrando ou saindo?");
        System.out.println("[1] Entrando");
        System.out.println("[2] Saindo");

        solicitarOpcao();
    }



    //SOLICITAR ENTRADA
    public static void solicitarOpcao() {

        System.out.println("\nDigite a opcao desejada:");
    }

    public static void solicitarId() {

        System.out.println("\nDigite o id do empregado:");
    }

    public static void solicitarNome() {

        System.out.println("Digite o nome do empregado:");
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

    public static void solicitarValorDaVenda() {

        System.out.println("Digite o valor da venda realizada:");
    }



    //ADICIONAR
    public static void empregadoAdicionado() {

        System.out.println("Novo empregado adicionado!");
    }



    //REMOVER
    public static void listaVazia() {

        System.out.println("Nenhum empregado encontrado.");
    }

    public static void empregadoRemovido() {

        System.out.println("Empregado removido!");
    }



    //REGISTRAR CARTAO DE PONTO
    public static void empregadoNaoHorista() {

        System.out.println("O empregado selecionado nao e horista.");
    }

    public static void entradaRegistrada() {

        System.out.println("Entrada registrada!");
    }

    public static void entradaJaRegistrada() {

        System.out.println("Este empregado ja registrou a entrada hoje.");
    }

    public static void saidaRegistrada() {

        System.out.println("Saida registrada!");
    }

    public static void entradaAindaNaoRegistrada() {

        System.out.println("Este empregado ainda nao registrou a entrada.");
    }

    public static void saidaJaRegistrada() {

        System.out.println("Este empregado ja registrou a saida hoje.");
    }



    //REGISTRAR RESULTADO DE VENDA
    public static void empregadoNaoComissionado() {

        System.out.println("O empregado selecionado nao e comissionado.");
    }

    public static void resultadoDeVendaRegistrado() {

        System.out.println("Resultado de venda registrado!");
    }


    //DESFAZER/REFAZER
    public static void operacaoNaoRealizada() {

        System.out.println("Nenhuma operacao foi realizada.");
    }

    public static void adicionarDesfeito() {

        System.out.println("Operacao desfeita! Empregado removido.");
    }

    public static void adicionarRefeito() {

        System.out.println("Operacao refeita! Empregado adicionado.");
    }

    public static void removerDesfeito() {

        System.out.println("Operacao desfeita! Empregado adicionado.");
    }

    public static void removerRefeito() {

        System.out.println("Operacao refeita! Empregado removido.");
    }

    public static void registroNoCartaoDePontoDesfeito() {

        System.out.println("Operacao desfeita! Registro no cartao de ponto removido.");
    }

    public static void registroNoCartaoDePontoRefeito() {

        System.out.println("Operacao refeita! Registro no cartao de ponto adicionado.");
    }

    public static void registroResultadoDeVendaDesfeito() {

        System.out.println("Operacao desfeita! Registro de resultado de venda removido.");
    }

    public static void registroResultadoDeVendaRefeito() {

        System.out.println("Operacao refeita! Registro de resultado de venda adicionado.");
    }


    //GERAL
    public static void mostrarString(String string) {

        System.out.println(string);
    }
}