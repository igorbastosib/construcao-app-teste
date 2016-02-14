package admin;

import java.util.Date;

import javax.persistence.*;

/**
 * Classe de representacao dos dados para geracao de relatorios
 * @author IgorBastos
 *
 */

@Entity
@Table(name = "pageViewers")
public class PageViewers {
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Column(name = "url", length = 200, unique = false, nullable = false)
	private String url;

	@Column(name = "ip", length = 20, nullable = false)
	private String ip;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateTime", nullable = true)
	private Date dateTime;

	//Construtores
	public PageViewers(){}
	
	public PageViewers(String url, String ip, Date dateTime){
		this.setUrl(url);
		this.setIp(ip);
		this.setDateTime(dateTime);
	}

	//Getters & Setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
