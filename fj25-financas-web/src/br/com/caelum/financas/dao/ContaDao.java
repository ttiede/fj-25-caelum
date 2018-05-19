package br.com.caelum.financas.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import br.com.caelum.financas.modelo.Conta;

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
public class ContaDao {

	@Inject	//@PersistenceContext
	EntityManager manager;
	/*@Resource
	private	UserTransaction	ut;*/
	
	public void adiciona(Conta conta) {
		/*try {
			this.ut.begin();
		} catch (Exception e) {
			throw new EJBException(e);
		}*/
		this.manager.joinTransaction();

		this.manager.persist(conta);
/*		try {
			this.ut.commit();
		} catch (Exception e) {
			try {
				this.ut.rollback();
			} catch (Exception e1) {
				throw new EJBException(e1);
			}
			throw new EJBException(e);
		}*/
	}

	public Conta busca(Integer id) {
		return this.manager.find(Conta.class, id);
	}

	public List<Conta> lista() {
		return this.manager.createQuery("select c from Conta c", Conta.class).getResultList();
	}

	public void remove(Conta conta) {
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.joinTransaction();

		this.manager.remove(contaParaRemover);
	}

	public Conta altera(Conta conta) {
		this.manager.joinTransaction();
		return this.manager.merge(conta);
	}
}
