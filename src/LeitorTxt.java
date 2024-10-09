import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeitorTxt {
    private FileInputStream fileInputStream;
    private int ultimoCaractereLido = -1;  // Armazena o último caractere lido para retrocesso

    public LeitorTxt(String arquivo) {
        try {
            fileInputStream = new FileInputStream(arquivo);
        } catch (IOException ex) {
            Logger.getLogger(LeitorTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int lerProximoCaractere() {
        try {
            // Se um caractere foi retrocedido, retorne-o e limpe a variável
            if (ultimoCaractereLido != -1) {
                int ret = ultimoCaractereLido;
                ultimoCaractereLido = -1;  // Limpa o valor do buffer de retrocesso
                return ret;
            }

            // Caso contrário, leia o próximo caractere do arquivo
            return fileInputStream.read();
        } catch (IOException ex) {
            Logger.getLogger(LeitorTxt.class.getName()).log(Level.SEVERE, null, ex);
            return -1;  // Retorna -1 em caso de erro ou fim do arquivo
        }
    }

    public void retroceder(int caractere) {
        // Armazena o caractere para que ele seja lido novamente na próxima chamada de lerProximoCaractere
        ultimoCaractereLido = caractere;
    }

    public void close() {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
