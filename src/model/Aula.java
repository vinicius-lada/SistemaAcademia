package model;

public class Aula {
    private final int id;
    private String nome;
    private String professor;
    private String horario;
    private int capacidade;

    public Aula(int id, String nome, String professor, String horario, int capacidade) {
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

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setProfessor(String professor) {
        if (professor == null || professor.isEmpty()) {
            throw new IllegalArgumentException("Professor nao pode ser vazio.");
        }
        this.professor = professor;
    }

    public void setHorario(String horario) {
        if (horario == null || horario.isEmpty()) {
            throw new IllegalArgumentException("Horario nao pode ser vazio.");
        }
        this.horario = horario;
    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        }
        this.capacidade = capacidade;
    }

    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Professor: " + professor);
        System.out.println("Horario: " + horario);
        System.out.println("Capacidade: " + capacidade);
    }
}