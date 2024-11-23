package pm.br.main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import pm.br.business.Atividade;
import pm.br.business.Congresso;
import pm.br.business.Fornecedor;
import pm.br.business.ItemAudiovisual;
import pm.br.business.ItemCatering;
import pm.br.business.Networking;
import pm.br.business.PacoteServico;
import pm.br.business.PainelDiscussao;
import pm.br.business.Palestra;
import pm.br.business.PessoaFisica;
import pm.br.business.PessoaJuridica;
import pm.br.business.Profissional;
import pm.br.business.Workshop;

/**
 * The SistemaCongresso class is the main class for managing a conference
 * system. It provides
 * methods for registering conferences, activities, professionals, suppliers,
 * and service packages.
 * It also allows calculating the total cost of a service package and listing
 * activities.
 */
public class SistemaCongresso {

  private static List<Congresso> congressos = new ArrayList<>();
  private static List<PessoaJuridica> fornecedores = new ArrayList<>();
  private static List<PessoaFisica> profissionais = new ArrayList<>();
  private static List<PacoteServico> pacotesServicos = new ArrayList<>();
  private static List<Atividade> atividades = new ArrayList<>();

  public static void main(String[] args) {
    int opcao;
    do {
      opcao = mostrarMenu();
      switch (opcao) {
        case 1:
          cadastrarCongresso();
          break;
        case 2:
          cadastrarAtividade();
          break;
        case 3:
          cadastrarProfissional();
          break;
        case 4:
          cadastrarFornecedor();
          break;
        case 5:
          cadastrarPacoteServico();
          break;
        case 6:
          calcularCustoTotalPacote();
          break;
        case 7:
          listarAtividades();
          break;
        case 0:
          JOptionPane.showMessageDialog(null, "Saindo do sistema...");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
          break;
      }
    } while (opcao != 0);
  }

  private static int mostrarMenu() {
    String menu = """
        Escolha uma opção:
        1 - Cadastrar Congresso
        2 - Cadastrar Atividade
        3 - Cadastrar Profissional
        4 - Cadastrar Fornecedor
        5 - Cadastrar Pacote de Serviço
        6 - Calcular Custo Total do Pacote de Serviço
        7 - Listar Atividades
        0 - Sair
        """;
    String input = JOptionPane.showInputDialog(menu);
    if (input == null) {
      return 0;
    }
    return Integer.parseInt(input);
  }

