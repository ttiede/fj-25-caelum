package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaPesquisaIdConta {
	@Test
	public void findByIdContas_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		Conta encontrado = dao.busca(1); // usar um ID que exista no banco
		System.out.println(encontrado.getTitular());
		manager.close();
	}
}