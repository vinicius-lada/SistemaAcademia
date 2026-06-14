package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Professor implements Exibivel {
    private final int id;
    private String nome;
    private String especialidade;
    private String telefone;

    public Professor(int id, String nome, String especialidade, String telefone)
            throws ValidacaoException {
        this.id = id;
        setNome(nome);
        setEspecialidade(especialidade);
        setTelefone(telefone);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) throws ValidacaoException {
        if (especialidade == null || especialidade.isEmpty()) {
            throw new ValidacaoException("Especialidade nao pode ser vazia.");
        }
        this.especialidade = especialidade;
    }

    public void setTelefone(String telefone) throws ValidacaoException {
        if (telefone == null || telefone.isEmpty()) {
            throw new ValidacaoException("Telefone nao pode ser vazio.");
        }
        this.telefone = telefone;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Telefone: " + telefone);
    }
}
