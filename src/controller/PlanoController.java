package controller;

import exceptions.ValidacaoException;
import model.Plano;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class PlanoController {
    private final ArrayList<Plano> planos;
    private int proximoId;

    public PlanoController() {
        planos = ArquivoService.carregarPlanos();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Plano plano : planos) {
            if (plano.getId() >= proximoId) {
                proximoId = plano.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, double valor, int duracaoMeses)
            throws ValidacaoException {
        Plano plano = new Plano(proximoId, nome, valor, duracaoMeses);
        planos.add(plano);
        LoggerService.log("INFO", "Plano cadastrado: ID " + plano.getId() + " - " + plano.getNome());
        ArquivoService.salvarPlanos(planos);
        proximoId++;
    }

    public ArrayList<Plano> listar() {
        return planos;
    }

    public Plano buscarPorId(int id) {
        for (Plano plano : planos) {
            if (plano.getId() == id) {
                return plano;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, double valor, int duracaoMeses)
            throws ValidacaoException {
        Plano plano = buscarPorId(id);

        if (plano == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar plano inexistente: ID " + id);
            return false;
        }

        plano.setNome(nome);
        plano.setValor(valor);
        plano.setDuracaoMeses(duracaoMeses);
        LoggerService.log("INFO", "Plano atualizado: ID " + plano.getId() + " - " + plano.getNome());
        ArquivoService.salvarPlanos(planos);
        return true;
    }

    public boolean remover(int id) {
        Plano plano = buscarPorId(id);

        if (plano == null) {
            LoggerService.log("WARNING", "Tentativa de remover plano inexistente: ID " + id);
            return false;
        }

        planos.remove(plano);
        LoggerService.log("INFO", "Plano removido: ID " + plano.getId() + " - " + plano.getNome());
        ArquivoService.salvarPlanos(planos);
        return true;
    }
}
