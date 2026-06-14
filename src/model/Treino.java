package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Treino implements Exibivel {
    private final int id;
    private String nome;
    private String objetivo;
    private String descricao;
    private String nivel;

    public Treino(int id, String nome, String objetivo, String descricao, String nivel)
            throws ValidacaoException {
        this.id = id;
        setNome(nome);
        setObjetivo(objetivo);
        setDescricao(descricao);
        setNivel(nivel);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setObjetivo(String objetivo) throws ValidacaoException {
        if (objetivo == null || objetivo.isEmpty()) {
            throw new ValidacaoException("Objetivo nao pode ser vazio.");
        }
        this.objetivo = objetivo;
    }

    public void setDescricao(String descricao) throws ValidacaoException {
        if (descricao == null || descricao.isEmpty()) {
            throw new ValidacaoException("Descricao nao pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public void setNivel(String nivel) throws ValidacaoException {
        if (nivel == null || nivel.isEmpty()) {
            throw new ValidacaoException("Nivel nao pode ser vazio.");
        }
        this.nivel = nivel;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Descricao: " + descricao);
        System.out.println("Nivel: " + nivel);
    }
}
