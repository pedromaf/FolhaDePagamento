package control;

import model.Empregado;
import view.Erro;

import java.util.Scanner;

public class Input {

    private static Scanner input = new Scanner(System.in);

    public static int validarOperacao(int limite1, int limite2) {

        int operacao;

        do {
            operacao = entradaInt();

            if(operacao < limite1 || operacao > limite2) {
                Erro.operacaoInvalida(limite1, limite2);
            }

        }while(operacao < limite1 || operacao > limite2);

        return operacao;
    }

    public static double validarPercentual() {

        double percentual;

        do {
            percentual = entradaDouble();

            if(percentual < 0 || percentual > 1) {
                Erro.percentualInvalido();
            }
        }while(percentual < 0 || percentual > 1);

        return percentual;
    }

    public static boolean validarSindicalizado() {

        if(validarOperacao(1,2) == 1) {
            return true;
        }
        return false;
    }

    public static String entradaString() {

        return input.nextLine();
    }

    public static double entradaDouble() {

        return Double.parseDouble(input.nextLine());
    }

    public static int entradaInt() {

        return Integer.parseInt(input.nextLine());
    }

    public static Empregado.Tipo entradaTipoEmpregado() {

        int opcao = validarOperacao(1,3);
        switch(opcao) {
            case 1:
                return Empregado.Tipo.ASSALARIADO;
            case 2:
                return Empregado.Tipo.HORISTA;
            case 3:
                return Empregado.Tipo.COMISSIONADO;
            default:
                return Empregado.Tipo.ASSALARIADO;
        }
    }
}
