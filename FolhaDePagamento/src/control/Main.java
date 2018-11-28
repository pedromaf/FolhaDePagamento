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

            operacao = Input.validarOperacao(0,11);
            operacaoRealizda = false;
            switch(operacao) {
                case 0:
                    controleEmpregados.relatorio(); //OPCAO PARA CONFERIR AS FUNCIONALIDADES (NÃO FAZ PARTE DO PROJETO DO SISTEMA)
                    break;
                case 1:
                    operacaoRealizda = controleEmpregados.adicionarEmpregado();
                    break;
                case 2:
                    operacaoRealizda = controleEmpregados.removerEmpregado();
                    break;
                case 3:
                    operacaoRealizda = controleEmpregados.registrarNoCartaoDePonto();
                    break;
                case 4:
                    operacaoRealizda = controleEmpregados.registrarResultadoDeVenda();
                    break;
                case 5:
                    operacaoRealizda = controleEmpregados.registrarTaxaDeServico();
                    break;
                case 6:
                    operacaoRealizda = controleEmpregados.alterarDadosDoEmpregado();
                    break;
                case 7:
                    operacaoRealizda = controleEmpregados.rodarFolhaDePagamento();
                    break;
                case 8:
                    desfazer = controleEmpregados.desfazerRefazer(ultimaOperacao, desfazer);
                    break;
                case 9:
                    controleEmpregados.alterarAgendaDePagamento();
                    break;
                case 10:
                    controleEmpregados.criarAgendaDePagamento();
                    break;
                case 11:
                    sair = true;
                    break;
            }

            if(operacaoRealizda) {
                ultimaOperacao = operacao;
                desfazer = true;
            }
        } while(!sair);
    }
}


