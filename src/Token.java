    import listatokens.TokenName;

    public class Token {

        private int numero;

        private TokenName tipo;

        private String token;

        public Token(){

        }

        public Token(int numero, TokenName tipo, String token) {
            this.numero = numero;
            this.tipo = tipo;
            this.token = token;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public TokenName getTipo() {
            return tipo;
        }

        public void setTipo(TokenName tipo) {
            this.tipo = tipo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return numero + " -> "+ tipo +" -> "+ token;
        }
    }
