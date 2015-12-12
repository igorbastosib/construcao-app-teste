package dao;

import classes.*;
import java.util.List;

/**
 * Classe de persistencia para tabela usuario no DB
 * 
 * @author Igor de Bastos
 * @created 12/12/2015
 * 
 *          Registro de alteracoes devem ser especificados no fim da classe
 *          seguindo este mesmo padrao
 *
 */
public class DAOUsuario extends AbstractJpaDAO<Usuario> {
	private static DAOUsuario instance = null;

	private DAOUsuario() {
		super();
		setClazz(Usuario.class);
	}

	public static DAOUsuario getInstance() {
		if (instance == null) {
			instance = new DAOUsuario();
		}
		return instance;
	}
        
        //Outros metodos
        /**
         * Valida usuario e senha no banco de dados
         * @param login
         * @param senha
         * @return
         */
        public boolean validarLoginSenha(String login, String senha){
            List<Usuario> users = findAll(new String[] { "varchar","varchar" }, new String[] { "login","senha" }, new String[] { login,senha }, new String[] { "=","=" });
            
            if(users != null && users.size() == 1){
                return true;
            }
            return false;
        }
}
/**
 * DESCRICAO DA ALTERACAO
 * 
 * @author NOME DO AUTOR
 * @created DATA ALTERACAO
 *
 */