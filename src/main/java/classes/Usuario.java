package classes;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe Usuario relacionada aa tabela usuario do DB
 * 
 * @author Igor de Bastos
 * @created 12/12/2015
 * 
 *          Registro de alteracoes devem ser especificados no fim da classe
 *          seguindo este mesmo padrao
 *
 */
@Entity
@Table
public class Usuario implements Serializable{
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idUsuario;
	@Column(name = "login", nullable = false, length = 45)
	private String login; // Aqui não seria melhor LoginUsuario?
	@Column(name = "senha", nullable = false, length = 45)
	private String senha;// Aqui SenhaUsuario?
	

	// Construtores
	public Usuario() {
		super(); //esta é uma variável de rotina?
	}

	// Getters e Setters
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
	// Outros Metodos
}
/**
 * DESCRICAO DA ALTERACAO
 * 
 * @author NOME DO AUTOR
 * @created DATA ALTERACAO
 *
 */
