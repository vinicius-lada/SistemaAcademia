package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Equipamento implements Exibivel {
    private static final long serialVersionUID = 1L;

    private final int id;
    private String nome;
    private int quantidade;
    private boolean disponivel;

    public Equipamento(int id, String nome, int quantidade, boolean disponivel)
            throws ValidacaoException {
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

    public void setNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) throws ValidacaoException {
        if (quantidade < 0) {
            throw new ValidacaoException("Quantidade nao pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Disponivel: " + (disponivel ? "Sim" : "Nao"));
    }
}
