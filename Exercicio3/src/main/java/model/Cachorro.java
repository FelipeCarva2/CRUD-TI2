package model;

public class Cachorro {
	private int codigo;
	private String nome;
	private String raca;
	private char sexo;
	
	public Cachorro(int codigo, String nome, String raca, char sexo) {
		this.codigo = codigo;
		this.nome = nome;
		this.raca = raca;
		this.sexo = sexo;
	}
	public Cachorro() {
		this.codigo = -1;
		this.nome = "";
		this.raca = "";
		this.sexo = '*';
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "Cachorro [codigo=" + codigo + ", nome=" + nome + ", raca=" + raca + ", sexo=" + sexo + "]";
	}
}