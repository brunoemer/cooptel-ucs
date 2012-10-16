package model;

public class Colaborador {
	private int id;
	private int id_usuario;
	private String cpf;
	private String nome;
	private String endereco;
	private String email;
	private boolean ativo;
	
	public Colaborador(){
		this.id_usuario = 0;
		this.cpf = "";
		this.nome = "";
		this.endereco = "";
		this.email = "";
		this.ativo = false;
	}
	
	public Colaborador(String cpf, String nome, String endereco, String email, boolean ativo, int id_usuario, int id){
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.ativo = ativo;
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
	
	public String getCpf(){
		return this.cpf;
	}
	
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public void setEndereco(String endereco){
		this.endereco = endereco;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public boolean getAtivo(){
		return this.ativo;
	}
	
	public void setAtivo(boolean ativo){
		this.ativo = ativo;
	}
}
