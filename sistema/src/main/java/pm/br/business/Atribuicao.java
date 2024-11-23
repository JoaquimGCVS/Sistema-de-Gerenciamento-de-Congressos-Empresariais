package pm.br.business;

import java.time.LocalDate;

public class Atribuicao {
    private LocalDate dataAtribuicao;
    private double cargaHoraria;
    private PessoaFisica profissional;
    private Atividade atividade;

    public Atribuicao(LocalDate dataAtribuicao, double cargaHoraria, PessoaFisica profissional, Atividade atividade) {
        setDataAtribuicao(dataAtribuicao);
        setCargaHoraria(cargaHoraria);
        setProfissional(profissional);
        setAtividade(atividade);
    }

    public LocalDate getDataAtribuicao() {
        return dataAtribuicao;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public PessoaFisica getProfissional() {
        return profissional;
    }

    public Atividade getAtividade() {
        return atividade;
    }


    public void setDataAtribuicao(LocalDate dataAtribuicao) {
        if (dataAtribuicao == null) {
            throw new IllegalArgumentException("A data de atribuição não pode ser nula.");
        }
        this.dataAtribuicao = dataAtribuicao;
    }

    public void setCargaHoraria(double cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser positiva.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public void setProfissional(PessoaFisica profissional) {
        if (profissional == null) {
            throw new IllegalArgumentException("O profissional não pode ser nulo.");
        }
        this.profissional = profissional;
    }

    public void setAtividade(Atividade atividade) {
        if (atividade == null) {
            throw new IllegalArgumentException("A atividade não pode ser nula.");
        }
        this.atividade = atividade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atribuição{");
        sb.append("dataAtribuicao=").append(dataAtribuicao);
        sb.append(", cargaHoraria=").append(cargaHoraria);
        sb.append(", profissional=").append(profissional);
        sb.append(", atividade=").append(atividade);
        sb.append('}');
        return sb.toString();
    }
}