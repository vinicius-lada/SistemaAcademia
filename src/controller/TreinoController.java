package controller;

import exceptions.ValidacaoException;
import model.Treino;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class TreinoController {
    private final ArrayList<Treino> treinos;
    private int proximoId;

    public TreinoController() {
        treinos = ArquivoService.carregarTreinos();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Treino treino : treinos) {
            if (treino.getId() >= proximoId) {
                proximoId = treino.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, String objetivo, String descricao, String nivel)
            throws ValidacaoException {
        Treino treino = new Treino(proximoId, nome, objetivo, descricao, nivel);
        treinos.add(treino);
        LoggerService.log("INFO", "Treino cadastrado: ID " + treino.getId() + " - " + treino.getNome());
        ArquivoService.salvarTreinos(treinos);
        proximoId++;
    }

    public ArrayList<Treino> listar() {
        return treinos;
    }

    public Treino buscarPorId(int id) {
        for (Treino treino : treinos) {
            if (treino.getId() == id) {
                return treino;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String objetivo, String descricao, String nivel)
            throws ValidacaoException {
        Treino treino = buscarPorId(id);

        if (treino == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar treino inexistente: ID " + id);
            return false;
        }

        treino.setNome(nome);
        treino.setObjetivo(objetivo);
        treino.setDescricao(descricao);
        treino.setNivel(nivel);
        LoggerService.log("INFO", "Treino atualizado: ID " + treino.getId() + " - " + treino.getNome());
        ArquivoService.salvarTreinos(treinos);
        return true;
    }

    public boolean remover(int id) {
        Treino treino = buscarPorId(id);

        if (treino == null) {
            LoggerService.log("WARNING", "Tentativa de remover treino inexistente: ID " + id);
            return false;
        }

        treinos.remove(treino);
        LoggerService.log("INFO", "Treino removido: ID " + treino.getId() + " - " + treino.getNome());
        ArquivoService.salvarTreinos(treinos);
        return true;
    }
}
