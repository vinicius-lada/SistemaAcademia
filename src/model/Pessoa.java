package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public abstract class Pessoa implements Exibivel {
    private final int id;
    private String nome;
    private String cpf;
    private String telefone;

    public Pessoa(int id, String nome, String cpf, String telefone)
            throws ValidacaoException {
        this.id = id;
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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

    public void setCpf(String cpf) throws ValidacaoException {
        if (cpf == null || cpf.isEmpty()) {
            throw new ValidacaoException("CPF nao pode ser vazio.");
        }
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) throws ValidacaoException {
        if (telefone == null || telefone.isEmpty()) {
            throw new ValidacaoException("Telefone nao pode ser vazio.");
        }
        this.telefone = telefone;
    }
}