  private static void cadastrarCongresso() {
    String setor = "";
    while (setor.isBlank() || setor == null) {
      setor = JOptionPane.showInputDialog("Informe o setor do congresso:");
    }

    Float valorContratado = null;
    while (valorContratado == null || valorContratado <= 0 || Float.isNaN(valorContratado)) {
      try {
        valorContratado = Float.parseFloat(
            JOptionPane.showInputDialog("Informe o valor contratado (deve ser maior que zero):"));
        if (valorContratado <= 0) {
          JOptionPane.showMessageDialog(null, "O valor deve ser maior que zero. Tente novamente.");
          valorContratado = null;
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
        valorContratado = null;
      }
    }

    String especificacoes = "";
    while (especificacoes.isBlank() || especificacoes == null) {
      especificacoes = JOptionPane.showInputDialog("Informe as especificações:");
    }

    Congresso congresso = new Congresso(setor, valorContratado, especificacoes);
    congressos.add(congresso);
    JOptionPane.showMessageDialog(null, "Congresso cadastrado com sucesso!");

    int opcao;
    do {
      opcao = Integer.parseInt(
          JOptionPane.showInputDialog(
              """
                  Escolha uma opção:
                  1 - Adicionar fornecedor
                  2 - Listar fornecedores
                  3 - Remover fornecedor
                  4 - Adicionar atividade
                  5 - Listar atividades
                  6 - Filtrar atividades por data
                  0 - Sair
                  """));
      switch (opcao) {
        case 1:
          congresso.adicionarFornecedor(cadastrarFornecedor());
          break;
        case 2:
          congresso.listarFornecedores();
          break;
        case 3:
          congresso.removerFornecedorPorNome(
              JOptionPane.showInputDialog("Informe o nome do fornecedor:"));
          break;
        case 4:
          Atividade atividade = cadastrarAtividade();
          congresso.adicionarAtividade(atividade);
          break;
        case 5:
          congresso.listarAtividades();
          break;
        case 6:
          String dataFiltro = JOptionPane.showInputDialog("Informe a data para filtrar as atividades:");
          List<Atividade> atividadesFiltradas = congresso.filtrarAtividadesPorData(dataFiltro);
          if (atividadesFiltradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma atividade encontrada para a data informada.");
          } else {
            StringBuilder atividadesList = new StringBuilder("Atividades filtradas:\n");
            for (Atividade a : atividadesFiltradas) {
              atividadesList.append(a.getDescricao()).append("\n");
            }
            JOptionPane.showMessageDialog(null, atividadesList.toString());
          }
          break;
        case 0:
          JOptionPane.showMessageDialog(null, "Saindo do cadastro de congresso...");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
      }
    } while (opcao != 0);
  }

  private static Atividade cadastrarAtividade() {
    String tipo = "";
    while (tipo.isBlank()) {
      tipo = JOptionPane.showInputDialog("Informe o tipo da atividade:");
    }

    String data = "";
    while (data.isBlank()) {
      data = JOptionPane.showInputDialog("Informe a data da atividade (dd/MM/yyyy):");
      if (!isValidDate(data)) {
        JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida no formato dd/MM/yyyy.");
        data = "";
      }
    }

    String local = "";
    while (local.isBlank()) {
      local = JOptionPane.showInputDialog("Informe o local da atividade:");
    }

    Integer qtdParticipantes = null;
    while (qtdParticipantes == null || qtdParticipantes <= 0) {
      try {
        qtdParticipantes = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de participantes:"));
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para a quantidade de participantes.");
      }
    }

    int tipoAtividade = 0;
    while (tipoAtividade < 1 || tipoAtividade > 4) {
      try {
        tipoAtividade = Integer.parseInt(JOptionPane.showInputDialog(
            """
                Escolha uma opção:
                1 - Palestra
                2 - Painel de Discussão
                3 - Workshop
                4 - Mesa Redonda
                """));
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o tipo de atividade.");
      }
    }

    Atividade atividade = null;

    switch (tipoAtividade) {
      case 1:
        atividade = new Palestra(tipo, data, local, qtdParticipantes);
        break;
      case 2:
        atividade = new PainelDiscussao(tipo, data, local, qtdParticipantes);
        break;
      case 3:
        atividade = new Workshop(tipo, data, local, qtdParticipantes);
        break;
      case 4:
        atividade = new Networking(tipo, data, local, qtdParticipantes);
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opção inválida. A atividade não foi criada.");
        return null;
    }

    // Adicionando a atividade à lista
    atividades.add(atividade);

    int opcao;
    do {
      opcao = Integer.parseInt(JOptionPane.showInputDialog(
          """
              Escolha uma opção:
              1 - Adicionar profissional
              2 - Listar profissionais
              0 - Sair
              """));

      switch (opcao) {
        case 1:
          adicionarProfissional(atividade);
          break;
        case 2:
          listarProfissionais(atividade);
          break;
        case 0:
          JOptionPane.showMessageDialog(null, "Saindo do cadastro de atividade...");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
      }
    } while (opcao != 0);

    JOptionPane.showMessageDialog(null, "Atividade cadastrada com sucesso!");
    return atividade;
  }

  private static boolean isValidDate(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    try {
      sdf.parse(date);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  private static Profissional cadastrarProfissional() {
    String nome = "";
    while (nome.isBlank() || nome == null) {
      nome = JOptionPane.showInputDialog("Informe o nome do profissional:");
    }

    String papel = "";
    while (papel.isBlank() || papel == null) {
      papel = JOptionPane.showInputDialog("Informe o papel do profissional:");
    }

    String alocacaoTempo = "";
    while (alocacaoTempo.isBlank() || alocacaoTempo == null) {
      alocacaoTempo = JOptionPane.showInputDialog("Informe a alocação de tempo do profissional:");
    }

    String cpf = "";
    while (cpf.isBlank() || cpf == null) {
      cpf = JOptionPane.showInputDialog("Informe o CPF do profissional:");
    }

    Profissional profissional = new Profissional(nome,cpf, papel, alocacaoTempo);
    profissionais.add(profissional);
    JOptionPane.showMessageDialog(null, "Profissional cadastrado com sucesso!");
    return profissional;
  }

  private static void adicionarProfissional(Atividade atividade) {
    Profissional profissional = cadastrarProfissional();

    if (profissional != null) {
      atividade.adicionarProfissional(profissional);
      JOptionPane.showMessageDialog(null, "Profissional adicionado com sucesso!");
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possível adicionar o profissional.");
    }
  }

  private static void listarProfissionais(Atividade atividade) {
    String papel = "";
    while (papel.isBlank()) {
      papel = JOptionPane.showInputDialog("Informe o papel do profissional:");
      if (papel == null) {
        JOptionPane.showMessageDialog(null, "Operação cancelada.");
        return;
      }
    }

    List<PessoaFisica> profissionaisFiltrados = atividade.filtrarProfissionaisPorPapel(papel);
    if (profissionaisFiltrados.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nenhum profissional encontrado com o papel informado!");
    } else {
      StringBuilder listaProfissionais = new StringBuilder("Profissionais com o papel " + papel + ":\n");
      for (PessoaFisica profissional : profissionaisFiltrados) {
        listaProfissionais.append(profissional.getNome()).append("\n");
      }
      JOptionPane.showMessageDialog(null, listaProfissionais.toString());
    }
  }

  private static PessoaJuridica cadastrarFornecedor() {
    String nome = "";
    while (nome.isBlank() || nome == null) {
      nome = JOptionPane.showInputDialog("Informe o nome do fornecedor:");
    }

    String tipoServico = "";
    while (tipoServico.isBlank() || tipoServico == null) {
      tipoServico = JOptionPane.showInputDialog("Informe o tipo de serviço do fornecedor:");
    }

    String cnpj = "";
    while (cnpj.isBlank() || cnpj == null) {
      cnpj = JOptionPane.showInputDialog("Informe o CNPJ do fornecedor:");
    }

    String endereco = "";
    while (endereco.isBlank() || endereco == null) {
      endereco = JOptionPane.showInputDialog("Informe o endereço do fornecedor:");
    }

    JOptionPane.showMessageDialog(null, "Deseja adicionar um pacote de serviço ao fornecedor?");
    int opcao = Integer.parseInt(
        JOptionPane.showInputDialog(
            """
                Escolha uma opção:
                1 - Sim
                2 - Não
                """));
    if (opcao == 1) {
      PacoteServico pacoteServico = cadastrarPacoteServico();
      Fornecedor fornecedor = new Fornecedor(nome, tipoServico, cnpj, endereco);
      fornecedor.adicionarPacoteServico(pacoteServico);
      fornecedores.add(fornecedor);
    } else if (opcao == 2) {
      Fornecedor fornecedor = new Fornecedor(nome, tipoServico, cnpj, endereco);
      fornecedores.add(fornecedor);
    } else {
      JOptionPane.showMessageDialog(null, "Opção inválida!");
    }

    Fornecedor fornecedor = new Fornecedor(nome, tipoServico, cnpj, endereco);
    fornecedores.add(fornecedor);
    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
    return fornecedor;
  }

  private static PacoteServico cadastrarPacoteServico() {

    String nome = "";
    while (nome.isBlank() || nome == null) {
      nome = JOptionPane.showInputDialog("Informe o nome do pacote de serviço:");
    }

    String descricao = "";
    while (descricao.isBlank() || descricao == null) {
      descricao = JOptionPane.showInputDialog("Informe a descrição do pacote de serviço:");
    }

    PacoteServico pacoteServico = new PacoteServico(nome, descricao);
    pacotesServicos.add(pacoteServico);

    int opcao;
    do {
      opcao = Integer.parseInt(
          JOptionPane.showInputDialog(
              """
                  Escolha uma opção:
                  1 - Adicionar item de serviço
                  2 - Listar itens de serviço
                  0 - Sair
                  """));
      switch (opcao) {
        case 1:
          adicionarItemServico(pacoteServico);
          break;
        case 2:
          listarItensServico(pacoteServico);
          break;
        case 0:
          JOptionPane.showMessageDialog(null, "Saindo do cadastro de pacote de serviço...");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
      }
    } while (opcao != 0);

    JOptionPane.showMessageDialog(null, "Pacote de serviço cadastrado com sucesso!");
    return pacoteServico;
  }

  private static void adicionarItemServico(PacoteServico pacoteServico) {

    int opcao = Integer.parseInt(
        JOptionPane.showInputDialog(
            """
                Escolha uma opção:
                1 - Adicionar item de catering
                2 - Adicionar item de Audiovisual
                0 - Sair
                """));

    if (opcao != 1 && opcao != 2 && opcao != 0) {
      JOptionPane.showMessageDialog(null, "Opção inválida!");
      return;
    }

    String nome = "";
    while (nome.isBlank() || nome == null) {
      nome = JOptionPane.showInputDialog("Informe o tipo do item de serviço:");
    }

    int quantidade = 0;
    while (quantidade <= 0) {
      quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade do item de serviço:"));
    }

    String descricao = "";
    while (descricao.isBlank() || descricao == null) {
      descricao = JOptionPane.showInputDialog("Informe a descrição do item de serviço:");
    }

    if (opcao == 1) {
      String tipoAlimento = "";
      while (tipoAlimento.isBlank() || tipoAlimento == null) {
        tipoAlimento = JOptionPane.showInputDialog("Informe o tipo de alimento:");
      }

      Double precoPorPessoa = 0.0;
      while (precoPorPessoa <= 0 || precoPorPessoa == null) {
        precoPorPessoa = Double.parseDouble(JOptionPane.showInputDialog("Informe o preço por pessoa:"));
      }

      pacoteServico.adicionarItemServico(
          new ItemCatering(nome, quantidade, descricao, tipoAlimento, precoPorPessoa));
    }
    if (opcao == 2) {
      String equipamento = "";
      while (equipamento.isBlank() || equipamento == null) {
        equipamento = JOptionPane.showInputDialog("Informe o equipamento:");
      }

      Double precoUnitario = 0.0;
      while (precoUnitario <= 0 || precoUnitario == null) {
        precoUnitario = Double.parseDouble(JOptionPane.showInputDialog("Informe o preço unitário:"));
      }

      pacoteServico.adicionarItemServico(
          new ItemAudiovisual(nome, quantidade, descricao, equipamento, precoUnitario));
    }

    if (opcao != 0) {
      JOptionPane.showMessageDialog(null, "Item de serviço adicionado com sucesso!");
    } else {
      JOptionPane.showMessageDialog(null, "Saindo do cadastro de item de serviço...");
    }
  }

  private static void listarItensServico(PacoteServico pacoteServico) {
    JOptionPane.showMessageDialog(null, pacoteServico.toString());
  }

  private static void calcularCustoTotalPacote() {
    String nomePacote = "";
    while (nomePacote.isBlank() || nomePacote == null) {
      nomePacote = JOptionPane.showInputDialog("Informe o nome do pacote de serviço:");
    }

    PacoteServico pacoteServico = null;
    for (PacoteServico pacote : pacotesServicos) {
      if (pacote.getNome().equals(nomePacote)) {
        pacoteServico = pacote;
        break;
      }
    }

    if (pacoteServico == null) {
      JOptionPane.showMessageDialog(null, "Pacote de serviço não encontrado!");
    } else {
      JOptionPane.showMessageDialog(
          null, "Custo total do pacote de serviço: R$" + pacoteServico.calcularCustoTotal());
    }
  }

  private static void listarAtividades() {
    if (atividades.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nenhuma atividade cadastrada.");
    } else {
      StringBuilder lista = new StringBuilder("Atividades cadastradas:\n");
      for (Atividade atividade : atividades) {
        lista.append(atividade).append("\n");
      }
      JOptionPane.showMessageDialog(null, lista.toString());
    }
  }

}
