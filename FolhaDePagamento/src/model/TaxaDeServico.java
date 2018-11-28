package model;

public class TaxaDeServico {

    private boolean descontado;
    private double valor;
    private Data data;

    public TaxaDeServico(double valor, Data data) {

        this.descontado = false;
        this.valor = valor;
        this.data = data;
    }

    public TaxaDeServico(TaxaDeServico taxaDeServico) {

        this.descontado = taxaDeServico.descontado;
        this.valor = taxaDeServico.valor;
        this.data = taxaDeServico.data;
    }

    public double getValor() {

        return this.valor;
    }

    public Data getData() {

        return this.data;
    }

    public boolean descontar() {

        if(!descontado) {
            descontado = true;
            return true;
        }
        return false;
    }

    public void info() {

        System.out.println("-----------------------------");
        if(this.descontado) {
            System.out.println("[PAGO]\n");
        }
        System.out.println("Valor: " + this.valor);
        System.out.println("Data: " + this.data.toString());
        System.out.println("-----------------------------");
    }
}
