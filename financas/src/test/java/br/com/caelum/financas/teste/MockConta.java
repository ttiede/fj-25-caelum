package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.Dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class MockConta {

	@Before
	public void createContaToTests() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		Conta conta = new Conta();
		conta.setTitular("Jose	Roberto");
		conta.setBanco("Banco	do	Brasil");
		conta.setNumero("123456-6");
		conta.setAgencia("0999");
		manager.getTransaction().begin();
		dao.adiciona(conta);
		manager.getTransaction().commit();
		manager.close();
		System.out.println("Conta	gravada	com	sucesso!");
	}

	@Before
	public void salvaMovimentacaoComConta_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		Conta conta = new Conta();
		conta.setTitular("Carlos	Roberto");
		conta.setBanco("Banco	do	Brasil");
		conta.setNumero("123456-6");
		conta.setAgencia("0999");
		manager.getTransaction().begin();
		dao.adiciona(conta);
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setDescricao("conta	de	luz	-	abril/2010");
		movimentacao.setValor(new BigDecimal("54"));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacaoDao.adiciona(movimentacao);
		manager.getTransaction().commit();
		manager.close();
	}

	@Test
	public void testePools() {
		for (int i = 0; i < 10; i++) {
			EntityManager manager = new JPAUtil().getEntityManager();
			manager.getTransaction().begin();
			System.out.println("Criado	EntityManager	número	" + i);
		}
		try {
			Thread.sleep(30	*	1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}