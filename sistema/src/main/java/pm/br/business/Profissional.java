package pm.br.business;

public class Profissional extends PessoaFisica {

  private String papel;
  private String alocacaoTempo;
  

  public Profissional(String nome, String cpf, String papel, String alocacaoTempo) {
    super(nome, cpf);
    this.setPapel(papel);
    this.setAlocacaoTempo(alocacaoTempo);
  }

  @Override
  public String getPapel() {
    return papel;
  }

  @Override
  public String getAlocacaoTempo() {
    return alocacaoTempo;
  }

  public void setPapel(String papel) {
    if (papel == null || papel.isEmpty()) {
      throw new IllegalArgumentException("O papel do profissional não pode ser nulo ou vazio.");
    }
    this.papel = papel;
  }

  public void setAlocacaoTempo(String alocacaoTempo) {
    if (alocacaoTempo == null || alocacaoTempo.isEmpty()) {
      throw new IllegalArgumentException("O tempo de alocação não pode ser nulo ou vazio.");
    }
    this.alocacaoTempo = alocacaoTempo;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append("Papel: ").append(this.getPapel()).append("\n");
    sb.append("Alocacao de tempo: ").append(this.getAlocacaoTempo()).append("\n");
    return sb.toString();
  }
}
