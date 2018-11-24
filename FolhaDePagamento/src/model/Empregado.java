package model;

//O acesso ao modulo view por parte dessa classe nao deveria acontecer, mas por motivos de simplificacao de codigo foi necessario. gg
import view.Console;

import java.util.ArrayList;

public class Empregado {

    public enum FormaDePagamento {
        CHEQUE, CORREIO, BANCO
    }

    public enum Tipo {
        HORISTA, ASSALARIADO, COMISSIONADO
    }

    ArrayList<CartaoDePonto> cartoesDePonto;
    ArrayList<ResultadoDeVenda> resultadosDeVendas;
    ArrayList<TaxaDeServico> taxasDeServico;

    private int idSistema;
    private String nome;
    private String endereco;
    private Tipo tipo;
    private FormaDePagamento formaDePagamento;
    private double salarioHora;
    private double salarioMensal;
    private double comissao;
    private boolean sindicalizado;
    private int idSindicato;
    private double taxaSindicato;

    public Empregado(String nome, String endereco, Tipo tipo, boolean sindicalizado, double taxaSindicato, double salario, double comissao, int idSistema, int idSindicato) {

        taxasDeServico = new ArrayList<>();
        cartoesDePonto = new ArrayList<>();
        resultadosDeVendas = new ArrayList<>();

        if(tipo == Tipo.HORISTA) {
            this.salarioHora = salario;
        } else if(tipo == Tipo.COMISSIONADO) {
            this.salarioMensal = salario;
            this.comissao = comissao;
        } else {
            this.salarioMensal = salario;
        }

        if(sindicalizado) {
            this.taxaSindicato = taxaSindicato;
            this.idSindicato = idSindicato;
        }

        this.idSistema = idSistema;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.sindicalizado = sindicalizado;
        this.formaDePagamento = FormaDePagamento.BANCO; //PADRAO
    }

    public Empregado(Empregado empregado) {

        this.cartoesDePonto = new ArrayList<>();
        this.resultadosDeVendas = new ArrayList<>();
        this.taxasDeServico = new ArrayList<>();

        copiarCartoesDePonto(this.cartoesDePonto, empregado.cartoesDePonto);
        copiarResultadosDeVenda(this.resultadosDeVendas, empregado.resultadosDeVendas);
        copiarTaxasDeServico(this.taxasDeServico, empregado.taxasDeServico);

        this.idSistema = empregado.idSistema;
        this.nome = empregado.nome;
        this.endereco = empregado.endereco;
        this.tipo = empregado.tipo;
        this.formaDePagamento = empregado.formaDePagamento;
        this.salarioHora = empregado.salarioHora;
        this.salarioMensal = empregado.salarioMensal;
        this.comissao = empregado.comissao;
        this.sindicalizado = empregado.sindicalizado;
        this.idSindicato = empregado.idSindicato;
        this.taxaSindicato = empregado.taxaSindicato;
    }

    private void copiarCartoesDePonto(ArrayList<CartaoDePonto> novaLista, ArrayList<CartaoDePonto> lista) {

        if(lista != null) {
            for(CartaoDePonto atual: lista) {
                novaLista.add(new CartaoDePonto(atual));
            }
        }
    }

    private void copiarResultadosDeVenda(ArrayList<ResultadoDeVenda> novaLista, ArrayList<ResultadoDeVenda> lista) {

        if(lista != null) {
            for(ResultadoDeVenda atual: lista) {
                novaLista.add(new ResultadoDeVenda(atual));
            }
        }
    }

    private void copiarTaxasDeServico(ArrayList<TaxaDeServico> novaLista, ArrayList<TaxaDeServico> lista) {

        if(lista != null) {
            for(TaxaDeServico atual: lista) {
                novaLista.add(new TaxaDeServico(atual));
            }
        }
    }


    //CARTAO DE PONTO
    public boolean criarCartaoDePonto() {

        if(cartoesDePonto != null) {
            Data dataAtual = new Data();

            for(CartaoDePonto atual : cartoesDePonto) {
                if(dataAtual.mesmoDia(atual.getEntrada())) {
                    return false;
                }
            }

            CartaoDePonto cartaoDePonto = new CartaoDePonto();
            cartaoDePonto.registrarEntrada(dataAtual);
            cartoesDePonto.add(cartaoDePonto);
            return true;
        }
        return false;
    }

    public boolean registrarSaidaNoCartaoDePonto() {

        if(cartoesDePonto != null) {
            Data dataAtual = new Data();

            for(CartaoDePonto atual : cartoesDePonto) {
                if(dataAtual.mesmoDia(atual.getEntrada())) {
                    if(atual.getSaida() == null) {
                        atual.registrarSaida(dataAtual);
                        return true;
                    }
                    Console.saidaJaRegistrada();
                    return false;
                }
            }
            Console.entradaAindaNaoRegistrada();
            return false;
        }
        return false;
    }


    //RESULTADO DE VENDA
    public void adicionarResultadoDeVenda(double valorVenda) {

        Data dataAtual = new Data();
        resultadosDeVendas.add(new ResultadoDeVenda(valorVenda, dataAtual));
    }



    //GERAL
    public int getIdSistema() {

        return this.idSistema;
    }

    private String stringTipo() {

        if(this.tipo == Tipo.ASSALARIADO) {
            return "Assalariado";
        } else if(this.tipo == Tipo.HORISTA) {
            return "Horista";
        } else {
            return "Comissionado";
        }
    }

    public String toString() {

        return "[" + this.idSistema + "] " + this.nome + " (" + stringTipo() + ")";
    }

    public boolean eHorista() {

        return (this.tipo == Tipo.HORISTA);
    }

    public boolean eComissionado() {

        return (this.tipo == Tipo.COMISSIONADO);

    }


    //RELATORIO (A FUNCAO RELATORIO NAO ESTA DE ACORDO COM A ORGANIZACAO MVC POR QUESTOES DE TEMPO)
    public void infoRelatorio() {

        System.out.println("[" + this.idSistema + "] " + this.nome + " (" + stringTipo() + ")");
        infoCartoesDePonto();
        infoResultadoDeVendas();
        infoTaxasDeServico();
        System.out.println();
    }

    private void infoCartoesDePonto() {

        System.out.println("    Cartoes de ponto:");
        if(cartoesDePonto != null) {
            if(!cartoesDePonto.isEmpty()) {
                for(CartaoDePonto atual: cartoesDePonto) {
                    atual.info();
                }
            } else {
                System.out.println("    Nenhum.");
            }
        }
    }

    private void infoResultadoDeVendas() {

        System.out.println("    Resultados de vendas:");
        if(resultadosDeVendas != null) {
            if(!resultadosDeVendas.isEmpty()) {
                for(ResultadoDeVenda atual: resultadosDeVendas) {
                    atual.info();
                }
            } else {
                System.out.println("    Nenhum.");
            }
        }
    }

    private void infoTaxasDeServico() {

        System.out.println("    Taxas de servico:");
        if(taxasDeServico != null) {
            if(!taxasDeServico.isEmpty()) {
                for(TaxaDeServico atual: taxasDeServico) {
                    atual.info();
                }
            } else {
                System.out.println("    Nenhum.");
            }
        }
    }
}
