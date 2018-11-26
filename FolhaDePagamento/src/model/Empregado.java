package model;

import control.Input;
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

    private AgendaDePagamento agendaDePagamento;
    private double saldo;

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
            this.salarioMensal = 0;
            this.agendaDePagamento = new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.SEMANAL, "Sex", 1);
        } else if(tipo == Tipo.COMISSIONADO) {
            this.salarioMensal = salario;
            this.salarioHora = 0;
            this.comissao = comissao;
            this.agendaDePagamento = new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.SEMANAL, "Sex", 2);
        } else {
            this.salarioHora = 0;
            this.comissao = 0;
            this.salarioMensal = salario;
            this.agendaDePagamento = new AgendaDePagamento(AgendaDePagamento.TipoDePagamento.MENSAL, "---", AgendaDePagamento.ULTIMO_DIA_UTIL);
        }

        if(sindicalizado) {
            this.taxaSindicato = taxaSindicato;
            this.idSindicato = idSindicato;
        }

        this.idSistema = idSistema;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.saldo = 0;
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
        empregado.agendaDePagamento.copiar(this.agendaDePagamento);
        this.saldo = empregado.saldo;
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



    //SET
    public void setNome(String novoNome) {

        this.nome = novoNome;
    }

    public void setEndereco(String novoEndereco) {

        this.endereco = novoEndereco;
    }

    public void setTipo(Tipo novoTipo) {

        this.tipo = novoTipo;
    }

    public void setFormaDePagamento(FormaDePagamento novaFormaDePagamento) {

        this.formaDePagamento = novaFormaDePagamento;
    }

    public void setSalarioMensal(double novoSalarioMensal) {

        this.salarioMensal = novoSalarioMensal;
        this.salarioHora = 0;
    }

    public void setSalarioHora(double novoSalarioHora) {

        this.salarioHora = novoSalarioHora;
        this.salarioMensal = 0;
    }

    public void setComissao(double novaComissao) {

        this.comissao = novaComissao;
    }

    public void setIdSindicato(int novoIdSindicato) {

        this.idSindicato = novoIdSindicato;
    }

    public void setTaxaSindical(double novaTaxaSindical) {

        this.taxaSindicato = novaTaxaSindical;
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



    //TAXA DE SERVICO
    public void adicionarTaxaDeServico(double valorTaxa) {

        Data dataAtual = new Data();
        taxasDeServico.add(new TaxaDeServico(valorTaxa, dataAtual));
    }



    //ALTERAR DADOS
    public void alterarTipo(Tipo novoTipo) {

        double novoSalario;

        this.setTipo(novoTipo);

        switch(novoTipo) {
            case ASSALARIADO:
                Console.solicitarSalarioMensal();
                novoSalario = Input.lerDouble();

                this.setSalarioMensal(novoSalario);
                break;
            case HORISTA:
                Console.solicitarSalarioHora();
                novoSalario = Input.lerDouble();

                this.setSalarioHora(novoSalario);
                break;
            case COMISSIONADO:
                double comissao;

                Console.solicitarComissao();
                comissao = Input.validarPercentual();

                Console.solicitarSalarioMensal();
                novoSalario = Input.lerDouble();

                this.setSalarioMensal(novoSalario);
                this.setComissao(comissao);
        }
    }

    public void alterarSindicalizado(int idSindicato) {

        if(this.sindicalizado) {
            this.sindicalizado = false;
            this.taxaSindicato = 0;
            Console.desassociouSindicato();
        } else {
            this.sindicalizado = true;
            this.idSindicato = idSindicato;

            Console.associouSindicato();
            Console.solicitarValorDaTaxaDeServico();
            this.taxaSindicato = Input.validarPercentual();
        }
    }



    //FOLHA DE PAGAMENTO
    //TODO
    public void pagar(Data hoje) {


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

        return "[" + this.idSistema + "] " + this.nome + " (" + stringTipo() + ")" + ((this.sindicalizado)?" {Sindicalizado}":"");
    }

    public boolean eHorista() {

        return (this.tipo == Tipo.HORISTA);
    }

    public boolean eComissionado() {

        return (this.tipo == Tipo.COMISSIONADO);

    }

    public boolean eSindicalizado() {

        return sindicalizado;
    }



    //RELATORIO (A FUNCAO RELATORIO NAO ESTA DE ACORDO COM A ORGANIZACAO MVC POR QUESTOES DE TEMPO)
    public void infoRelatorio() {

        System.out.println(this.toString());
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

