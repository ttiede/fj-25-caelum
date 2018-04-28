package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRemoveConta {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		manager.getTransaction().begin();
		Conta conta = dao.busca(1); // usar um ID que exista no banco
		dao.remove(conta);
		manager.getTransaction().commit();
		manager.close();
	}

}
