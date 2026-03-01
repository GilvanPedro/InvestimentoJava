package br.com.ControleInvestimento.modal.entity;

public enum TipoInvestimento{

    TESOURO_DIRETO("Empréstimo que você faz ao governo. Tem baixo risco e pode ser pós-fixado (Selic), prefixado ou atrelado à inflação (IPCA)"),

    CDB("Você empresta dinheiro ao banco e recebe juros em troca. Geralmente rende um percentual do CDI."),

    LCI_LCA("Semelhantes ao CDB, mas voltadas ao setor imobiliário (LCI) e agrícola (LCA). São isentas de Imposto de Renda para pessoa física."),

    DEBENTURES("Você empresta dinheiro para empresas. Podem pagar juros maiores, mas têm risco maior que títulos bancários."),

    ACOES("Você compra uma pequena parte de uma empresa (como Petrobras ou Vale). O lucro vem da valorização e dos dividendos."),

    FLLS("Fundos que investem em imóveis (shoppings, galpões, escritórios). Costumam pagar rendimentos mensais."),

    ETFS("Fundos negociados em bolsa que replicam índices de mercado, como os listados na B3. Permitem diversificação com uma única compra."),

    CRIPTOMOEDA("Ativo digital que funciona como dinheiro virtual e utiliza criptografia para garantir segurança nas transações."),

    IMOVEL_FISICO("Compra de casas, apartamentos ou terrenos para aluguel ou revenda."),

    OURO("Metal precioso usado como reserva de valor."),

    OUTRO("Algum outro investimeto que não está aqui");

    private String descricao;

    TipoInvestimento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
