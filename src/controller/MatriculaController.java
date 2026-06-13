package controller;

import exceptions.ValidacaoException;
import model.Matricula;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class MatriculaController {
    private final ArrayList<Matricula> matriculas;
    private int proximoId;

    public MatriculaController() {
        matriculas = ArquivoService.carregarMatriculas();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Matricula matricula : matriculas) {
            if (matricula.getId() >= proximoId) {
                proximoId = matricula.getId() + 1;
            }
        }
    }

    public void cadastrar(String nomeAluno, String plano, String dataMatricula, boolean ativa)
            throws ValidacaoException {
        Matricula matricula = new Matricula(proximoId, nomeAluno, plano, dataMatricula, ativa);
        matriculas.add(matricula);
        LoggerService.log("INFO", "Matricula cadastrada: ID " + matricula.getId() + " - " + matricula.getNomeAluno());
        ArquivoService.salvarMatriculas(matriculas);
        proximoId++;
    }

    public ArrayList<Matricula> listar() {
        return matriculas;
    }

    public Matricula buscarPorId(int id) {
        for (Matricula matricula : matriculas) {
            if (matricula.getId() == id) {
                return matricula;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nomeAluno, String plano, String dataMatricula, boolean ativa)
            throws ValidacaoException {
        Matricula matricula = buscarPorId(id);

        if (matricula == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar matricula inexistente: ID " + id);
            return false;
        }

        matricula.setNomeAluno(nomeAluno);
        matricula.setPlano(plano);
        matricula.setDataMatricula(dataMatricula);
        matricula.setAtiva(ativa);
        LoggerService.log("INFO", "Matricula atualizada: ID " + matricula.getId() + " - " + matricula.getNomeAluno());
        ArquivoService.salvarMatriculas(matriculas);
        return true;
    }

    public boolean remover(int id) {
        Matricula matricula = buscarPorId(id);

        if (matricula == null) {
            LoggerService.log("WARNING", "Tentativa de remover matricula inexistente: ID " + id);
            return false;
        }

        matriculas.remove(matricula);
        LoggerService.log("INFO", "Matricula removida: ID " + matricula.getId() + " - " + matricula.getNomeAluno());
        ArquivoService.salvarMatriculas(matriculas);
        return true;
    }
}
