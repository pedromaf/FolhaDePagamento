package model;

public class CartaoDePonto {

    private Data entrada;
    private Data saida;

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


}
