package construcao.app_teste;

import org.hibernate.Session;
import classes.*;
import dao.*;

import conexaodb.HibernateUtil;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario user = new Usuario();
		user.setLogin("login1");
		user.setSenha("senha1");
		
		DAOUsuario.getInstance().createOrUpdate(user);
	}

}
