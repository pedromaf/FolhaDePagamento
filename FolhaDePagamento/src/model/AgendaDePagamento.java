package model;

public class AgendaDePagamento {

    public enum TipoDePagamento {

        MENSAL, SEMANAL
    }

    public static final int ULTIMO_DIA_UTIL = 0;

    private Data ultimoPagamento;
    private Data proximoPagamento;
    private TipoDePagamento tipoDePagamento;
    private String diaDaSemana;/** SE tipoDePagamento SEMANAL, diaDaSemana = {Dom, Seg, Qua, Qui, Sex, Sab}
                                   SE tipoDePagamento MENSAL, diaDaSemana = ---**/
    private int frequencia; /**SE tipoDePagamento = MENSAL, frequencia REPRESENTA OS DIAS DO MES [0,31] (0 = ULTIMO_DIA_UTIL)
                               SE tipoDePagamento = SEMANAL, frequencia REPRESENTA A QUANTIDADE DE SEMANAS ENTRE OS PAGAMENTOS [1,4]**/

    public AgendaDePagamento(TipoDePagamento tipoDePagamento, String diaDaSemana, int frequencia) {

        Data hoje = new Data();
        //TODO CALCULAR PRIMEIRO PAGAMENTO (proximoPagamento)
        this.tipoDePagamento = tipoDePagamento;
        this.diaDaSemana = diaDaSemana;
        this.frequencia = frequencia;

    }

    public void copiar(AgendaDePagamento agendaDePagamento) {

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
