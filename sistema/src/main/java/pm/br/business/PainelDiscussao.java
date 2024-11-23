package pm.br.business;

public class PainelDiscussao extends Atividade {

    public PainelDiscussao(String descricao, String data, String local, int qtdParticipantes) {
        super(descricao, data, local, qtdParticipantes);
    }

    @Override
    public String toString() {
        return "Painel de Discussão: " + super.toString();
    }


}
