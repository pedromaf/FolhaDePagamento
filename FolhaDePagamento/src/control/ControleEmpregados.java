package control;

import model.Empregado;
import view.*;

import java.util.ArrayList;

public class ControleEmpregados {

    private static int idSistemaGeral = 0;
    private static int idSindicatoGeral = 0;

    private ArrayList<Empregado> listaEmpregados;

    private Empregado ultimoEmpregadoAdicionado;
    private Empregado ultimoEmpregadoRemovido;

    ControleEmpregados() {

        listaEmpregados = new ArrayList<>();
    }

    //ADICIONAR
    public boolean adicionarEmpregado() {

        String nome;
        String endereco;
        Empregado.Tipo tipo;
        double salario = 0;
        double comissao = 0;
        boolean sindicalizado;
        double taxaSindicato = 0;
        int idSindicato = 0;

        Console.solicitarNome();
        nome = Input.lerString();

        Console.solicitarEndereco();
        endereco = Input.lerString();

        Console.menuTipoEmpregado();
        tipo = Input.lerTipoEmpregado();

        switch(tipo) {
            case HORISTA:
                Console.solicitarSalarioHora();
                salario = Input.lerDouble();
                break;
            case COMISSIONADO:
                Console.solicitarComissao();
                comissao = Input.validarPercentual();
            case ASSALARIADO:
                Console.solicitarSalarioMensal();
                salario = Input.lerDouble();
                break;
        }

        Console.menuSindicalizado();
        sindicalizado = Input.validarSindicalizado();

        if(sindicalizado) {
            idSindicato = ++idSindicatoGeral;
            Console.solicitarTaxaSindicato();
            taxaSindicato = Input.validarPercentual();
        }

        Empregado novoEmpregado = new Empregado(nome, endereco, tipo, sindicalizado, taxaSindicato, salario, comissao, ++idSistemaGeral, idSindicato);
        ultimoEmpregadoAdicionado = novoEmpregado;
        listaEmpregados.add(novoEmpregado);
        Console.empregadoAdicionado();
        return true;
    }


    //REMOVER
    public boolean removerEmpregado() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
            return false;
        } else {
            int id;

            listarEmpregados();

            Console.solicitarId();
            id = Input.lerInt();

            for(Empregado atual: listaEmpregados) {
                if(atual.getIdSistema() == id) {
                    ultimoEmpregadoRemovido = listaEmpregados.remove(listaEmpregados.indexOf(atual));
                    Console.empregadoRemovido();
                    return true;
                }
            }

            Erro.idInvalido();
            return false;
        }
    }

    private void listarEmpregados() {

        for(Empregado atual: listaEmpregados) {
            Console.mostrarString(atual.toString());
        }
    }


    //DESFAZER/REFAZER
    public boolean desfazerRefazer(int ultimaOperacao, boolean desfazer) {

        switch(ultimaOperacao) {
            case 0:
                Console.operacaoNaoRealizada();
                return desfazer;
            case 1:
                if(desfazer) {
                    desfazerAdicionarEmpregado();
                } else {
                    refazerAdicionarEmpregado();
                }
                break;
            case 2:
                if(desfazer) {
                    desfazerRemoverEmpregado();
                } else {
                    refazerRemoverEmpregado();
                }
                break;
                default:
                    return desfazer;
        }

        return !desfazer;
    }

    private void desfazerAdicionarEmpregado() {

        for(Empregado atual: listaEmpregados) {
            if(ultimoEmpregadoAdicionado.equals(atual)) {
                listaEmpregados.remove(atual);
                Console.adicionarDesfeito();
                return;
            }
        }
    }

    private void refazerAdicionarEmpregado() {

        listaEmpregados.add(ultimoEmpregadoAdicionado);
        Console.adicionarRefeito();
    }

    private void desfazerRemoverEmpregado() {

        listaEmpregados.add(ultimoEmpregadoRemovido);
        Console.removerDesfeito();
    }

    private void refazerRemoverEmpregado() {

        for(Empregado atual: listaEmpregados) {
            if(ultimoEmpregadoRemovido.equals(atual)) {
                listaEmpregados.remove(atual);
                Console.removerRefeito();
                return;
            }
        }
    }
}
