package pm.br.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PacoteServico {
  private String nome;
  private String descricao;
  private List<ItemServico> itens = new ArrayList<>();

  public PacoteServico(String nome, String descricao) {
    this.setNome(nome);
    this.setDescricao(descricao);
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      throw new IllegalArgumentException("Nome do pacote não pode ser nulo ou vazio.");

    }
    this.nome = nome;
  }

  public void setDescricao(String descricao) {
    if (descricao == null || descricao.isEmpty()) {
      throw new IllegalArgumentException("Descrição do pacote não pode ser nula ou vazia.");

    }
    this.descricao = descricao;
  }

  public void adicionarItemServico(ItemServico item) {
    if (item == null) {
      throw new IllegalArgumentException("Item não pode ser nulo.");
    }
    if (itens.contains(item)) {
      throw new IllegalArgumentException("Item já existe no pacote.");
    }
    itens.add(item);
  }

  public List<ItemServico> ordenarItensPorCusto(boolean crescente) {
    List<ItemServico> itensOrdenados = new ArrayList<>(itens);
    itensOrdenados.sort(Comparator.comparing(ItemServico::calcularCustoTotal));
    if (!crescente) {
      Collections.reverse(itensOrdenados);
    }
    return itensOrdenados;
  }

  public List<ItemServico> filtrarItensPorTipo(String tipo) {
    return itens.stream()
        .filter(item -> item.getTipo().toLowerCase().contains(tipo.toLowerCase()))
        .collect(Collectors.toList());
  }

  public double calcularCustoTotal() {
    return itens.stream()
        .mapToDouble(ItemServico::calcularCustoTotal)
        .sum();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("PacoteServico: ").append(nome).append("\n");
    sb.append("Descrição: ").append(descricao).append("\n");
    sb.append("Itens:\n");
    for (ItemServico item : itens) {
      sb.append("- ").append(item).append("\n");
    }
    return sb.toString();
  }
}
