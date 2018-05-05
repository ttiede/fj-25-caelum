package br.com.caelum.financas.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Movimentacao;

public class MovimentacaoDao {

	private EntityManager manager;

	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona(Movimentacao movimentacao) {
		this.manager.persist(movimentacao);
	}

	public void remove(Movimentacao movimentacao) {
		this.manager.remove(movimentacao);
	}

	public Movimentacao update(Movimentacao movimentacao) {
		return  this.manager.merge(movimentacao);
	}

	public Movimentacao busca(Integer id) {
		return this.manager.find(Movimentacao.class, id);
	}

	
	public List<Movimentacao> lista() {
		return this.manager.createQuery("select	movimentacao from Movimentacao movimentacao", Movimentacao.class).getResultList();
	}
}