package pm.br.business;

public class ItemCatering extends ItemServico {
  private String tipoAlimento;
  private double precoPorPessoa;

  public ItemCatering(String tipo, int quantidade, String descricao,
      String tipoAlimento, double precoPorPessoa) {
    super(tipo, quantidade, descricao);
    setTipoAlimento(tipoAlimento);
    setPrecoPorPessoa(precoPorPessoa);
  }

  public String getTipoAlimento() {
    return tipoAlimento;
  }

  public void setTipoAlimento(String tipoAlimento) {
    if (tipoAlimento == null || tipoAlimento.isEmpty()) {
      throw new IllegalArgumentException("O tipo de alimento não pode ser nulo ou vazio.");
    }
    this.tipoAlimento = tipoAlimento;
  }

  public double getPrecoPorPessoa() {
    return precoPorPessoa;
  }

  public void setPrecoPorPessoa(double precoPorPessoa) {
    if (precoPorPessoa <= 0) {
      throw new IllegalArgumentException("O preço por pessoa deve ser positivo.");
    }
    this.precoPorPessoa = precoPorPessoa;
  }

  @Override
  public double calcularCustoTotal() {
    return this.getQuantidade() * precoPorPessoa;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ItemCatering: ");
    sb.append("tipo=").append(getTipo());
    sb.append(", quantidade=").append(getQuantidade());
    sb.append(", tipoAlimento=").append(getTipoAlimento());
    sb.append(", preço por pessoa=").append(getPrecoPorPessoa());
    return sb.toString();
  }
}