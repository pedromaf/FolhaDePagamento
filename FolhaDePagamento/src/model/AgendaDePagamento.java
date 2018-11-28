package model;

public class AgendaDePagamento {

    public enum TipoDePagamento {

        MENSAL, SEMANAL
    }

    private static int identificacaoGeral = 0;

    public static final int ULTIMO_DIA_UTIL = 0;

    private int identificacao;
    private Data ultimoPagamento;
    private Data proximoPagamento;
    private TipoDePagamento tipoDePagamento;
    private int diaDaSemana;/** SE tipoDePagamento SEMANAL, diaDaSemana = [1,7]
                                   SE tipoDePagamento MENSAL, diaDaSemana = 0**/
    private int frequencia; /**SE tipoDePagamento = MENSAL, frequencia REPRESENTA OS DIAS DO MES [0,31] (0 = ULTIMO_DIA_UTIL)
                               SE tipoDePagamento = SEMANAL, frequencia REPRESENTA A QUANTIDADE DE SEMANAS ENTRE OS PAGAMENTOS [1,4]**/

    public AgendaDePagamento() {}

    public AgendaDePagamento(TipoDePagamento tipoDePagamento, int diaDaSemana, int frequencia) {

        this.identificacao = ++identificacaoGeral;
        this.tipoDePagamento = tipoDePagamento;
        this.diaDaSemana = diaDaSemana;
        this.frequencia = frequencia;
        this.ultimoPagamento = new Data();
        calcularProximoPagamento();
    }

    public int getIdentificacao() {

        return this.identificacao;
    }

    public int getFrequencia() {

        return this.frequencia;
    }

    public Data getUltimoPagamento() {

        return this.ultimoPagamento;
    }

    public void setUltimoPagamento(Data novoUltimoPagamento) {

        this.ultimoPagamento = novoUltimoPagamento;
        calcularProximoPagamento();
    }

    public TipoDePagamento getTipoDePagamento() {

        return this.tipoDePagamento;
    }

    public void copiar(AgendaDePagamento agendaDePagamento) {

        if(agendaDePagamento != null) {

            agendaDePagamento.identificacao = this.identificacao;
            if(this.ultimoPagamento != null) {
                this.ultimoPagamento.copiar(agendaDePagamento.ultimoPagamento);
            }
            if(this.proximoPagamento != null) {
                this.proximoPagamento.copiar(agendaDePagamento.proximoPagamento);
            }
            agendaDePagamento.tipoDePagamento = this.tipoDePagamento;
            agendaDePagamento.diaDaSemana = this.diaDaSemana;
            agendaDePagamento.frequencia = this.frequencia;
        }
    }

    public void calcularProximoPagamento() {

        int mes = this.ultimoPagamento.getMes();
        int ano = this.ultimoPagamento.getAno();
        int dia = this.ultimoPagamento.getDia();
        int diaSemana = this.ultimoPagamento.getDiaDaSemana();

        if(this.tipoDePagamento == TipoDePagamento.MENSAL) {
            mes = incrementarVariavel(mes, 12);
            if(mes == 1) {
                ano++;
            }
            this.proximoPagamento = new Data(0,0, mes, ano);
        } else {
            for(int i = 7*this.frequencia; i > 0; i--) {
                diaSemana = incrementarVariavel(diaSemana, 7);

                if(Data.tem31Dias(mes)) {
                    dia = incrementarVariavel(dia, 31);
                } else if(Data.tem30Dias(mes)) {
                    dia = incrementarVariavel(dia, 30);
                } else {
                    dia = incrementarVariavel(dia, 28);
                }
                if(dia == 1) {
                    mes = incrementarVariavel(mes, 12);
                    if(mes == 1) {
                        ano++;
                    }
                }
                if(diaSemana == this.diaDaSemana && i < 8) {
                    break;
                }
            }
            this.proximoPagamento = new Data(diaSemana, dia, mes, ano);
        }
    }

    private int incrementarVariavel(int variavel, int limite) {

        if(variavel == limite) {
            return 1;
        }
        return ++variavel;
    }

    private String tipoDePagamentoString() {

        if(TipoDePagamento.MENSAL == this.tipoDePagamento) {
            return "Mensal";
        } else {
            return  "Semanal";
        }
    }

    private String diaDaSemanaString() {

        switch(this.diaDaSemana) {
            case 1:
                return "Domingo";
            case 2:
                return "Segunda";
            case 3:
                return "Terca";
            case 4:
                return "Quarta";
            case 5:
                return "Quinta";
            case 6:
                return "Sexta";
            case 7:
                return "Sabado";
            default:
                return "---";
        }
    }

    private String frequenciaString() {

        if(this.tipoDePagamento == TipoDePagamento.MENSAL) {
            return "Dia do mes: " + this.frequencia;
        } else {
            return "Pagamento a cada " + this.frequencia + ((this.frequencia > 1)?"Semanas":"Semana");
        }
    }

    public String toString() {

        return "Ultimo pagamento: " + this.ultimoPagamento.toString() + "\n" +
                "Proximo pagamento: " + this.proximoPagamento.toString() + "\n" +
                "Tipo de pagamento: " + tipoDePagamentoString() + "\n" +
                "Dia da semana: " + diaDaSemanaString() + "\n" +
                frequenciaString() + "\n";
    }

    public String descricao() {

        String descricao = "[" + this.identificacao + "]" + tipoDePagamentoString();
        if(this.tipoDePagamento == TipoDePagamento.MENSAL) {
            if(this.frequencia != ULTIMO_DIA_UTIL) {
                descricao += " pagamento dia " + this.frequencia + "\n";
            } else {
                descricao += " pagamento no ultimo dia util\n";
            }
        } else {
            descricao += " pagamento " + diaDaSemanaString() + " a cada " + this.frequencia + " semanas";
        }
        return descricao;
    }

    public boolean pagar(Data diaDePagamento) {

        if(this.proximoPagamento.mesmoDia(diaDePagamento)) {
            if(!this.ultimoPagamento.mesmoDia(diaDePagamento)) {
                Data auxiliar = proximoPagamento;
                calcularProximoPagamento();
                this.ultimoPagamento = auxiliar;
                return true;
            }
        }
        return false;
    }
}
