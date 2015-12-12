package conexaodb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe de conexao e persistencia no DB. Segue o padrao Singleton, mantendo
 * apenas uma conexao com a base de dados getSessionFactory garante o Singleton
 * 
 * @author Igor de Bastos
 * @created 12/12/2015
 * 
 *          Registro de alteracoes devem ser especificados no fim da classe
 *          seguindo este mesmo padrao
 *
 */
public abstract class HibernateUtil {
	/**
	 * Thread que garante o processamento em multi-processadores, nao se prende
	 * ao processo da Aplicacao
	 */
	private static final ThreadLocal<SessionFactory> threadLocal = new ThreadLocal<SessionFactory>();

	/**
	 * Cria a comunicacao com a base de dados
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			// Cria a SessionFactory baseada nas configuracoes do
			// src\main\resources\hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Garante o padrao de projeto Singleton
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		SessionFactory em = threadLocal.get();
		if (em == null) {
			em = buildSessionFactory();
			;
			threadLocal.set(em);
			createTables();
		}
		return em;
	}

	/**
	 * Finaliza a comunicacao com a base de dados
	 */
	public static void shutdown() {
		SessionFactory em = threadLocal.get();
		if (em != null) {
			// Close caches and connection pools
			em.close();
			threadLocal.set(null);
		}
	}

	/**
	 * Retorna uma sessao para transacoes no banco de dados
	 * 
	 * @return
	 */
	public static Session getSession() {
		getSessionFactory();
		return threadLocal.get().openSession();
	}

	/**
	 * Cria tabelas no BD, se nao existir
	 */
	public static void createTables() {
		String query = "CREATE TABLE IF NOT EXISTS `usuario` (\n" + "`idUsuario` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "`login` varchar(45) DEFAULT NULL,\n" + "`senha` varchar(45) DEFAULT NULL,\n"
				+ "PRIMARY KEY (`idUsuario`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		getSession().createSQLQuery(query).executeUpdate();
	}
}

/**
 * DESCRICAO DA ALTERACAO
 * 
 * @author NOME DO AUTOR
 * @created DATA ALTERACAO
 *
 */