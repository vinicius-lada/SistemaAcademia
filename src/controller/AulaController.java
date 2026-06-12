package controller;

import model.Aula;
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
            return false;
        }

        aula.setNome(nome);
        aula.setProfessor(professor);
        aula.setHorario(horario);
        aula.setCapacidade(capacidade);
        return true;
    }

    public boolean remover(int id) {
        Aula aula = buscarPorId(id);

        if (aula == null) {
            return false;
        }

        aulas.remove(aula);
        return true;
    }
}