package model;

public class ResultadoDeVenda {

    private boolean descontado;
    private double valor;
    private Data data;

    public ResultadoDeVenda(double valor, Data data) {

        this.descontado = false;
        this.valor = valor;
        this.data = data;
    }

    public ResultadoDeVenda(ResultadoDeVenda resultadoDeVenda) {

        this.descontado = resultadoDeVenda.descontado;
        this.valor = resultadoDeVenda.valor;
        this.data = resultadoDeVenda.data;
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
