package control;

import model.Empregado;
import view.*;

import java.util.ArrayList;

public class ControleEmpregados {

    private static int idSistemaGeral = 0;
    private static int idSindicatoGeral = 0;

    private ArrayList<Empregado> listaEmpregados;

    ControleEmpregados() {

        listaEmpregados = new ArrayList<>();
    }

    public void adicionarEmpregado() {

        String nome;
        String endereco;
        Empregado.Tipo tipo;
        double salario = 0;
        double comissao = 0;
        boolean sindicalizado;
        double taxaSindicato = 0;
        int idSindicato = 0;

        Menu.solicitarNome();
        nome = Input.entradaString();

        Menu.solicitarEndereco();
        endereco = Input.entradaString();

        Menu.menuTipoEmpregado();
        tipo = Input.entradaTipoEmpregado();

        switch(tipo) {
            case HORISTA:
                Menu.solicitarSalarioHora();
                salario = Input.entradaDouble();
                break;
            case COMISSIONADO:
                Menu.solicitarComissao();
                comissao = Input.validarPercentual();
            case ASSALARIADO:
                Menu.solicitarSalarioMensal();
                salario = Input.entradaDouble();
                break;
        }

        Menu.menuSindicalizado();
        sindicalizado = Input.validarSindicalizado();

        if(sindicalizado) {
            idSindicato = ++idSindicatoGeral;
            Menu.solicitarTaxaSindicato();
            taxaSindicato = Input.validarPercentual();
        }

        listaEmpregados.add(new Empregado(nome, endereco, tipo, sindicalizado, taxaSindicato, salario, comissao, ++idSistemaGeral, idSindicato));
        Menu.empregadoAdicionado();
    }

    public void removerEmpregado() {
        //TODO
    }
}
