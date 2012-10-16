package model;

public class Usuario {    
    public static int TIPO_ADMINISTRADOR = 0;
    public static int TIPO_COLABORADOR = 1;
    public static int TIPO_VISITANTE = 2;
    
    private int id;
    private String login;
    private String senha;
    private int tipo;
    
    public Usuario(){}

    public Usuario(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
