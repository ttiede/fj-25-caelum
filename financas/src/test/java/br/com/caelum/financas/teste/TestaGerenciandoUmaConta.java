package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaGerenciandoUmaConta {
	@Test
	public void managerConta_sucess() {
		// Recupera um EntityManager
		EntityManager manager = new JPAUtil().getEntityManager();
		// Chama o DAO passando um EntityManager
		ContaDao dao = new ContaDao(manager);
		// Abre uma transação
		manager.getTransaction().begin();
		// Busca uma conta gerenciada (managed)
		Conta conta = dao.busca(1); // ID 1 deve existir no banco
		// Altera o titular
		conta.setTitular("Novo	titular");
		// Consolida as modificações
		manager.getTransaction().commit();
		manager.close();
	}
}