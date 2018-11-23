package control;

import view.*;

public class Main {

    public static void main(String[] args) {

        ControleEmpregados controleEmpregados = new ControleEmpregados();

        boolean sair = false;
        boolean desfazer = true;
        boolean operacaoRealizda;
        int operacao;
        int ultimaOperacao = 0;

        do {
            Console.menuPrincipal();

            operacao = Input.validarOperacao(1,11);
            operacaoRealizda = false;
            switch(operacao) {
                case 1:
                    operacaoRealizda = controleEmpregados.adicionarEmpregado();
                    break;
                case 2:
                    operacaoRealizda = controleEmpregados.removerEmpregado();
                    break;
                case 3:
                    //TODO
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    desfazer = controleEmpregados.desfazerRefazer(ultimaOperacao, desfazer);
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    sair = true;
                    break;
            }

            if(operacaoRealizda) {
                ultimaOperacao = operacao;
            }
        } while(!sair);

    }
}
