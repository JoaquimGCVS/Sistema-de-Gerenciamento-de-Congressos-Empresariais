package pm.br.business;

import java.util.List;

public class ImplCalculadoraDeCusto implements CalculadoraDeCusto {

  @Override
  public float calcularCustoTotal(List<ItemServico> itens) {
    if (itens == null || itens.isEmpty()) {
      return 0f;
    }
    return (float) itens.stream()
        .mapToDouble(ItemServico::calcularCustoTotal)
        .reduce(0.0, Double::sum);
  }
}
