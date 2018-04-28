package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaListagemConta {
	@Test
	public void listaContas_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		List<Conta> lista = dao.lista();
		for (Conta conta : lista) {
			System.out.println(conta.getNumero());
		}
		manager.close();
	}
}