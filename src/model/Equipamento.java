package model;

public class Equipamento {
    private final int id;
    private String nome;
    private int quantidade;
    private boolean disponivel;

    public Equipamento(int id, String nome, int quantidade, boolean disponivel) {
        this.id = id;
        setNome(nome);
        setQuantidade(quantidade);
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade nao pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Disponivel: " + (disponivel ? "Sim" : "Nao"));
    }
}
