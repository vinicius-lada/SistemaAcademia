package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Matricula implements Exibivel {
    private final int id;
    private Aluno aluno;
    private Plano plano;
    private String dataMatricula;
    private boolean ativa;

    public Matricula(int id, Aluno aluno, Plano plano, String dataMatricula, boolean ativa)
            throws ValidacaoException {
        this.id = id;
        setAluno(aluno);
        setPlano(plano);
        setDataMatricula(dataMatricula);
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Plano getPlano() {
        return plano;
    }

    public String getNomeAluno() {
        return aluno.getNome();
    }

    public String getNomePlano() {
        return plano.getNome();
    }

    public String getDataMatricula() {
        return dataMatricula;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAluno(Aluno aluno) throws ValidacaoException {
        if (aluno == null) {
            throw new ValidacaoException("Aluno nao pode ser vazio.");
        }
        this.aluno = aluno;
    }

    public void setPlano(Plano plano) throws ValidacaoException {
        if (plano == null) {
            throw new ValidacaoException("Plano nao pode ser vazio.");
        }
        this.plano = plano;
    }

    public void setDataMatricula(String dataMatricula) throws ValidacaoException {
        if (dataMatricula == null || dataMatricula.isEmpty()) {
            throw new ValidacaoException("Data da matricula nao pode ser vazia.");
        }
        this.dataMatricula = dataMatricula;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Plano: " + plano.getNome());
        System.out.println("Data da matricula: " + dataMatricula);
        System.out.println("Ativa: " + (ativa ? "Sim" : "Nao"));
    }
}
