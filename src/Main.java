import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo de teste
        String caminhoArquivo = "C:\\Users\\João Marcelo\\Documents\\codigoC.txt";

        System.out.println(" - Tabela de Símbolos: \n");

        // Inicializa a classe de leitura de tokens
        LeituraToken leitor = new LeituraToken();
        leitor.leituraToken(caminhoArquivo);

        // Lista para armazenar tokens gerados
        List<Token> tokens = leitor.criarTokens(null);


        // Exibe os tokens identificados
        System.out.println("\n - Lista de Tokens: \n");
            for (Token token : tokens) {
            System.out.println(token);
        }

        // Fecha o leitor
        leitor.leitorTxt.close();
    }
}
