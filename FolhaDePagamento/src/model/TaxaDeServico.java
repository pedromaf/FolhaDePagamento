package model;

public class TaxaDeServico {

    private double valor;
    private Data data;

    public TaxaDeServico(double valor, Data data) {

        this.valor = valor;
        this.data = data;
    }

    public TaxaDeServico(TaxaDeServico taxaDeServico) {

        this.valor = taxaDeServico.valor;
        this.data = taxaDeServico.data;
    }

    public void info() {

        System.out.println("-----------------------------");
        System.out.println("Valor: " + this.valor);
        System.out.println("Data: " + this.data.toString());
        System.out.println("-----------------------------");
    }
}
