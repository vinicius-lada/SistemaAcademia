package controller;

import model.Equipamento;
import util.LogUtil;
import java.util.ArrayList;

public class EquipamentoController {
    private final ArrayList<Equipamento> equipamentos;
    private int proximoId;

    public EquipamentoController() {
        equipamentos = new ArrayList<>();
        proximoId = 1;
    }

    public void cadastrar(String nome, int quantidade, boolean disponivel) {
        Equipamento equipamento = new Equipamento(proximoId, nome, quantidade, disponivel);
        equipamentos.add(equipamento);
        LogUtil.registrar("INFO", "Equipamento cadastrado: ID " + equipamento.getId() + " - " + equipamento.getNome());
        proximoId++;
    }

    public ArrayList<Equipamento> listar() {
        return equipamentos;
    }

    public Equipamento buscarPorId(int id) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == id) {
                return equipamento;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, int quantidade, boolean disponivel) {
        Equipamento equipamento = buscarPorId(id);

        if (equipamento == null) {
            LogUtil.registrar("WARNING", "Tentativa de atualizar equipamento inexistente: ID " + id);
            return false;
        }

        equipamento.setNome(nome);
        equipamento.setQuantidade(quantidade);
        equipamento.setDisponivel(disponivel);
        LogUtil.registrar("INFO", "Equipamento atualizado: ID " + equipamento.getId() + " - " + equipamento.getNome());
        return true;
    }

    public boolean remover(int id) {
        Equipamento equipamento = buscarPorId(id);

        if (equipamento == null) {
            LogUtil.registrar("WARNING", "Tentativa de remover equipamento inexistente: ID " + id);
            return false;
        }

        equipamentos.remove(equipamento);
        LogUtil.registrar("INFO", "Equipamento removido: ID " + equipamento.getId() + " - " + equipamento.getNome());
        return true;
    }
}
