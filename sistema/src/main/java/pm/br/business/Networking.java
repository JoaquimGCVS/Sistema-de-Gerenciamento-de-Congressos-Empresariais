package pm.br.business;

public class Networking  extends Atividade {

    public Networking(String descricao, String data, String local, int qtdParticipantes) {
        super(descricao, data, local, qtdParticipantes);
    }
 
    @Override
    public String toString() {
        return "Networking: " + super.toString();
    }
}
