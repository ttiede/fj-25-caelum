package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.caelum.financas.Dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestConta extends MockConta {
	@Test
	public void createConta_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		Conta conta = new Conta();
		conta.setTitular("Carlos	Roberto");
		conta.setBanco("Banco	do	Brasil");
		conta.setNumero("123456-6");
		conta.setAgencia("0999");
		manager.getTransaction().begin();
		dao.adiciona(conta);
		manager.getTransaction().commit();
		manager.close();
		System.out.println("Conta	gravada	com	sucesso!");
	}

	@Test
	public void managerConta_sucess() {
		// Recupera um EntityManager
		EntityManager manager = new JPAUtil().getEntityManager();
		// Chama o DAO passando um EntityManager
		ContaDao dao = new ContaDao(manager);
		// Abre uma transação
		manager.getTransaction().begin();
		// Busca uma conta gerenciada (managed)
		Conta conta = dao.busca(2); // ID 2 deve existir no banco
		// Altera o titular
		conta.setTitular("Novo	titular");
		// Consolida as modificações
		manager.getTransaction().commit();
		manager.close();
	}

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

	@Test
	public void findByIdContas_sucess() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		Conta encontrado = dao.busca(1); // usar um ID que exista no banco
		System.out.println(encontrado.getTitular());
		manager.close();
	}

	@Test
	public void testAlteraConta_success() {
		EntityManager manager = new JPAUtil().getEntityManager();// recupera um
		ContaDao dao = new ContaDao(manager);
		manager.getTransaction().begin();
		Conta conta = dao.busca(1); // usar um ID que exista no banco
		conta.setTitular("Joãozinho");
		Conta contaMerged =  dao.update(conta);
		manager.getTransaction().commit();
		manager.close();
	}

	@Test
	public void removeConta_success() {
		EntityManager manager = new JPAUtil().getEntityManager();
		ContaDao dao = new ContaDao(manager);
		manager.getTransaction().begin();
		Conta conta = dao.busca(3); // usar um ID que exista no banco
		dao.remove(conta);
		manager.getTransaction().commit();
		manager.close();
	}
}