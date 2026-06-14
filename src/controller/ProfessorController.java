package controller;

import exceptions.ValidacaoException;
import model.Professor;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class ProfessorController {
    private final ArrayList<Professor> professores;
    private int proximoId;

    public ProfessorController() {
        professores = ArquivoService.carregarProfessores();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Professor professor : professores) {
            if (professor.getId() >= proximoId) {
                proximoId = professor.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, String especialidade, String telefone)
            throws ValidacaoException {
        Professor professor = new Professor(proximoId, nome, especialidade, telefone);
        professores.add(professor);
        LoggerService.log("INFO", "Professor cadastrado: ID " + professor.getId() + " - " + professor.getNome());
        ArquivoService.salvarProfessores(professores);
        proximoId++;
    }

    public ArrayList<Professor> listar() {
        return professores;
    }

    public Professor buscarPorId(int id) {
        for (Professor professor : professores) {
            if (professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String especialidade, String telefone)
            throws ValidacaoException {
        Professor professor = buscarPorId(id);

        if (professor == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar professor inexistente: ID " + id);
            return false;
        }

        professor.setNome(nome);
        professor.setEspecialidade(especialidade);
        professor.setTelefone(telefone);
        LoggerService.log("INFO", "Professor atualizado: ID " + professor.getId() + " - " + professor.getNome());
        ArquivoService.salvarProfessores(professores);
        return true;
    }

    public boolean remover(int id) {
        Professor professor = buscarPorId(id);

        if (professor == null) {
            LoggerService.log("WARNING", "Tentativa de remover professor inexistente: ID " + id);
            return false;
        }

        professores.remove(professor);
        LoggerService.log("INFO", "Professor removido: ID " + professor.getId() + " - " + professor.getNome());
        ArquivoService.salvarProfessores(professores);
        return true;
    }
}
