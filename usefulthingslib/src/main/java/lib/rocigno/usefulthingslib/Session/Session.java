package lib.rocigno.usefulthingslib.Session;

/**
 * Um simples model de sessão para guardar alguns dados temporios do usuário
 */
public class Session {
    private static String nome_completo;
    private static String nick;
    private static int id;
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Session.token = token;
    }

    public static String getNome_completo() {
        return nome_completo;
    }

    public static void setNome_completo(String nome_completo) {
        Session.nome_completo = nome_completo;
    }

    public static String getNick() {
        return nick;
    }

    public static void setNick(String nick) {
        Session.nick = nick;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

}
