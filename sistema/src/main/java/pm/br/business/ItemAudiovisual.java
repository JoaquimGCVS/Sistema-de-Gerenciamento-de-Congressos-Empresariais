package pm.br.business;

public class ItemAudiovisual extends ItemServico {
  private String equipamento;
  private double precoUnitario;

  public ItemAudiovisual(String tipo, int quantidade, String descricao,
      String equipamento, double precoUnitario) {
    super(tipo, quantidade, descricao);
    setEquipamento(equipamento);
    setPrecoUnitario(precoUnitario);
  }

  public String getEquipamento() {
    return equipamento;
  }

  public void setEquipamento(String equipamento) {
    if (equipamento == null || equipamento.isEmpty()) {
      throw new IllegalArgumentException("O nome do equipamento não pode ser nulo ou vazio.");
    }
    this.equipamento = equipamento;
  }

  public double getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(double precoUnitario) {
    if (precoUnitario <= 0) {
      throw new IllegalArgumentException("O preço unitário deve ser positivo.");
    }
    this.precoUnitario = precoUnitario;
  }

  @Override
  public double calcularCustoTotal() {
    return this.getQuantidade() * precoUnitario;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ItemAudiovisual: ");
    sb.append("tipo=").append(getTipo());
    sb.append(", quantidade=").append(getQuantidade());
    sb.append(", equipamento=").append(getEquipamento());
    sb.append(", preço unitário=").append(getPrecoUnitario());
    return sb.toString();
  }
}