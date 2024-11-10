import listatokens.TokenName;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeituraToken {
    LeitorTxt leitorTxt;
    int numerotokens = 0;


    public void leituraToken(String arquivo){
        leitorTxt = new LeitorTxt(arquivo);
    }

    public List<Token> criarTokens(Token token){
        List<Token> listaTokens = new ArrayList<>();

        while ((token = nextToken()) != null){
            listaTokens.add(token);
        }
        return listaTokens;
    }

    public Token nextToken(){
        int chLido;
        StringBuilder palavra = new StringBuilder();

        while ((chLido = leitorTxt.lerProximoCaractere()) != -1){
            char ch = (char) chLido;

            if (ch == ' ' || ch == '\n'){
                continue;
            }

            if(ch == '/'){
                int nextCh = leitorTxt.lerProximoCaractere();
                if (nextCh == '/'){

                    while((nextCh = leitorTxt.lerProximoCaractere()) != -1 && nextCh != '\n'){

                    }
                    continue;
                }else if (nextCh == '*'){
                    while ((chLido = leitorTxt.lerProximoCaractere()) != -1){
                        if(chLido == '*'){
                            int proximoChBloco = leitorTxt.lerProximoCaractere();
                            if (proximoChBloco == '/'){
                                break;
                            }else {
                                leitorTxt.retroceder(proximoChBloco);
                            }
                        }
                    }
                    continue;
                }else {
                    leitorTxt.retroceder(nextCh);
                }
            }

            if (ch == '"'){
                StringBuilder stringLiteral = new StringBuilder();
                while((chLido = leitorTxt.lerProximoCaractere()) != -1){
                    ch = (char) chLido;

                    if (ch == '"'){
                        break;
                    }
                    if (ch == '\\'){
                        leitorTxt.lerProximoCaractere();
                        ch = (char) chLido;
                    }

                    stringLiteral.append(ch);
                }
                return new Token(TokenName.STRING_LITERAL, stringLiteral.toString());

            }

            if (Character.isLetter(ch) || ch == '_'){
                palavra.append(ch);
                while((chLido = leitorTxt.lerProximoCaractere()) != -1){
                    ch = (char) chLido;
                    if(Character.isLetterOrDigit(ch) || ch == '_'){
                        palavra.append(ch);
                    }else {
                        if(ch == '.'){
                            leitorTxt.retroceder(chLido);
                            String idAtual = palavra.toString();
                            if (!idAtual.isEmpty()){
                                return new Token(TokenName.ID, idAtual);
                            }
                            return new Token(TokenName.PONTO, ".");
                        }
                        leitorTxt.retroceder(chLido);
                        break;

                    }

                }
                return verificarId(palavra.toString());
            }

            if (Character.isDigit(ch)){
                palavra.append(ch);
                while((chLido = leitorTxt.lerProximoCaractere()) != -1){
                    ch = (char) chLido;
                    if (Character.isDigit(ch)){
                        palavra.append(ch);
                    }else {
                        leitorTxt.retroceder(chLido);
                        break;
                    }

                }
                return verificaNumero(palavra.toString());
            }

            //Delimitadores
            switch(ch){
                case '(':
                    return new Token(TokenName.ABRE_PARENTESE, "(");
                case ')':
                    return new Token(TokenName.FECHA_PARENTESE, ")");
                case '[':
                    return new Token(TokenName.ABRE_COLCHETE, "[");
                case ']':
                    return new Token(TokenName.FECHA_COLCHETE, "]");
                case '{':
                    return new Token(TokenName.ABRE_CHAVE, "{");
                case '}':
                    return new Token(TokenName.FECHA_CHAVE, "}");
                case ',':
                    return new Token(TokenName.VIRGULA, ",");
                case '.':
                    return new Token(TokenName.PONTO, ".");
                case ':':
                    return new Token(TokenName.DOIS_PONTOS, ":");
                case ';':
                    return new Token(TokenName.PONTO_E_VIRGULA, ";");
            }

            //Operadores
            switch(ch){
                case '+':
                    int nxtCh;
                    if ((nxtCh = leitorTxt.lerProximoCaractere()) == '+'){
                        return new Token(TokenName.OPERADOR_INCREMENTO, "++");
                    }else if ((nxtCh = leitorTxt.lerProximoCaractere()) == '='){
                        return new Token(TokenName.OPERADOR_ADICAO_ATRIBUICAO, "+=");
                    }else {
                        return new Token(TokenName.OPERADOR_SOMA, "+");
                    }
                case '-':

                    if ((nxtCh = leitorTxt.lerProximoCaractere()) == '-'){
                        return new Token(TokenName.OPERADOR_DECREMENTO, "--");
                    } else if ((nxtCh = leitorTxt.lerProximoCaractere()) == '=') {
                        return new Token(TokenName.OPERADOR_SUBTRACAO_ATRIBUICAO, "-=");
                    }else {
                        return new Token(TokenName.OPERADOR_SUBTRACAO, "-");
                    }
                case '*':
                    if ((nxtCh = leitorTxt.lerProximoCaractere()) == '='){
                        return new Token(TokenName.OPERADOR_MULTIPLICACAO_ATRIBUICAO, "*=");
                    }else {
                        return new Token(TokenName.OPERADOR_MULTIPLICACAO, "*");
                    }
                case '/':
                    if((nxtCh = leitorTxt.lerProximoCaractere())  == '='){
                        return new Token(TokenName.OPERADOR_DIVISAO_ATRIBUICAO, "/=");
                    }else {
                        return new Token(TokenName.OPERADOR_DIVISAO, "/");
                    }
                case '%': if ((nxtCh = leitorTxt.lerProximoCaractere())  == '='){

                        return new Token(TokenName.OPERADOR_MODULO_ATRIBUICAO, "%=");
                    }else {

                        return new Token(TokenName.OPERADOR_RESTO, "%");
                }
                case '=':
                    if ((nxtCh = leitorTxt.lerProximoCaractere())  == '='){
                        return new Token(TokenName.OPERADOR_IGUAL, "==");
                    }else {
                        return new Token(TokenName.OPERADOR_ATRIBUICAO, "=");
                    }
                case '>':
                    if ((nxtCh = leitorTxt.lerProximoCaractere())  == '='){
                        return new Token(TokenName.OPERADOR_MAIOR_OU_IGUAL, ">=");
                    }else {
                        return new Token(TokenName.OPERADOR_MAIOR_QUE, ">");
                    }
                case '<':
                    if ((nxtCh = leitorTxt.lerProximoCaractere())  == '='){
                        return new Token(TokenName.OPERADOR_MENOR_OU_IGUAL, "<=");
                    }else {
                        return new Token(TokenName.OPERADOR_MENOR_QUE, "<");
                    }
                case '!':
                    if((nxtCh = leitorTxt.lerProximoCaractere())  == '='){
                        return new Token(TokenName.OPERADOR_DIFERENTE, "!=");
                    } else {
                        leitorTxt.retroceder(nxtCh);
                        return new Token(TokenName.OPERADOR_NOT, "!");
                    }
                case '|':
                    if ((nxtCh = leitorTxt.lerProximoCaractere()) == '|'){
                        return new Token(TokenName.OPERADOR_OU, "|");
                    }
                case '&':
                    if ((nxtCh = leitorTxt.lerProximoCaractere()) == '&'){
                        return new Token(TokenName.OPERADOR_E, "&&");
                    }else {
                        leitorTxt.retroceder(nxtCh);
                        return new Token(TokenName.OPERADOR_REFERENCIA, "&");
                    }
            }

        }
        return null;
    }


    private int numeroId = 0;
    private Map<String, Integer> idMap = new HashMap<>();

    private Token verificarId(String token){
        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(token);


        if (mat.matches()){
            if (isKeyWord(token)){
                return new Token(TokenName.PALAVRA_RESERVADA, token);
            }

            if (!idMap.containsKey(token)) {
                numeroId++;
                idMap.put(token, numeroId);
                System.out.println(" ID " + numeroId + ": " + token);
            }

            int atualId = idMap.get(token);
            return new Token(TokenName.ID, atualId);
        }

        return null;
    }

    private boolean isKeyWord(String palavra){
        String[] keyWords = { "auto", "break", "case", "char", "const", "continue",
                "default", "do", "double", "else", "enum", "extern", "float", "for", "goto",
        "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
        "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};

        for (String k : keyWords){
            if (palavra.equals(k)){
                return true;
            }
        }
        return false;
    }

    private Token verificaNumero(String token){
        String regexNum = "^[0-9]*$";
        Pattern pat = Pattern.compile(regexNum);
        Matcher mat = pat.matcher(token);

        if (mat.matches()){
            return new Token(TokenName.NUMERO, token);
        }
       return null;
    }

}
