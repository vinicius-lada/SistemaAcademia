package util;

import model.Aluno;
import model.Aula;
import model.Equipamento;
import model.Matricula;
import model.Pagamento;
import model.Plano;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArquivoService {
    private static final String PASTA = "dados";
    private static final String ARQUIVO_ALUNOS = "dados/alunos.dat";
    private static final String ARQUIVO_AULAS = "dados/aulas.dat";
    private static final String ARQUIVO_EQUIPAMENTOS = "dados/equipamentos.dat";
    private static final String ARQUIVO_MATRICULAS = "dados/matriculas.dat";
    private static final String ARQUIVO_PLANOS = "dados/planos.dat";
    private static final String ARQUIVO_PAGAMENTOS = "dados/pagamentos.dat";

    public static void salvarAlunos(ArrayList<Aluno> alunos) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_ALUNOS)
            );

            out.writeObject(alunos);
            out.close();

            LoggerService.log("INFO", "Alunos salvos com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar alunos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Aluno> carregarAlunos() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_ALUNOS)
            );

            ArrayList<Aluno> alunos = (ArrayList<Aluno>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Alunos carregados com sucesso.");
            return alunos;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhum aluno carregado.");
            return new ArrayList<>();
        }
    }

    public static void salvarAulas(ArrayList<Aula> aulas) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_AULAS)
            );

            out.writeObject(aulas);
            out.close();

            LoggerService.log("INFO", "Aulas salvas com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar aulas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Aula> carregarAulas() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_AULAS)
            );

            ArrayList<Aula> aulas = (ArrayList<Aula>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Aulas carregadas com sucesso.");
            return aulas;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhuma aula carregada.");
            return new ArrayList<>();
        }
    }

    public static void salvarEquipamentos(ArrayList<Equipamento> equipamentos) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_EQUIPAMENTOS)
            );

            out.writeObject(equipamentos);
            out.close();

            LoggerService.log("INFO", "Equipamentos salvos com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar equipamentos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Equipamento> carregarEquipamentos() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_EQUIPAMENTOS)
            );

            ArrayList<Equipamento> equipamentos = (ArrayList<Equipamento>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Equipamentos carregados com sucesso.");
            return equipamentos;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhum equipamento carregado.");
            return new ArrayList<>();
        }
    }

    public static void salvarMatriculas(ArrayList<Matricula> matriculas) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_MATRICULAS)
            );

            out.writeObject(matriculas);
            out.close();

            LoggerService.log("INFO", "Matriculas salvas com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar matriculas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Matricula> carregarMatriculas() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_MATRICULAS)
            );

            ArrayList<Matricula> matriculas = (ArrayList<Matricula>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Matriculas carregadas com sucesso.");
            return matriculas;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhuma matricula carregada.");
            return new ArrayList<>();
        }
    }

    public static void salvarPlanos(ArrayList<Plano> planos) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_PLANOS)
            );

            out.writeObject(planos);
            out.close();

            LoggerService.log("INFO", "Planos salvos com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar planos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Plano> carregarPlanos() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_PLANOS)
            );

            ArrayList<Plano> planos = (ArrayList<Plano>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Planos carregados com sucesso.");
            return planos;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhum plano carregado.");
            return new ArrayList<>();
        }
    }

    public static void salvarPagamentos(ArrayList<Pagamento> pagamentos) {
        try {
            criarPasta();

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(ARQUIVO_PAGAMENTOS)
            );

            out.writeObject(pagamentos);
            out.close();

            LoggerService.log("INFO", "Pagamentos salvos com sucesso.");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar pagamentos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Pagamento> carregarPagamentos() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(ARQUIVO_PAGAMENTOS)
            );

            ArrayList<Pagamento> pagamentos = (ArrayList<Pagamento>) in.readObject();
            in.close();

            LoggerService.log("INFO", "Pagamentos carregados com sucesso.");
            return pagamentos;
        } catch (Exception e) {
            LoggerService.log("WARNING", "Nenhum pagamento carregado.");
            return new ArrayList<>();
        }
    }

    private static void criarPasta() throws IOException {
        File pasta = new File(PASTA);

        if (!pasta.exists() && !pasta.mkdir()) {
            throw new IOException("Nao foi possivel criar a pasta dados.");
        }
    }
}
