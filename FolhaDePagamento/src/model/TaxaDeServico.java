package model;

public class TaxaDeServico {

    private double percentual;
    private Data data;

    public TaxaDeServico() {}

    public TaxaDeServico(TaxaDeServico taxaDeServico) {

        this.percentual = taxaDeServico.percentual;
        this.data = taxaDeServico.data;
    }

    public void info() {

        System.out.println("-----------------------------");
        System.out.println("Valor: " + this.percentual);
        System.out.println("Data: " + this.data.toString());
        System.out.println("-----------------------------");
    }
}
