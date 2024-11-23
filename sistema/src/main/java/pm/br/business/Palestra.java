package pm.br.business;

public class Palestra extends Atividade {

    public Palestra(String descricao, String data, String local, int qtdParticipantes) {
        super(descricao, data, local, qtdParticipantes);
    }

    @Override
    public String toString() {
        return "Palestra: " + super.toString();
    }

}
