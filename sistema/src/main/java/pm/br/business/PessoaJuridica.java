package pm.br.business;

public abstract class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CNPJ: ").append(this.cnpj);
        return sb.toString();
    }

    public abstract String getTipoServico();

}
