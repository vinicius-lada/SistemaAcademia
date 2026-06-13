package model;

import exceptions.ValidacaoException;
import interfaces.Exibivel;

public class Pagamento implements Exibivel {
    private final int id;
    private String nomeAluno;
    private double valor;
    private String formaPagamento;
    private boolean pago;

    public Pagamento(int id, String nomeAluno, double valor, String formaPagamento, boolean pago)
            throws ValidacaoException {
        this.id = id;
        setNomeAluno(nomeAluno);
        setValor(valor);
        setFormaPagamento(formaPagamento);
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public double getValor() {
        return valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setNomeAluno(String nomeAluno) throws ValidacaoException {
        if (nomeAluno == null || nomeAluno.isEmpty()) {
            throw new ValidacaoException("Nome do aluno nao pode ser vazio.");
        }
        this.nomeAluno = nomeAluno;
    }

    public void setValor(double valor) throws ValidacaoException {
        if (valor <= 0) {
            throw new ValidacaoException("Valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public void setFormaPagamento(String formaPagamento) throws ValidacaoException {
        if (formaPagamento == null || formaPagamento.isEmpty()) {
            throw new ValidacaoException("Forma de pagamento nao pode ser vazia.");
        }
        this.formaPagamento = formaPagamento;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome do aluno: " + nomeAluno);
        System.out.println("Valor: " + valor);
        System.out.println("Forma de pagamento: " + formaPagamento);
        System.out.println("Pago: " + (pago ? "Sim" : "Nao"));
    }
}
