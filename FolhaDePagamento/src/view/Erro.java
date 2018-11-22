package view;

public class Erro {

    public static void operacaoInvalida(int limite1, int limite2) {

        System.out.println("Opcao invalida! Digite valores inteiros entre " + limite1 + " e " + limite2 + ".");
    }

    public static void percentualInvalido() {

        System.out.println("Percentual invalido! Digite valores entre 0.0 e 1.0.");
    }
}
