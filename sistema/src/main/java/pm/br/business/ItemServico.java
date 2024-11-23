package pm.br.business;

public abstract class ItemServico {
  private String tipo;
  private int quantidade;
  private String descricao;

  public ItemServico(String tipo, int quantidade, String descricao) {
    setTipo(tipo);
    setQuantidade(quantidade);
    setDescricao(descricao);
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    if (tipo == null || tipo.isEmpty()) {
      throw new IllegalArgumentException("Tipo do serviço não pode ser nulo ou vazio.");
    }
    this.tipo = tipo;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    if (quantidade < 0) {
      throw new IllegalArgumentException("Quantidade não pode ser negativa.");
    }
    this.quantidade = quantidade;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public abstract double calcularCustoTotal();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ItemServico: ");
    sb.append("tipo=").append(tipo);
    sb.append(", quantidade=").append(quantidade);
    sb.append(", descricao=").append(descricao);
    return sb.toString();
  }
}
