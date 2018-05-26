package br.com.caelum.financas.mb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validator;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.validator.NumeroEAgenciaValidator;

@Named
@RequestScoped
public class ValidacaoBean {

	@Inject
	private Validator validator;

	private Conta conta = new Conta();

	public void validar() {
		System.out.println("Validando a conta");
		Set<ConstraintViolation<Conta>> erros = validator.validate(conta);
		for (ConstraintViolation<Conta> erro : erros) {
			geraMensagemJsf(erro);
		}

	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = NumeroEAgenciaValidator.class)
	public @interface NumeroEAgencia {
		String message() default "{br.com.caelum.financas.validator.NumeroEAgencia.message}";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}
	
	public Conta getConta() {
		return conta;
	}

	/**
	 * Esse metodo disponibiliza uma mensagem para a tela JSF.
	 */
	private void geraMensagemJsf(ConstraintViolation<Conta> erro) {
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(erro.getPropertyPath().toString() + "  " + erro.getMessage()));
	}

}
