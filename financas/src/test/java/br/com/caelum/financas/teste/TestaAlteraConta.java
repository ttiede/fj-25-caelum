package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaAlteraConta {

	@Test
	public void testAlteraConta_success() {
		EntityManager manager = new JPAUtil().getEntityManager();// recupera um
		ContaDao dao = new ContaDao(manager);
		manager.getTransaction().begin();
		Conta conta = dao.busca(2); // usar um ID que exista no banco
		conta.setTitular("Joãozinho");
		manager.getTransaction().commit();
		manager.close();
	}
}