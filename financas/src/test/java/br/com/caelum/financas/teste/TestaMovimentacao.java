package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaMovimentacao {
	@Test
	public void salvaMovimentacaoComConta_sucess() {
		EntityManager	manager	=	new	JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		Conta	conta	=	new	Conta();
		conta.setBanco("Banco	Santander");
		conta.setNumero("99999-9");
		conta.setAgencia("999");
		conta.setTitular("Maria");
		manager.persist(conta);
		Movimentacao	movimentacao	=	new	Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(LocalDateTime.now());
		movimentacao.setDescricao("conta	de	luz	-	abril/2010");
		movimentacao.setValor(new	BigDecimal("54"));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		manager.persist(movimentacao);
		manager.getTransaction().commit();
		manager.close();
	}
}
