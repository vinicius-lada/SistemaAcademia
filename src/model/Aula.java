package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Aula implements Exibivel {
    private static final long serialVersionUID = 1L;

    private final int id;
    private String nome;
    private String professor;
    private String horario;
    private int capacidade;

    public Aula(int id, String nome, String professor, String horario, int capacidade)
            throws ValidacaoException {
        this.id = id;
        setNome(nome);
        setProfessor(professor);
        setHorario(horario);
        setCapacidade(capacidade);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setProfessor(String professor) throws ValidacaoException {
        if (professor == null || professor.isEmpty()) {
            throw new ValidacaoException("Professor nao pode ser vazio.");
        }
        this.professor = professor;
    }

    public void setHorario(String horario) throws ValidacaoException {
        if (horario == null || horario.isEmpty()) {
            throw new ValidacaoException("Horario nao pode ser vazio.");
        }
        this.horario = horario;
    }

    public void setCapacidade(int capacidade) throws ValidacaoException {
        if (capacidade <= 0) {
            throw new ValidacaoException("Capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Professor: " + professor);
        System.out.println("Horario: " + horario);
        System.out.println("Capacidade: " + capacidade);
    }
}
