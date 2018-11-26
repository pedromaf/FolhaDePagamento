package control;

import model.Empregado;
import view.Erro;

import java.util.Scanner;

public class Input {

    private static Scanner input = new Scanner(System.in);

    //VALIDAR ENTRADAS
    public static int validarOperacao(int limite1, int limite2) {

        int operacao;

        do {
            operacao = lerInt();

            if(operacao < limite1 || operacao > limite2) {
                Erro.operacaoInvalida(limite1, limite2);
            }

        }while(operacao < limite1 || operacao > limite2);

        return operacao;
    }

    public static double validarPercentual() {

        double percentual;

        do {
            percentual = lerDouble();

            if(percentual < 0 || percentual > 1) {
                Erro.percentualInvalido();
            }
        }while(percentual < 0 || percentual > 1);

        return percentual;
    }

    public static boolean validarOperacaoBinaria() {

        if(validarOperacao(1,2) == 1) {
            return true;
        }
        return false;
    }



    //LER TIPO DE DADO ESPECIFICO
    public static String lerString() {

        return input.nextLine();
    }

    public static double lerDouble() {

        return Double.parseDouble(input.nextLine());
    }

    public static int lerInt() {

        return Integer.parseInt(input.nextLine());
    }

    public static Empregado.Tipo lerTipoEmpregado() {

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

    public static Empregado.FormaDePagamento lerFormaDePagamento() {

        int opcao = validarOperacao(1,3);
        switch(opcao) {
            case 1:
                return Empregado.FormaDePagamento.CHEQUE;
            case 2:
                return Empregado.FormaDePagamento.CORREIO;
            case 3:
                return Empregado.FormaDePagamento.BANCO;
            default:
                return Empregado.FormaDePagamento.BANCO;
        }
    }
}
