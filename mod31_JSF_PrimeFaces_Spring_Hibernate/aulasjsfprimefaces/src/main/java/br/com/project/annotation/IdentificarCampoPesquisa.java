package br.com.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificarCampoPesquisa {
	
	String descricaoCampo(); // campo descrição na tela
	String campoConsulta(); // campo banco dados
	int principal() default 10000; // posição no combo
}
