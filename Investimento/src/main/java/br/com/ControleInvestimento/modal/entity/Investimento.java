package br.com.ControleInvestimento.modal.entity;

public class Investimento {
    private long id;
    private String descricao;
    private TipoInvestimento tipoInvestimento;
    private double valor;
    private boolean internacional;

    public Investimento(long id, String descricao, TipoInvestimento tipoInvestimento, double valor, boolean internacional) {
        this.id = id;
        this.descricao = descricao;
        this.tipoInvestimento = tipoInvestimento;
        this.valor = valor;
        this.internacional = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    public double getValor() {
        return valor;
    }

    public boolean isInternacional() {
        return internacional;
    }

    public long getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipoInvestimento(TipoInvestimento tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return """
            -------- INVESTIMENTO --------
            ID: %d
            Descrição: %s
            Tipo: %s
            Valor: R$ %.2f
            Internacional: %s
            ------------------------------
            """.formatted(
                id,
                descricao,
                tipoInvestimento,
                valor,
                internacional ? "Sim" : "Não"
        );
    }
}
