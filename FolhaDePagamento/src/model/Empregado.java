package model;

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

        if(tipo == Tipo.HORISTA) {
            cartoesDePonto = new ArrayList<>();
            this.salarioHora = salario;
        } else if(tipo == Tipo.COMISSIONADO) {
            resultadosDeVendas = new ArrayList<>();
            this.salarioMensal = salario;
            this.comissao = comissao;
        } else {
            this.salarioMensal = salario;
        }

        if(sindicalizado) {
            taxasDeServico = new ArrayList<>();
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

    public int getIdSistema() {

        return this.idSistema;
    }

    public String toString() {

        return "[" + this.idSistema + "] " + this.nome;
    }
}
