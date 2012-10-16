package model;

import java.util.ArrayList;
import java.util.List;

public class Acomodacao {
	private int id;
	private int id_colaborador;
	private int tipo;
	private boolean cafe;
	private String endereco;
	private double latitude;
	private double longitude;
	private String foto1;
	private String foto2;
	private String fotoExterna;
	private float valordiario;
	private String descricao;
	private List<Disponibilidade> disponibilidades;
	
	public Acomodacao() {
		this.id_colaborador = 0;
		this.tipo = 0;
		this.cafe = true;
		this.endereco = "";
		this.latitude = 0.00;
		this.longitude = 0.00;
		this.foto1 = "";
		this.foto2 = "";
		this.fotoExterna = "";
		this.valordiario = 0;
		this.descricao = "";
		this.disponibilidades = new ArrayList<Disponibilidade>();
	}
	
	public Acomodacao(int id_colaborador, int tipo, boolean cafe,
			String endereco, double latitude, double longitude, String foto1, String foto2, 
			String fotoExterna, float valordiario, String descricao, int id, List<Disponibilidade> disponibilidades) {
		super();
		this.id_colaborador = id_colaborador;
		this.tipo = tipo;
		this.cafe = cafe;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.fotoExterna = fotoExterna;
		this.valordiario = valordiario;
		this.descricao = descricao;
		this.id = id;
		this.disponibilidades = disponibilidades;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getIdColaborador() {
		return id_colaborador;
	}
	
	public void setIdColaborador(int id_colaborador) {
		this.id_colaborador = id_colaborador;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public boolean isCafe() {
		return cafe;
	}
	
	public void setCafe(boolean cafe) {
		this.cafe = cafe;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getFoto1() {
		return foto1;
	}
	
	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}
	
	public String getFoto2() {
		return foto2;
	}
	
	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}
	
	public String getFotoExterna() {
		return fotoExterna;
	}

	public void setFotoExterna(String fotoExterna) {
		this.fotoExterna = fotoExterna;
	}

	public float getValorDiario() {
		return valordiario;
	}
	
	public void setValorDiario(float valordiario) {
		this.valordiario = valordiario;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Disponibilidade> getDisponibilidades(){
		return this.disponibilidades;
	}
	
	public void setDisponibilidades(List<Disponibilidade> disponibilidades){
		this.disponibilidades = disponibilidades;
	}
}
