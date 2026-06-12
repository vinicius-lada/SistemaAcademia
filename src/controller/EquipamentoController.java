package controller;

import exceptions.ValidacaoException;
import model.Equipamento;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class EquipamentoController {
    private final ArrayList<Equipamento> equipamentos;
    private int proximoId;

    public EquipamentoController() {
        equipamentos = ArquivoService.carregarEquipamentos();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() >= proximoId) {
                proximoId = equipamento.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, int quantidade, boolean disponivel)
            throws ValidacaoException {
        Equipamento equipamento = new Equipamento(proximoId, nome, quantidade, disponivel);
        equipamentos.add(equipamento);
        LoggerService.log("INFO", "Equipamento cadastrado: ID " + equipamento.getId() + " - " + equipamento.getNome());
        ArquivoService.salvarEquipamentos(equipamentos);
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

    public boolean atualizar(int id, String nome, int quantidade, boolean disponivel)
            throws ValidacaoException {
        Equipamento equipamento = buscarPorId(id);

        if (equipamento == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar equipamento inexistente: ID " + id);
            return false;
        }

        equipamento.setNome(nome);
        equipamento.setQuantidade(quantidade);
        equipamento.setDisponivel(disponivel);
        LoggerService.log("INFO", "Equipamento atualizado: ID " + equipamento.getId() + " - " + equipamento.getNome());
        ArquivoService.salvarEquipamentos(equipamentos);
        return true;
    }

    public boolean remover(int id) {
        Equipamento equipamento = buscarPorId(id);

        if (equipamento == null) {
            LoggerService.log("WARNING", "Tentativa de remover equipamento inexistente: ID " + id);
            return false;
        }

        equipamentos.remove(equipamento);
        LoggerService.log("INFO", "Equipamento removido: ID " + equipamento.getId() + " - " + equipamento.getNome());
        ArquivoService.salvarEquipamentos(equipamentos);
        return true;
    }
}
