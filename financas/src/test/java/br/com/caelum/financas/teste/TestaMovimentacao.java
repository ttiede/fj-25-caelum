package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.Dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacao extends MockConta {
	@Test
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
	public void buscaContaDaMovimentacao_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);
		Movimentacao movimentacao = movimentacaoDao.busca(1);
		System.out.println(movimentacao .getConta().getTitular());
	}
	
	@Test
	public void salvaMovimentacaoSemDataComConta_sucess() {
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
		movimentacao.setDescricao("conta	de	luz	-	abril/2010");
		movimentacao.setValor(new BigDecimal("54"));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacaoDao.adiciona(movimentacao);
		manager.getTransaction().commit();

		System.out.println(movimentacao.getData());
		manager.close();
	}

	
}
