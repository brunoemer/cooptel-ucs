package model;

public class Visitante {
	private int id;
	private int id_usuario;
	private String nome;
	private String paisOrigem;
	
	public Visitante(){
		this.id_usuario = 0;
		this.nome = "";
		this.paisOrigem = "";
	}
	
	public Visitante(String nome, String paisOrigem, int id_usuario, int id){
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.id_usuario = id_usuario;
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public int getIdUsuario(){
		return this.id_usuario;
	}
	
	public void setIdUsuario(int id_usuario){
		this.id_usuario = id_usuario;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getPaisOrigem(){
		return this.paisOrigem;
	}
	
	public void setPaisOrigem(String paisOrigem){
		this.paisOrigem = paisOrigem;
	}
}
