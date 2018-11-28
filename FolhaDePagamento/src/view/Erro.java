package view;

public class Erro {

    //ENTRADA INVALIDA
    public static void operacaoInvalida(int limite1, int limite2) {

        System.out.println("Opcao invalida! Digite valores inteiros entre " + limite1 + " e " + limite2 + ".");
    }

    public static void percentualInvalido() {

        System.out.println("Percentual invalido! Digite valores entre 0.0 e 1.0.");
    }

    public static void idInvalido() {

        System.out.println("ID invalido! Empregado nao encontrado.");
    }

    public static void identificacaoInvalida() {

        System.out.println("ID invalido! Agenda de pagamento nao encontrada.");
    }
}
