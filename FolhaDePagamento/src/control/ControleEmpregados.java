package control;

import model.Empregado;
import view.*;

import java.util.ArrayList;

public class ControleEmpregados {

    private static int idSistemaGeral = 0;
    private static int idSindicatoGeral = 0;


    private ArrayList<Empregado> listaEmpregados;

    //CACHE
    // TODO MUDANCA NA FORMA DE ARMAZENAR CACHE NECESSARIA (ultimoEmpregadoUtilizado -> ultimoEmpregadoAdicionadoOuRemovido)
    private Empregado ultimoEmpregadoUtilizado;


    ControleEmpregados() {

        this.listaEmpregados = new ArrayList<>();
    }

    //GERAL
    private void listarEmpregados() {

        for(Empregado atual: listaEmpregados) {
            Console.mostrarString(atual.toString());
        }
    }

    private Empregado recuperarEmpregadoPorId() {

        int id;

        listarEmpregados();

        Console.solicitarId();
        id = Input.lerInt();

        for(Empregado atual: listaEmpregados) {
            if(atual.getIdSistema() == id) {
                return atual;
            }
        }
        Erro.idInvalido();
        return null;
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
        sindicalizado = Input.validarOperacaoBinaria();

        if(sindicalizado) {
            idSindicato = ++idSindicatoGeral;
            Console.solicitarTaxaSindicato();
            taxaSindicato = Input.validarPercentual();
        }

        Empregado novoEmpregado = new Empregado(nome, endereco, tipo, sindicalizado, taxaSindicato, salario, comissao, ++idSistemaGeral, idSindicato);
        ultimoEmpregadoUtilizado = novoEmpregado;
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
            Empregado empregadoDaLista = recuperarEmpregadoPorId();
            if(empregadoDaLista != null) {
                ultimoEmpregadoUtilizado = listaEmpregados.remove(listaEmpregados.indexOf(empregadoDaLista));
                Console.empregadoRemovido();
                return true;
            } else {
                return false;
            }
        }
    }


    //CARTAO DE PONTO
    public boolean registrarNoCartaoDePonto() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
            return false;
        } else {
            Empregado empregadoDaLista = recuperarEmpregadoPorId();

            if(empregadoDaLista != null && empregadoDaLista.eHorista()) {
                if(empregadoDaLista.eHorista()) {

                    Console.menuRegistrarNoCartaoDePonto();
                    boolean entrada = Input.validarOperacaoBinaria();

                    if(entrada) { //RGISTRO DE ENTRADA
                        for(Empregado atual: listaEmpregados) {
                            if(empregadoDaLista.equals(atual)) {
                                if(atual.criarCartaoDePonto()) {
                                    //TODO ADICIONAR EM CACHE EMPREGADO SEM O REGISTRO DE ENTRADA
                                    Console.entradaRegistrada();
                                    return true;
                                } else {
                                    Console.entradaJaRegistrada();
                                    return false;
                                }
                            }
                        }
                    } else {//RGISTRO DE SAIDA
                        for(Empregado atual: listaEmpregados) {
                            if(empregadoDaLista.equals(atual)) {
                                if(atual.registrarSaidaNoCartaoDePonto()) {
                                    //TODO ADICIONAR EM CACHE EMPREGADO SEM O REGISTRO DE SAIDA
                                    Console.saidaRegistrada();
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                } else {
                    Console.empregadoNaoHorista();
                    return false;
                }
            } else {
                return false;
            }
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
            case 3:
                if(desfazer) {
                    desfazerRegistrarNoCartaoDePonto();
                } else {
                    refazerRegistrarNoCartaoDePonto();
                }
                default:
                    return desfazer;
        }

        return !desfazer;
    }

    private void desfazerAdicionarEmpregado() {

        listaEmpregados.remove(ultimoEmpregadoUtilizado);
        Console.adicionarDesfeito();
    }

    private void refazerAdicionarEmpregado() {

        listaEmpregados.add(ultimoEmpregadoUtilizado);
        Console.adicionarRefeito();
    }

    private void desfazerRemoverEmpregado() {

        listaEmpregados.add(ultimoEmpregadoUtilizado);
        Console.removerDesfeito();
    }

    private void refazerRemoverEmpregado() {

        listaEmpregados.remove(ultimoEmpregadoUtilizado);
        Console.removerRefeito();
    }

    private void desfazerRegistrarNoCartaoDePonto() {

    }

    private void refazerRegistrarNoCartaoDePonto() {

    }
}
