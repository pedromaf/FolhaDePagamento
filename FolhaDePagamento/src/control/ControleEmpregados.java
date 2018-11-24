package control;

import model.Empregado;
import view.*;

import java.util.ArrayList;

public class ControleEmpregados {

    private static int idSistemaGeral = 0;
    private static int idSindicatoGeral = 0;


    private ArrayList<Empregado> listaEmpregados;

    //CACHE
    private Empregado ultimoEmpregadoUtilizado;
    private Empregado ultimoEmpregadoUtilizadoAuxiliar;

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
        ultimoEmpregadoUtilizado =  new Empregado(novoEmpregado);
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
                ultimoEmpregadoUtilizado = new Empregado(empregadoDaLista);
                listaEmpregados.remove(empregadoDaLista);
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

            if(empregadoDaLista != null) {
                if(empregadoDaLista.eHorista()) {

                    ultimoEmpregadoUtilizadoAuxiliar = new Empregado(empregadoDaLista); //CACHE TEMPORARIO
                    Console.menuRegistrarNoCartaoDePonto();
                    boolean entrada = Input.validarOperacaoBinaria();

                    if(entrada) { //RGISTRO DE ENTRADA
                        if(empregadoDaLista.criarCartaoDePonto()) {
                            ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
                            Console.entradaRegistrada();
                            return true;
                        } else {
                            Console.entradaJaRegistrada();
                            return false;
                        }
                    } else {//RGISTRO DE SAIDA
                        if(empregadoDaLista.registrarSaidaNoCartaoDePonto()) {
                            ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
                            Console.saidaRegistrada();
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    Console.empregadoNaoHorista();
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    //RESULTADO DE VENDA
    public boolean registrarResultadoDeVenda() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
            return false;
        } else {
            Empregado empregadoDaLista = recuperarEmpregadoPorId();

            if(empregadoDaLista != null) {
                if(empregadoDaLista.eComissionado()) {

                    double valorVenda;

                    ultimoEmpregadoUtilizadoAuxiliar = new Empregado(empregadoDaLista); //CACHE TEMPORARIO

                    Console.solicitarValorDaVenda();
                    valorVenda = Input.lerDouble();

                    empregadoDaLista.adicionarResultadoDeVenda(valorVenda);
                    Console.resultadoDeVendaRegistrado();
                    return true;
                } else {
                    Console.empregadoNaoComissionado();
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
                    removerEmpregadoNovamente();
                    Console.adicionarDesfeito();
                } else {
                    adicionarEmpregadoNovamente();
                    Console.adicionarRefeito();
                }
                break;
            case 2:
                if(desfazer) {
                    adicionarEmpregadoNovamente();
                    Console.removerDesfeito();
                } else {
                    removerEmpregadoNovamente();
                    Console.removerRefeito();
                }
                break;
            case 3:
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.registroNoCartaoDePontoDesfeito();
                } else {
                    Console.registroNoCartaoDePontoRefeito();
                }
                break;
            case 4:
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.registroResultadoDeVendaDesfeito();
                } else {
                    Console.registroResultadoDeVendaRefeito();
                }
                break;
                default:
                    return desfazer;
        }

        return !desfazer;
    }

    private void adicionarEmpregadoNovamente() {

        listaEmpregados.add(new Empregado(ultimoEmpregadoUtilizado));
    }

    private void removerEmpregadoNovamente() {

        for(Empregado atual: listaEmpregados) {
            if(ultimoEmpregadoUtilizado.getIdSistema() == atual.getIdSistema()) {
                listaEmpregados.remove(atual);
                break;
            }
        }
    }

    private void desfazerOuRefazerRegistroDeInformacao() {

        for(Empregado atual: listaEmpregados) {
            if(ultimoEmpregadoUtilizado.getIdSistema() == atual.getIdSistema()) {
                ultimoEmpregadoUtilizadoAuxiliar = new Empregado(atual);
                listaEmpregados.remove(atual);
                listaEmpregados.add(new Empregado(ultimoEmpregadoUtilizado));
                ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
                break;
            }
        }
    }


    //RELATORIO EMPREGADOS
    public void relatorio() {

        System.out.println("LISTA DE EMPREGADOS:");
        if(!listaEmpregados.isEmpty()) {
            for(Empregado atual: listaEmpregados) {
                atual.infoRelatorio();
            }
        } else {
            System.out.println("    Vazia.");
        }
    }
}
