package model;

import exceptions.ValidacaoException;

public class Professor extends Pessoa {
    private String especialidade;

    public Professor(int id, String nome, String cpf, String especialidade, String telefone)
            throws ValidacaoException {
        super(id, nome, cpf, telefone);
        setEspecialidade(especialidade);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) throws ValidacaoException {
        if (especialidade == null || especialidade.isEmpty()) {
            throw new ValidacaoException("Especialidade nao pode ser vazia.");
        }
        this.especialidade = especialidade;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Telefone: " + getTelefone());
    }
}
