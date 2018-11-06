package lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta classe representa um cliente.
 * 
 * @author Wallyngson Guedes
 *
 */
public class Cliente implements Comparable<Cliente> {

	private String cpf, nome, email, localizacao;
	private List<Conta> contas;
	private Map<String, Debito> debitos;
	private Fornecedor fornecedor;
	
	public Cliente(String cpf, String nome, String email, String localizacao) {
		this.parametrosValidos( nome, email, localizacao);

		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localizacao;
		this.contas = new ArrayList<>();
		this.debitos = new HashMap<>();
	}
	
	/**
	 * Verifica se os parametros sao validos.
	 * 
	 * @param nome
	 * @param email
	 * @param localizacao
	 */
	private void parametrosValidos(String nome, String email, String localizacao) {
		if (nome.isEmpty() || nome == null)
			throw new IllegalArgumentException("nome nao pode ser vazio ou nulo.");
		if (email.isEmpty() || email == null)
			throw new IllegalArgumentException("email nao pode ser vazio ou nulo.");
		if(localizacao.isEmpty() || localizacao == null)
			throw new IllegalArgumentException("localizacao nao pode ser vazia ou nula.");
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	public String getCPF() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}

	// CONTAS
	
	public void adicionaCompra(String fornecedor, String data, String nomeProduto, String descricao) {
		this.validaProduto(nomeProduto, descricao);
		
		this.contas.add(new Conta(fornecedor, data, nomeProduto, descricao));
		this.debitos.put(fornecedor, new Debito(this.fornecedor.retornaPreco(nomeProduto, descricao)));
	}
	
	private void validaProduto(String nomeProduto, String descricao) {
		this.fornecedor.validarProduto(nomeProduto, descricao);
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente novo = (Cliente) obj;
			return this.getCPF().equals(novo.getCPF());
		}
		return false;
	}

	@Override
	public int compareTo(Cliente c1) {
		return this.getNome().compareTo(c1.getNome());

	}


}
