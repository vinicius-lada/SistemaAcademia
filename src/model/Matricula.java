package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Matricula implements Exibivel {
    private final int id;
    private String nomeAluno;
    private String plano;
    private String dataMatricula;
    private boolean ativa;

    public Matricula(int id, String nomeAluno, String plano, String dataMatricula, boolean ativa)
            throws ValidacaoException {
        this.id = id;
        setNomeAluno(nomeAluno);
        setPlano(plano);
        setDataMatricula(dataMatricula);
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getPlano() {
        return plano;
    }

    public String getDataMatricula() {
        return dataMatricula;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setNomeAluno(String nomeAluno) throws ValidacaoException {
        if (nomeAluno == null || nomeAluno.isEmpty()) {
            throw new ValidacaoException("Nome do aluno nao pode ser vazio.");
        }
        this.nomeAluno = nomeAluno;
    }

    public void setPlano(String plano) throws ValidacaoException {
        if (plano == null || plano.isEmpty()) {
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
        System.out.println("Nome do aluno: " + nomeAluno);
        System.out.println("Plano: " + plano);
        System.out.println("Data da matricula: " + dataMatricula);
        System.out.println("Ativa: " + (ativa ? "Sim" : "Nao"));
    }
}
