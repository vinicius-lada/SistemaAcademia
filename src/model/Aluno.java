package model;

import exceptions.ValidacaoException;

public class Aluno extends Pessoa {
    public Aluno(int id, String nome, String cpf, String telefone)
            throws ValidacaoException {
        super(id, nome, cpf, telefone);
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Telefone: " + getTelefone());
    }
}
