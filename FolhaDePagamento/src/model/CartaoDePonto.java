package model;

public class CartaoDePonto {

    private boolean descontado;
    private Data entrada;
    private Data saida;

    public CartaoDePonto() {

        this.descontado = false;
    }

    public CartaoDePonto(CartaoDePonto cartaoDePonto) {

        this.descontado = cartaoDePonto.descontado;
        this.entrada = cartaoDePonto.entrada;
        this.saida = cartaoDePonto.saida;
    }

    public double horasTrabalhadas() {

        if(this.saida != null) {
            int hora = this.saida.getHora() - this.entrada.getHora();
            int minuto = this.saida.getMinuto() - this.entrada.getMinuto();

            if(minuto < 0) {
                hora -= 1;
                minuto = 60 - minuto;
            }

            return (((double)minuto/60) + hora);
        } else {
            return 0;
        }
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
        System.out.println("Entrada: " + entrada.toString());
        if(this.saida != null) {
            System.out.println("Saida: " + saida.toString());
        } else {
            System.out.println("Saida: NÃO REGISTRADA");
        }
        System.out.println("-----------------------------");
    }
}
