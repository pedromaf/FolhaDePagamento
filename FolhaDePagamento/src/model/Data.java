package model;

import java.util.Calendar;

public class Data {

    private int diaDaSemana;//DOMINGO = 1 -> SABADO = 7

    private int dia;
    private int mes;
    private int ano;

    private int hora;
    private int minuto;
    private int segundo;

    public Data() {

        Calendar calendario = Calendar.getInstance();

        this.diaDaSemana = calendario.get(Calendar.DAY_OF_WEEK);
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

    public String getDiaDaSemanaString() {

        switch(this.diaDaSemana) {
            case 1:
                return "Dom";
            case 2:
                return "Seg";
            case 3:
                return "Ter";
            case 4:
                return "Qua";
            case 5:
                return "Qui";
            case 6:
                return "Sex";
            case 7:
                return "Sab";
            default:
                return "---";
        }
    }

    public int getDiaDaSemana() {

        return this.diaDaSemana;
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

    public void copiar(Data data) {

        data.diaDaSemana = this.diaDaSemana;
        data.dia = this.dia;
        data.mes = this.mes;
        data.ano = this.ano;
        data.hora = this.hora;
        data.minuto = this.minuto;
        data.segundo = this.segundo;
    }

    public String toString() {

        return getDiaDaSemanaString() + " " + this.dia + "/" + this.mes + "/" + this.ano + " " + this.hora + ":" + this.minuto + ":" + this.segundo + ".";
    }
}
