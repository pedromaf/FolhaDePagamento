package model;

import java.util.Calendar;

public class Data {

    private int dia;
    private int mes;
    private int ano;

    private int hora;
    private int minuto;
    private int segundo;

    public Data() {

        Calendar calendario = Calendar.getInstance();

        this.dia = calendario.get(Calendar.DAY_OF_MONTH);
        this.mes = calendario.get(Calendar.MONTH);
        this.ano = calendario.get(Calendar.YEAR);
        this.hora = calendario.get(Calendar.HOUR);
        this.minuto = calendario.get(Calendar.MINUTE);
        this.segundo = calendario.get(Calendar.SECOND);
    }

    public boolean mesmoDia(Data data) {

        if(data != null) {
            return (data.getDia() == this.dia && data.getMes() == this.mes && data.getAno() == this.ano);
        }
        return false;
    }

    public int getDia() {

        return dia;
    }

    public int getMes() {

        return mes;
    }

    public int getAno() {

        return ano;
    }

    public int getHora() {

        return hora;
    }

    public int getMinuto() {

        return minuto;
    }

    public int getSegundo() {

        return segundo;
    }

    public String toString() {

        return this.dia + "/" + this.mes + "/" + this.ano + " " + this.hora + ":" + this.minuto + ":" + this.segundo + ".";
    }
}
