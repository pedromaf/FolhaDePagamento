package control;

import model.AgendaDePagamento;
import model.Data;
import model.Empregado;
import view.*;

import java.util.ArrayList;

public class ControleEmpregados {

    private static int idSistemaGeral = 0;
    private static int idSindicatoGeral = 0;


    private ArrayList<Empregado> listaEmpregados;
    private ArrayList<AgendaDePagamento> listaAgendasDePagamento;
    private Data ultimoDiaDePagamento;

    //CACHE
    private Data ultimoDiaDePagamentoAuxiliar;
    private ArrayList<Empregado> ultimaListaEmpregados;
    private Empregado ultimoEmpregadoUtilizado;
    private Empregado ultimoEmpregadoUtilizadoAuxiliar;

    ControleEmpregados() {

        this.listaEmpregados = new ArrayList<>();
        this.ultimaListaEmpregados = new ArrayList<>();
        this.listaAgendasDePagamento = new ArrayList<>();

        //INICIALIZAR AGENDAS DE PAGAMENTO PADRAO
        listaAgendasDePagamento.add(new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.SEMANAL, 6, 1));                      //HORISTA PADRAO [1]
        listaAgendasDePagamento.add(new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.SEMANAL, 6, 2));                      //COMISSIONADO PADRAO [2]
        listaAgendasDePagamento.add(new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.MENSAL, 0, AgendaDePagamento.ULTIMO_DIA_UTIL)); //ASSALARIADO PADRAO [3]
    }

    //GERAL
    private void listarEmpregados() {

        for(Empregado atual: listaEmpregados) {
            Console.mostrarString(atual.toString());
        }
    }

    private void listarAgendasDePagamento() {

        for(AgendaDePagamento atual: listaAgendasDePagamento) {
            Console.mostrarString(atual.descricao());
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

    private AgendaDePagamento recuperarAgendaDePagamentoPorId() {

        int id;

        listarAgendasDePagamento();

        Console.solicitarIdentificacao();
        id = Input.lerInt();

        AgendaDePagamento agendaDePagamento = getAgendaDePagamento(id);
        if(agendaDePagamento == null) {
            Erro.identificacaoInvalida();
        }
        return agendaDePagamento;
    }

    private AgendaDePagamento getAgendaDePagamento(int identificacao) {

        for(AgendaDePagamento atual: listaAgendasDePagamento) {
            if(atual.getIdentificacao() == identificacao) {
                AgendaDePagamento agendaDePagamento = new AgendaDePagamento();
                atual.copiar(agendaDePagamento);
                return agendaDePagamento;
            }
        }
        return null;
    }


    //ADICIONAR
    public boolean adicionarEmpregado() {

        String nome;
        String endereco;
        Empregado.Tipo tipo;
        AgendaDePagamento agendaDePagamento;
        double salario;
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
                agendaDePagamento = getAgendaDePagamento(1);
                break;
            case COMISSIONADO:
                Console.solicitarSalarioMensal();
                salario = Input.lerDouble();
                Console.solicitarComissao();
                comissao = Input.validarPercentual();
                agendaDePagamento = getAgendaDePagamento(2);
                break;
            case ASSALARIADO:
            default:
                Console.solicitarSalarioMensal();
                salario = Input.lerDouble();
                agendaDePagamento = getAgendaDePagamento(3);
        }
        System.out.println("OXE");
        agendaDePagamento.gerarPrimeiroPagamento();

        Console.menuSindicalizado();
        sindicalizado = Input.validarOperacaoBinaria();

        if(sindicalizado) {
            idSindicato = ++idSindicatoGeral;
            Console.solicitarTaxaSindical();
            taxaSindicato = Input.lerDouble();
        }

        Empregado novoEmpregado = new Empregado(nome, endereco, tipo, agendaDePagamento, sindicalizado, taxaSindicato, salario, comissao, ++idSistemaGeral, idSindicato);
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
                    ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
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


    //TAXA DE SERVICO
    public boolean registrarTaxaDeServico() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
            return false;
        } else {
            Empregado empregadoDaLista = recuperarEmpregadoPorId();

            if(empregadoDaLista != null) {
                if(empregadoDaLista.eSindicalizado()) {

                    double valorTaxa;

                    ultimoEmpregadoUtilizadoAuxiliar = new Empregado(empregadoDaLista); //CACHE TEMPORARIO

                    Console.solicitarValorDaTaxaDeServico();
                    valorTaxa = Input.lerDouble();

                    empregadoDaLista.adicionarTaxaDeServico(valorTaxa);
                    ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
                    Console.taxaDeServicoRegistrada();
                    return true;
                } else {
                    Console.empregadoNaoSindicalizado();
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    //ALTERAR DADOS DO EMPREGADO
    public boolean alterarDadosDoEmpregado() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
            return false;
        } else {
            Empregado empregadoDaLista = recuperarEmpregadoPorId();
            if(empregadoDaLista != null) {
                ultimoEmpregadoUtilizadoAuxiliar = new Empregado(empregadoDaLista);//CACHE TEMPORARIO

                int operacao;

                Console.menuAlterarDados();
                operacao = Input.validarOperacao(1,6);

                switch(operacao) {
                    case 1:
                        alterarNome(empregadoDaLista);
                        break;
                    case 2:
                        alterarEndereco(empregadoDaLista);
                        break;
                    case 3:
                        alterarTipo(empregadoDaLista);
                        break;
                    case 4:
                        alterarFormaDePagamento(empregadoDaLista);
                        break;
                    case 5:
                        if(!alterarInformacoesSindicato(empregadoDaLista)) {
                            return false;
                        }
                        break;
                    case 6:
                    default:
                        return false;
                }
                ultimoEmpregadoUtilizado = ultimoEmpregadoUtilizadoAuxiliar;
                return true;
            } else {
                return false;
            }

        }
    }

    private void alterarNome(Empregado empregado) {

        String novoNome;

        Console.solicitarNome();
        novoNome = Input.lerString();

        empregado.setNome(novoNome);
        Console.nomeAlterado();
    }

    private void alterarEndereco(Empregado empregado) {

        String novoEndereco;

        Console.solicitarEndereco();
        novoEndereco = Input.lerString();

        empregado.setEndereco(novoEndereco);
        Console.enderecoAlterado();
    }

    private void alterarTipo(Empregado empregado) {

        Empregado.Tipo novoTipo;

        Console.solicitarEndereco();
        novoTipo = Input.lerTipoEmpregado();

        AgendaDePagamento novaAgendaDePagamento;

        switch(novoTipo) {
            case HORISTA:
                novaAgendaDePagamento = getAgendaDePagamento(1);
                break;
            case COMISSIONADO:
                novaAgendaDePagamento = getAgendaDePagamento(2);
                break;
            case ASSALARIADO:
            default:
                novaAgendaDePagamento = getAgendaDePagamento(3);
        }

        empregado.alterarTipo(novoTipo, novaAgendaDePagamento);
        Console.tipoAlterado();
    }

    private void alterarFormaDePagamento(Empregado empregado){

        Empregado.FormaDePagamento novaFormaDePagamento;

        Console.menuFormaDePagamento();
        novaFormaDePagamento = Input.lerFormaDePagamento();

        empregado.setFormaDePagamento(novaFormaDePagamento);
        Console.formaDePagamentoAlterada();
    }

    private boolean alterarInformacoesSindicato(Empregado empregado) {

        int operacao;

        Console.menuInformacoesDoSindicato();
        operacao = Input.validarOperacao(1,3);

        switch(operacao) {
            case 1:
                empregado.alterarSindicalizado(++idSindicatoGeral);
                Console.associacaoSindicalAlterada();
                break;
            case 2:
                if(empregado.eSindicalizado()) {
                    empregado.setIdSindicato(++idSindicatoGeral);
                    Console.identificacaoSindicalAlterada();
                } else {
                    Console.empregadoNaoSindicalizado();
                    return false;
                }
                break;
            case 3:
                if(empregado.eSindicalizado()) {
                    double novaTaxaSindical;
                    Console.solicitarTaxaSindical();
                    novaTaxaSindical = Input.validarPercentual();
                    empregado.setTaxaSindical(novaTaxaSindical);
                    Console.taxaSindicalAlterada();
                } else {
                    Console.empregadoNaoSindicalizado();
                    return false;
                }
        }

        return true;
    }


    //FOLHA DE PAGAMENTO
    public boolean rodarFolhaDePagamento() {

        if(listaEmpregados != null) {
            if(listaEmpregados.isEmpty()) {
                Console.listaVazia();
                return false;
            } else {
                Data hoje = new Data();
                if(ultimoDiaDePagamento != null && ultimoDiaDePagamento.mesmoDia(hoje)) {
                    Console.empregadosJaForamPagosHoje();
                } else {
                    ultimoDiaDePagamentoAuxiliar = ultimoDiaDePagamento;
                    ultimoDiaDePagamento = hoje;
                    if(!ultimaListaEmpregados.isEmpty()) {
                        ultimaListaEmpregados.clear();
                    }
                    for(Empregado atual: listaEmpregados) {
                        ultimaListaEmpregados.add(new Empregado(atual));
                        atual.efetuarPagamento(hoje);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //DESFAZER/REFAZER
    public boolean desfazerRefazer(int ultimaOperacao, boolean desfazer) {

        switch(ultimaOperacao) {
            case 0://NENHUMA OPERACAO REALIZADA PREVIAMENTE
                Console.operacaoNaoRealizada();
                return desfazer;
            case 1: //ADICIONAR EMPREGADO
                if(desfazer) {
                    removerEmpregadoNovamente();
                    Console.adicionarDesfeito();
                } else {
                    adicionarEmpregadoNovamente();
                    Console.adicionarRefeito();
                }
                break;
            case 2://REMOVER EMPREGADO
                if(desfazer) {
                    adicionarEmpregadoNovamente();
                    Console.removerDesfeito();
                } else {
                    removerEmpregadoNovamente();
                    Console.removerRefeito();
                }
                break;
            case 3://REGISTRO CARTAO DE PONTO
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.registroNoCartaoDePontoDesfeito();
                } else {
                    Console.registroNoCartaoDePontoRefeito();
                }
                break;
            case 4://REGISTRO RESULTADO DE VENDA
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.registroResultadoDeVendaDesfeito();
                } else {
                    Console.registroResultadoDeVendaRefeito();
                }
                break;
            case 5://REGISTRO TAXA DE SERVICO
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.registroTaxaDeServicoDesfeito();
                } else {
                    Console.registroTaxaDeServicoRefeito();
                }
                break;
            case 6://ALTERAR INFORMACAO DO EMPREGADO
                desfazerOuRefazerRegistroDeInformacao();
                if(desfazer) {
                    Console.alteracaoDeDadosDesfeita();
                } else {
                    Console.alteracaoDeDadosRefeita();
                }
                break;
            case 7: //FOLHA DE PAGAMENTO
                desfazerOuRefazerFolhaDePagamento();
                if(desfazer) {
                    Console.folhaDePagamentoDesfeita();
                } else {
                    Console.folhaDePagamentoRefeita();
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

    private void desfazerOuRefazerFolhaDePagamento() {

        Data auxiliar = ultimoDiaDePagamento;
        ultimoDiaDePagamento = ultimoDiaDePagamentoAuxiliar;
        ultimoDiaDePagamentoAuxiliar = auxiliar;

        ArrayList<Empregado> listaAuxiliar = new ArrayList<>(ultimaListaEmpregados);
        ultimaListaEmpregados.clear();

        for(Empregado atualAuxiliar: listaAuxiliar) {

                for(Empregado atualLista: listaEmpregados) {

                    if(atualAuxiliar.getIdSistema() == atualLista.getIdSistema()) {
                        ultimaListaEmpregados.add(atualLista);
                        listaEmpregados.remove(atualLista);
                        break;
                    }
                }
                listaEmpregados.add(atualAuxiliar);
        }
    }


    //AGENDA DE PAGAMENTO
    public void alterarAgendaDePagamento() {

        if(listaEmpregados.isEmpty()) {
            Console.listaVazia();
        } else {
            Empregado empregadoDaLista = recuperarEmpregadoPorId();
            if(empregadoDaLista != null) {
                AgendaDePagamento agendaDePagamento = recuperarAgendaDePagamentoPorId();
                if(!empregadoDaLista.setAgendaDePagamento(agendaDePagamento)) {
                    Console.agendaDePagamentoIncompativel();
                }
            }
        }
    }

    public void criarAgendaDePagamento() {

        Console.menuCriarAgendaDePagamento();
        boolean mensal = Input.validarOperacaoBinaria();
        int frequencia;
        int diaDaSemana = 0;

        if(mensal) {
            Console.solicitarDiaDoPagamento();
            frequencia = Input.validarOperacao(0, 31);
            listaAgendasDePagamento.add(new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.MENSAL, 0, frequencia));
        } else {
            Console.solicitarIntervaloSemanal();
            frequencia = Input.validarOperacao(1, 4);
            Console.solicitarDiaDaSemana();
            diaDaSemana = Input.validarOperacao(1,7);
            listaAgendasDePagamento.add(new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.SEMANAL, diaDaSemana, frequencia));
        }

        Console.agendaDePagamentoCriada();
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
