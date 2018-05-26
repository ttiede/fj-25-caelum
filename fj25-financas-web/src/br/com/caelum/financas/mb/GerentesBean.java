package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;
import br.com.caelum.financas.modelo.GerenteConta;

@Named
@ViewScoped
public class GerentesBean implements Serializable {

	@Inject
	private GerenteDao gerenteDao = new GerenteDao();

	private static final long serialVersionUID = 1L;

	private List<Gerente> gerentes;

	private Gerente gerente = new GerenteConta(); // implementar o getter e setter

	public List<Gerente> getGerentes() {
		if (this.gerentes == null) {
			this.gerentes = gerenteDao.lista();
		}
		return gerentes;
	}

	public void grava() {
		if (this.gerente.getId() == null) {
			gerenteDao.adiciona(gerente);
		} else {
			gerenteDao.altera(gerente);
		}
		this.gerentes = gerenteDao.lista();

		limpaFormularioDoJSF();
	}

	public void remove() {
		gerenteDao.remove(this.gerente);
		this.gerentes = gerenteDao.lista();

		limpaFormularioDoJSF();
	}
	
	private void limpaFormularioDoJSF() {
		this.gerente = new GerenteConta();
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public GerenteDao getGerenteDao() {
		return gerenteDao;
	}

	public void setGerenteDao(GerenteDao gerenteDao) {
		this.gerenteDao = gerenteDao;
	}
}
