package pm.br.business;

import java.util.ArrayList;
import java.util.List;

public class Congresso {
  private String setor;
  private float valorContratado;
  private String especificacoes;
  private List<Atividade> atividades;
  private List<PessoaJuridica> fornecedores;

  public Congresso(String setor, float valorContratado, String especificacoes) {
    this.setSetor(setor);
    this.setValorContratado(valorContratado);
    this.setEspecificacoes(especificacoes);
    this.atividades = new ArrayList<>();
    this.fornecedores = new ArrayList<>();
  }

  public String getSetor() {
    return setor;
  }

  public void setSetor(String setor) {
    if (setor == null || setor.isBlank()) {
        throw new IllegalArgumentException("Setor não pode ser nulo ou vazio.");
    }
    this.setor = setor;
  }

  public float getValorContratado() {
    return valorContratado;
  }

  public void setValorContratado(float valorContratado) {
    if (valorContratado <= 0) {
        throw new IllegalArgumentException("O valor contratado deve ser maior que zero.");
    }
    this.valorContratado = valorContratado;
  }

  public String getEspecificacoes() {
    return especificacoes;
  }

  public void setEspecificacoes(String especificacoes) {
    if (especificacoes == null || especificacoes.isBlank()) {
        throw new IllegalArgumentException("Especificações não podem ser nulas ou vazias.");
    }
    this.especificacoes = especificacoes;
  }

  public void adicionarFornecedor(PessoaJuridica fornecedor) {
    if (fornecedor != null) {
        fornecedores.add(fornecedor);
        System.out.println("Fornecedor adicionado: " + fornecedor.getNome());
    } else {
        System.out.println("Fornecedor é nulo e não foi adicionado.");
    }
  }

  public void listarFornecedores() {
    if (fornecedores.isEmpty()) {
        System.out.println("Nenhum fornecedor cadastrado.");
    } else {
        System.out.println("Lista de Fornecedores:");
        for (PessoaJuridica fornecedor : fornecedores) {
            System.out.println("- " + fornecedor.getNome());
        }
    }
  }

  public void removerFornecedor(PessoaJuridica fornecedor) {
    if (fornecedores.remove(fornecedor)) {
        System.out.println("Fornecedor removido: " + fornecedor.getNome());
    } else {
        System.out.println("Fornecedor não encontrado.");
    }
  }

  public void removerFornecedorPorNome(String nome) {
    boolean removido = fornecedores.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    if (removido) {
        System.out.println("Fornecedor com nome '" + nome + "' removido.");
    } else {
        System.out.println("Nenhum fornecedor com o nome '" + nome + "' foi encontrado.");
    }
  }

  public void adicionarAtividade(Atividade atividade) {
    if (atividade != null) {
        atividades.add(atividade);
        System.out.println("Atividade adicionada: " + atividade.getDescricao());
    } else {
        System.out.println("Atividade é nula e não foi adicionada.");
    }
  }

  public void listarAtividades() {
    if (atividades.isEmpty()) {
        System.out.println("Nenhuma atividade cadastrada.");
    } else {
        System.out.println("Lista de Atividades:");
        for (Atividade atividade : atividades) {
            System.out.println("- " + atividade.getDescricao());
        }
    }
  }

  public void removerAtividade(Atividade atividade) {
    if (atividades.remove(atividade)) {
        System.out.println("Atividade removida: " + atividade.getDescricao());
    } else {
        System.out.println("Atividade não encontrada.");
    }
  }

  public List<PessoaJuridica> getFornecedores() {
    return new ArrayList<>(fornecedores); 
  }

  public List<Atividade> getAtividades() {
    return new ArrayList<>(atividades);
  }

  public List<Atividade> filtrarAtividadesPorData(String data) {
    return atividades.stream()
        .filter(a -> a.getData().equals(data))
        .toList();
  }
  
  public List<PessoaJuridica> filtrarFornecedoresPorTipoDeServico(String tipo) {
    return fornecedores.stream()
        .filter(f -> f.getTipoServico().equalsIgnoreCase(tipo))
        .toList();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Setor: ").append(this.getSetor()).append("\n");
    sb.append("Valor contratado: ").append(this.getValorContratado()).append("\n");
    sb.append("Especificações: ").append(this.getEspecificacoes()).append("\n");
    sb.append("Fornecedores: ").append("\n");
    if (fornecedores.isEmpty()) {
        sb.append("Nenhum fornecedor cadastrado.\n");
    } else {
        fornecedores.forEach(f -> sb.append("- ").append(f.getNome()).append("\n"));
    }
    sb.append("Atividades: ").append("\n");
    if (atividades.isEmpty()) {
        sb.append("Nenhuma atividade cadastrada.\n");
    } else {
        atividades.forEach(a -> sb.append("- ").append(a.getDescricao()).append("\n"));
    }
    return sb.toString();
  }
}


