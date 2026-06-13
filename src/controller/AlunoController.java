package controller;

import exceptions.ValidacaoException;
import model.Aluno;
import util.ArquivoService;
import util.LoggerService;
import java.util.ArrayList;

public class AlunoController {
    private final ArrayList<Aluno> alunos;
    private int proximoId;

    public AlunoController() {
        alunos = ArquivoService.carregarAlunos();
        atualizarProximoId();
    }

    private void atualizarProximoId() {
        proximoId = 1;

        for (Aluno aluno : alunos) {
            if (aluno.getId() >= proximoId) {
                proximoId = aluno.getId() + 1;
            }
        }
    }

    public void cadastrar(String nome, String cpf, String telefone)
            throws ValidacaoException {
        Aluno aluno = new Aluno(proximoId, nome, cpf, telefone);
        alunos.add(aluno);
        LoggerService.log("INFO", "Aluno cadastrado: ID " + aluno.getId() + " - " + aluno.getNome());
        ArquivoService.salvarAlunos(alunos);
        proximoId++;
    }

    public ArrayList<Aluno> listar() {
        return alunos;
    }

    public Aluno buscarPorId(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    public boolean atualizar(int id, String nome, String cpf, String telefone)
            throws ValidacaoException {
        Aluno aluno = buscarPorId(id);

        if (aluno == null) {
            LoggerService.log("WARNING", "Tentativa de atualizar aluno inexistente: ID " + id);
            return false;
        }

        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        LoggerService.log("INFO", "Aluno atualizado: ID " + aluno.getId() + " - " + aluno.getNome());
        ArquivoService.salvarAlunos(alunos);
        return true;
    }

    public boolean remover(int id) {
        Aluno aluno = buscarPorId(id);

        if (aluno == null) {
            LoggerService.log("WARNING", "Tentativa de remover aluno inexistente: ID " + id);
            return false;
        }

        alunos.remove(aluno);
        LoggerService.log("INFO", "Aluno removido: ID " + aluno.getId() + " - " + aluno.getNome());
        ArquivoService.salvarAlunos(alunos);
        return true;
    }
}
