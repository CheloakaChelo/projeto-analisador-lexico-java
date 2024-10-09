package listatokens;

public enum TokenName {
    PALAVRA_RESERVADA,

    ID,

    NUMERO, STRING_LITERAL,

    //Operadores Atribuição
    OPERADOR_ATRIBUICAO, OPERADOR_ADICAO_ATRIBUICAO, OPERADOR_SUBTRACAO_ATRIBUICAO, OPERADOR_MULTIPLICACAO_ATRIBUICAO,
    OPERADOR_DIVISAO_ATRIBUICAO, OPERADOR_MODULO_ATRIBUICAO,

    //Operadores Unarios
    OPERADOR_INCREMENTO, OPERADOR_DECREMENTO,

    //Operadores Aritméticos
    OPERADOR_SOMA, OPERADOR_SUBTRACAO, OPERADOR_DIVISAO, OPERADOR_RESTO, OPERADOR_MULTIPLICACAO, OPERADOR_POTENCIA,

    //Operadores Relacionais
    OPERADOR_IGUAL, OPERADOR_DIFERENTE, OPERADOR_MAIOR_QUE, OPERADOR_MENOR_QUE,
    OPERADOR_MAIOR_OU_IGUAL, OPERADOR_MENOR_OU_IGUAL,

    //Operadores Lógicos
    OPERADOR_E, OPERADOR_OU, OPERADOR_NOT,

    //Simbolos
    VIRGULA, PONTO, ENDERECO_PONTEIRO,

    //Delimitadores
    ABRE_PARENTESE, FECHA_PARENTESE, ABRE_COLCHETE, FECHA_COLCHETE, ABRE_CHAVE, FECHA_CHAVE, ABRE_ASPAS, FECHA_ASPAS,
    PONTO_E_VIRGULA



}