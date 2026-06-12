package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogUtil {
    private static final String PASTA = "logs";
    private static final String ARQUIVO = "logs/log.txt";

    public static void registrar(String tipo, String mensagem) {
        try {
            File pasta = new File(PASTA);

            if (!pasta.exists() && !pasta.mkdir()) {
                throw new IOException("Nao foi possivel criar a pasta de logs.");
            }

            FileWriter writer = new FileWriter(ARQUIVO, true);
            String linha = "[" + LocalDateTime.now() + "] " + tipo + " - " + mensagem + "\n";
            writer.write(linha);
            writer.close();
        } catch (IOException erro) {
            System.out.println("Erro ao registrar log.");
        }
    }
}
