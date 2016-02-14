package admin;

/**
 * Classe para apresentacao do relatorio de acesso na relatorio.jsp
 * @author IgorBastos
 *
 */
public class RelatorioAcesso {
	//Atributos
	private String url;
	private int qtdAcessos;
	
	//Construtores
	public RelatorioAcesso(){}
	
	public RelatorioAcesso(String url, int qtdAcessos){
		this.url = url;
		this.qtdAcessos = qtdAcessos;
	}

	//Getters & Setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getQtdAcessos() {
		return qtdAcessos;
	}

	public void setQtdAcessos(int qtdAcessos) {
		this.qtdAcessos = qtdAcessos;
	}
	
}
