    import listatokens.TokenName;

    public class Token {


        private TokenName tipo;

        private String token;

        private int numeroId;


        public Token(TokenName tipo, int numeroId){
            this.tipo = tipo;
            this.numeroId = numeroId;
        }

        public Token(TokenName tipo, String token) {
            this.tipo = tipo;
            this.token = token;
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
            if (tipo == TokenName.ID){
            return "Token" + "-> " + tipo + " -> "+ numeroId;
            }else {
            return "Token" + " -> "+ tipo +" -> "+ token;
            }
        }
    }
