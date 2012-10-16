package controller;

import persistence.UsuarioPersistence;
import model.Usuario;

public class UsuarioController {
    
    private static Usuario usuarioLogado;

	private UsuarioPersistence usuarioPersistence;

	public UsuarioController() {
		this.usuarioPersistence = new UsuarioPersistence();
	}

	public Usuario consultar(Usuario u) {
		return this.usuarioPersistence.consultar(u);
	}
        
	public int cadastrar(Usuario u) {
		return usuarioPersistence.inserir(u);
	}
        
    public boolean logar(Usuario u){
        Usuario usuario = usuarioPersistence.logar(u);
        if(usuario != null){
            UsuarioController.usuarioLogado = usuario;
            return true;
        } else {
            return false;
        }
    }
    
    /* Statics para usuário logado */
    public static Usuario getUsuarioLogado(){
        return UsuarioController.usuarioLogado;
    }
}
