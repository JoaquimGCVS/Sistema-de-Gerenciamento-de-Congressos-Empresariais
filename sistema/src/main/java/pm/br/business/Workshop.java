package pm.br.business;

public class Workshop extends Atividade {

    public Workshop(String descricao, String data, String local, int qtdParticipantes) {
        super(descricao, data, local, qtdParticipantes);
    }

    @Override
    public String toString() {
        return "Workshop: " + super.toString();
    }
    
}
