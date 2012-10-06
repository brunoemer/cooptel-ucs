package model;

public class Colaborador {
	private int id;
	private String cpf;
	private String nome;
	private String endereco;
	private String email;
	private boolean ativo;
	
	public Colaborador(){
		this.cpf = "";
		this.nome = "";
		this.endereco = "";
		this.email = "";
		this.ativo = false;
	}
	
	public Colaborador(String cpf, String nome, String endereco, String email, boolean ativo, int id){
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.ativo = ativo;
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
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
