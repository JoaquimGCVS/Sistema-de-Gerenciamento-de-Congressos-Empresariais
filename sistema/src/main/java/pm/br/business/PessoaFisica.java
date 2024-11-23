package pm.br.business;

public abstract class PessoaFisica extends Pessoa {
  private String cpf;

  public PessoaFisica(String nome, String cpf) {
    super(nome);
    this.cpf = cpf;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CPF: ").append(this.cpf);
    return sb.toString();
  }

  public abstract String getPapel();

  public abstract String getAlocacaoTempo();
}
