package lib.rocigno.usefulthingslib.CustomViews.Models;

public class CardsModel {
    private String  numero,
                    distribuidora,
                    nome_impresso,
                    vencimento;

    private int     cod_seguranca;

    public CardsModel(String numero, String distribuidora, String nome_impresso, String vencimento, int cod_seguranca) {
        this.numero = numero;
        this.distribuidora = distribuidora;
        this.nome_impresso = nome_impresso;
        this.vencimento = vencimento;
        this.cod_seguranca = cod_seguranca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getNome_impresso() {
        return nome_impresso;
    }

    public void setNome_impresso(String nome_impresso) {
        this.nome_impresso = nome_impresso;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public int getCod_seguranca() {
        return cod_seguranca;
    }

    public void setCod_seguranca(int cod_seguranca) {
        this.cod_seguranca = cod_seguranca;
    }
}
