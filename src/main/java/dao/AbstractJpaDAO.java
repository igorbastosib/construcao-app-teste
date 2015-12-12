package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import conexaodb.HibernateUtil;

/**
 * Classe de reuso para Classes DAO
 * 
 * @author Igor de Bastos
 * @created 12/12/2015
 *
 * @param <T>
 * 
 *            Registro de alteracoes devem ser especificados no fim da classe
 *            seguindo este mesmo padrao
 * 
 */
public abstract class AbstractJpaDAO<T> {
	private Session session = null;
	private Class<T> clazz;

	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	/**
	 * Busca um registro pesquisando por um ID int
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findOne(final int id) {
		session = HibernateUtil.getSession();
		T entityReturn = (T) session.get(clazz, id);
		session.close();
		return entityReturn;
	}

	/**
	 * Busca um registro pesquisando por um ID varchar
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findOne(final String id) {
		session = HibernateUtil.getSession();
		T entityReturn = (T) session.get(clazz, id);
		session.close();
		return entityReturn;
	}

	/**
	 * Busca vos registros no BD, organize clumnsNames e columnsValues de forma
	 * correta columnsNames[i] typeCompare[i] colunmsValues[i], pois sra a clausula WHERE
	 * 
	 * @param columnsNames
	 * @param columnsValues
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String[] columnType, String[] columnsNames, String[] columnsValues, String[] typeCompare) {
		session = HibernateUtil.getSession();
		Query q = session.createQuery("from " + clazz.getName() + createSqlFindAll(columnType,columnsNames,columnsValues,typeCompare));
		List<T> resultList = q.list();
		session.close();
		return resultList;
	}

	/**
	 * Cria a clausula where para filtrar a pesquisa 
	 * @param columnType
	 * @param columnsNames
	 * @param columnsValues
	 * @param typeCompare
	 * @return
	 */
	private String createSqlFindAll(String[] columnType, String[] columnsNames, String[] columnsValues, String[] typeCompare){
		String whereSql = "";
		/**
		 * Se as colunas e valores tiverem sido preenchidas, entao gera a
		 * clausula WHERE
		 */
		if (columnType != null && columnsNames != null && columnsValues != null && typeCompare != null &&
			columnType.length > 0 && columnsNames.length > 0 && columnsValues.length > 0 && typeCompare.length > 0
				&& columnsNames.length == columnsValues.length) {
			whereSql = " where ";
			for (int i = 0; i < columnsNames.length; i++) {
				String stringType = "";
				if(!(columnType[i].equals("int") || columnType[i].equals("numeric"))){
					stringType = "'";
				}
				/**
				 * Se ja tem clausulas where
				 */
				if(i > 0){
					whereSql = whereSql + " and ";
				}
				
				/**
				 * Clausua where criada ou adicionado os ands
				 */
				whereSql = whereSql + " " + columnsNames[i] + " " + typeCompare[i] + " "+stringType+ columnsValues[i] +stringType;
			}
		}
		return whereSql;
	}

	/**
	 * Insere ou atualiza, se ja existir, o registro no banco de dados
	 * @param entity
	 */
	public void createOrUpdate(final T entity) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Deleta o registro do BD
	 * @param entity
	 */
	public void delete(final T entity) {
		session = HibernateUtil.getSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Deleta o registro do BD
	 * @param entity
	 */
	public void delete(final int entityId) {
		final T entity = findOne(String.valueOf(entityId));
		delete(entity);
	}
}

/**
 * DESCRICAO DA ALTERACAO
 * 
 * @author NOME DO AUTOR
 * @created DATA ALTERACAO
 *
 */

/**
 * Refatoracao, alteracoes sugeridas pelo proprio IDE; Adicionado findOne
 * buscando ID String para as tabelas com ID VARCHAR como Empresa, Pessoa,
 * Funcionario, etc
 * 
 * @author Igor de Bastos
 * @created 01/12/2015
 *
 */

/**
 * Refatoracao, adicionado a opcao que valida o tipo dos campos pesquisados na metodo findAll
 * 
 * @author Igor de Bastos
 * @created 03/12/2015
 *
 */