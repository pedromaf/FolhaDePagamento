package model;

public class CartaoDePonto {

    private Data entrada;
    private Data saida;

    public CartaoDePonto() {}

    public CartaoDePonto(CartaoDePonto cartaoDePonto) {

        this.entrada = cartaoDePonto.entrada;
        this.saida = cartaoDePonto.saida;
    }

    public void registrarEntrada(Data entrada) {

        if(this.entrada == null) {
            this.entrada = entrada;
        }
    }

    public void registrarSaida(Data saida) {

        if(this.saida == null) {
            this.saida = saida;
        }
    }

    public Data getEntrada() {

        return this.entrada;
    }

    public Data getSaida() {

        return this.saida;
    }

    public void info() {

        System.out.println("-----------------------------");
        System.out.println("Entrada: " + entrada.toString());
        if(this.saida != null) {
            System.out.println("Saida: " + saida.toString());
        } else {
            System.out.println("Saida: NÃO REGISTRADA");
        }
        System.out.println("-----------------------------");
    }
}
