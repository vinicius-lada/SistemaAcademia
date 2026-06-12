package controller;

import exceptions.ValidacaoException;
import model.Aula;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class AulaController {
    private final ArrayList<Aula> aulas;
    private int proximoId;

    public AulaController() {
        aulas = ArquivoService.carregarAulas();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Aula aula : aulas) {
            if (aula.getId() >= proximoId) {
                proximoId = aula.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, String professor, String horario, int capacidade)
            throws ValidacaoException {
        Aula aula = new Aula(proximoId, nome, professor, horario, capacidade);
        aulas.add(aula);
        LoggerService.log("INFO", "Aula cadastrada: ID " + aula.getId() + " - " + aula.getNome());
        ArquivoService.salvarAulas(aulas);
        proximoId++;
    }

    public ArrayList<Aula> listar() {
        return aulas;
    }

    public Aula buscarPorId(int id) {
        for (Aula aula : aulas) {
            if (aula.getId() == id) {
                return aula;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String professor, String horario, int capacidade)
            throws ValidacaoException {
        Aula aula = buscarPorId(id);

        if (aula == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar aula inexistente: ID " + id);
            return false;
        }

        aula.setNome(nome);
        aula.setProfessor(professor);
        aula.setHorario(horario);
        aula.setCapacidade(capacidade);
        LoggerService.log("INFO", "Aula atualizada: ID " + aula.getId() + " - " + aula.getNome());
        ArquivoService.salvarAulas(aulas);
        return true;
    }

    public boolean remover(int id) {
        Aula aula = buscarPorId(id);

        if (aula == null) {
            LoggerService.log("WARNING", "Tentativa de remover aula inexistente: ID " + id);
            return false;
        }

        aulas.remove(aula);
        LoggerService.log("INFO", "Aula removida: ID " + aula.getId() + " - " + aula.getNome());
        ArquivoService.salvarAulas(aulas);
        return true;
    }
}
