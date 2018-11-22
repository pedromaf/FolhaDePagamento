package control;

import view.*;

public class Main {

    public static void main(String[] args) {

        ControleEmpregados controleEmpregados = new ControleEmpregados();

        boolean exit = false;
        int operacao;
        int ultimaOperacao = 0;

        do {
            Menu.menuPrincipal();

            operacao = Input.validarOperacao(1,11);
            switch(operacao) {
                case 1:
                    controleEmpregados.adicionarEmpregado();
                    break;
                case 2:
                    //controleEmpregados.removerEmpregado();
                    break;
                case 3:
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
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    exit = true;
                    break;
            }

            if(operacao <= 7) {
                ultimaOperacao = operacao;
            }
        } while(!exit);

    }
}
