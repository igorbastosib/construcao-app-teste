package dao;

import java.util.ArrayList;

import javax.persistence.Query;

import admin.PageViewers;
import admin.RelatorioAcesso;
import admin.Usuario;
import java.util.List;

/**
 * Classe DAO de persistencia para PageViewers.class
 * @author IgorBastos
 *
 */
public class PageViewersDAO extends AbstractJpaDAO<PageViewers> {
	
	private static PageViewersDAO instance = null;
	
	public static PageViewersDAO getInstance() {
		if (instance == null) {
			instance = new PageViewersDAO();
		}
		return instance;
	}
	
	private PageViewersDAO(){
		super();
		setClazz(PageViewers.class);
	}
	
	/**
	 * 
	 * @return
	 */
	public List geraRelatorioAcessos(){
		Query query = JpaUtil.getEntityManager().createQuery("select a.url, count(a.url) as qtdAcessos from PageViewers a group by a.url order by qtdAcessos desc");
		List<RelatorioAcesso> relatorioAcessos = new ArrayList<RelatorioAcesso>();
		
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++)
		{
			RelatorioAcesso relAcc = new RelatorioAcesso((String) resultList.get(i)[0],  Integer.valueOf(String.valueOf(resultList.get(i)[1])));
			relatorioAcessos.add(relAcc);
		}
		return relatorioAcessos;
	}

	/**
	 * Busca a 10 ultimas paginas acessadas
	 * @return
	 */
	public List dezUltimasPagAcessadas(){
		Query query = JpaUtil.getEntityManager().createQuery("from PageViewers order by datetime desc offset 0 rows fetch next 10 rows only");
		return query.getResultList();
	}
}
