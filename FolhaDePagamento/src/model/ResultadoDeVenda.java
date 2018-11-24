package model;

public class ResultadoDeVenda {

    private double valor;
    private Data data;

    public ResultadoDeVenda(double valor, Data data) {

        this.valor = valor;
        this.data = data;
    }

    public ResultadoDeVenda(ResultadoDeVenda resultadoDeVenda) {

        this.valor = resultadoDeVenda.valor;
        this.data = resultadoDeVenda.data;
    }

    public void info() {

        System.out.println("-----------------------------");
        System.out.println("Valor: " + this.valor);
        System.out.println("Data: " + this.data.toString());
        System.out.println("-----------------------------");
    }
}
