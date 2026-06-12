package controller;

import model.Aula;
import util.LogUtil;
import java.util.ArrayList;

public class AulaController {
    private final ArrayList<Aula> aulas;
    private int proximoId;

    public AulaController() {
        aulas = new ArrayList<>();
        proximoId = 1;
    }

    public void cadastrar(String nome, String professor, String horario, int capacidade) {
        Aula aula = new Aula(proximoId, nome, professor, horario, capacidade);
        aulas.add(aula);
        LogUtil.registrar("INFO", "Aula cadastrada: ID " + aula.getId() + " - " + aula.getNome());
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

    public boolean atualizar(int id, String nome, String professor, String horario, int capacidade) {
        Aula aula = buscarPorId(id);

        if (aula == null) {
            LogUtil.registrar("WARNING", "Tentativa de atualizar aula inexistente: ID " + id);
            return false;
        }

        aula.setNome(nome);
        aula.setProfessor(professor);
        aula.setHorario(horario);
        aula.setCapacidade(capacidade);
        LogUtil.registrar("INFO", "Aula atualizada: ID " + aula.getId() + " - " + aula.getNome());
        return true;
    }

    public boolean remover(int id) {
        Aula aula = buscarPorId(id);

        if (aula == null) {
            LogUtil.registrar("WARNING", "Tentativa de remover aula inexistente: ID " + id);
            return false;
        }

        aulas.remove(aula);
        LogUtil.registrar("INFO", "Aula removida: ID " + aula.getId() + " - " + aula.getNome());
        return true;
    }
}
