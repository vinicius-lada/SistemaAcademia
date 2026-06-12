package util;

import model.Aula;
import model.Equipamento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArquivoService {
    private static final String PASTA = "dados";
    private static final String ARQUIVO_AULAS = "dados/aulas.dat";
    private static final String ARQUIVO_EQUIPAMENTOS = "dados/equipamentos.dat";

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

    private static void criarPasta() throws IOException {
        File pasta = new File(PASTA);

        if (!pasta.exists() && !pasta.mkdir()) {
            throw new IOException("Nao foi possivel criar a pasta dados.");
        }
    }
}
