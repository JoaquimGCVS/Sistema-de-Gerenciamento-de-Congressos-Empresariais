package pm.br.business;

public abstract class Pessoa {
  private String nome;

  public Pessoa(String nome) {
    setNome(nome); 
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (nome == null || nome.trim().isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
    }
    this.nome = nome;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Nome: ").append(nome);
    return sb.toString();
  }
}
