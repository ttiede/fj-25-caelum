package br.com.caelum.financas.EntityListener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.caelum.financas.modelo.Movimentacao;

public class MovimentacaoListener {
	@PrePersist
	@PreUpdate
	public void preAltera(Movimentacao m) {
		System.out.println("Atualizando	a	data	da	movimentacao");
		m.setData(LocalDateTime.now());
	}
}
