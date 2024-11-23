package pm.br.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Atividade {
  private String descricao;
  private String data;
  private String local;
  private int qtdParticipantes;
  private List<PessoaFisica> profissionais;

  public Atividade(String descricao, String data, String local, int qtdParticipantes) {
    this.setDescricao(descricao);
    this.setData(data);
    this.setLocal(local);
    this.setQtdParticipantes(qtdParticipantes);
    this.profissionais = new ArrayList<>();
  }

  public void setDescricao(String descricao) {
    if (descricao == null || descricao.isBlank()) {
      throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia");      
    }
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getData() {
    return data;
  }

  public String getLocal() {
    return local;
  }

  public int getQtdParticipantes() {
    return qtdParticipantes;
  }

  public void setData(String data) {
    if (data == null || data.isBlank()) {
      throw new IllegalArgumentException("Data nao pode ser nula ou vazia");      
    }
    this.data = data;
  }

  public void setLocal(String local) {
    if (local == null || local.isBlank()) {
      throw new IllegalArgumentException("Local nao pode ser nulo ou vazio");      
    }
    this.local = local;
  }

  public void setQtdParticipantes(int qtdParticipantes) {
    if (qtdParticipantes < 0) {
      throw new IllegalArgumentException("Quantidade de participantes nao pode ser negativa");      
    }
    this.qtdParticipantes = qtdParticipantes;
  }

  public void adicionarProfissional(PessoaFisica profissional) {
    profissionais.add(profissional);
  }

  public List<PessoaFisica> filtrarProfissionaisPorPapel(String papel) {
    return profissionais.stream()
        .filter(profissional -> profissional.getPapel().trim().equalsIgnoreCase(papel.trim()))
        .toList();
  }

  public List<PessoaFisica> listarProfissionais() {
    return profissionais;
  }

  public List<PessoaFisica> ordenarProfissionaisPorAlocacaoTempo() {
    return profissionais.stream()
        .sorted(Comparator.comparing(PessoaFisica::getAlocacaoTempo))
        .toList();
  }

  public List<PessoaFisica> ordenarProfissionaisPorPapel() {
    return profissionais.stream()
        .sorted(Comparator.comparing(PessoaFisica::getPapel))
        .toList();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Descricao: ").append(this.getDescricao()).append("\n");
    sb.append("Data: ").append(this.getData()).append("\n");
    sb.append("Local: ").append(this.getLocal()).append("\n");
    sb.append("Quantidade de participantes: ").append(this.getQtdParticipantes()).append("\n");
    return sb.toString();
  }
}
