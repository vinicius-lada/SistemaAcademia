package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Plano implements Exibivel {
    private final int id;
    private String nome;
    private double valor;
    private int duracaoMeses;

    public Plano(int id, String nome, double valor, int duracaoMeses)
            throws ValidacaoException {
        this.id = id;
        setNome(nome);
        setValor(valor);
        setDuracaoMeses(duracaoMeses);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setValor(double valor) throws ValidacaoException {
        if (valor <= 0) {
            throw new ValidacaoException("Valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public void setDuracaoMeses(int duracaoMeses) throws ValidacaoException {
        if (duracaoMeses <= 0) {
            throw new ValidacaoException("Duracao em meses deve ser maior que zero.");
        }
        this.duracaoMeses = duracaoMeses;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Valor: " + valor);
        System.out.println("Duracao em meses: " + duracaoMeses);
    }
}
