package controller;

import exceptions.ValidacaoException;
import model.Pagamento;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class PagamentoController {
    private final ArrayList<Pagamento> pagamentos;
    private int proximoId;

    public PagamentoController() {
        pagamentos = ArquivoService.carregarPagamentos();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId() >= proximoId) {
                proximoId = pagamento.getId() + 1;
            }
        }
    }

    public void cadastrar(String nomeAluno, double valor, String formaPagamento, boolean pago)
            throws ValidacaoException {
        Pagamento pagamento = new Pagamento(proximoId, nomeAluno, valor, formaPagamento, pago);
        pagamentos.add(pagamento);
        LoggerService.log("INFO", "Pagamento cadastrado: ID " + pagamento.getId() + " - " + pagamento.getNomeAluno());
        ArquivoService.salvarPagamentos(pagamentos);
        proximoId++;
    }

    public ArrayList<Pagamento> listar() {
        return pagamentos;
    }

    public Pagamento buscarPorId(int id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId() == id) {
                return pagamento;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nomeAluno, double valor, String formaPagamento, boolean pago)
            throws ValidacaoException {
        Pagamento pagamento = buscarPorId(id);

        if (pagamento == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar pagamento inexistente: ID " + id);
            return false;
        }

        pagamento.setNomeAluno(nomeAluno);
        pagamento.setValor(valor);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setPago(pago);
        LoggerService.log("INFO", "Pagamento atualizado: ID " + pagamento.getId() + " - " + pagamento.getNomeAluno());
        ArquivoService.salvarPagamentos(pagamentos);
        return true;
    }

    public boolean remover(int id) {
        Pagamento pagamento = buscarPorId(id);

        if (pagamento == null) {
            LoggerService.log("WARNING", "Tentativa de remover pagamento inexistente: ID " + id);
            return false;
        }

        pagamentos.remove(pagamento);
        LoggerService.log("INFO", "Pagamento removido: ID " + pagamento.getId() + " - " + pagamento.getNomeAluno());
        ArquivoService.salvarPagamentos(pagamentos);
        return true;
    }
}
