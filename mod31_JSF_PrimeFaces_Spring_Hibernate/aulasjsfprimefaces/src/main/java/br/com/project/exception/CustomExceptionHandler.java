package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.session.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	
	private ExceptionHandler wrapperd;
	
	final FacesContext facesContext = FacesContext.getCurrentInstance();
	
	final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	
	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
	
	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapperd = exceptionHandler;
	}

	// Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
	@Override
	public ExceptionHandler getWrapped() {
		return wrapperd;
	}
	
	// Sobrescreve o método handle que é responsável por manipular as exceções do JSF
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			
			// Recuperar a exceção do contexto
			Throwable exception = context.getException();
			
			// Trabalhar a exceção
			try {
				
				requestMap.put("exceptionMessage", exception.getMessage());
				
				if (exception != null && 
						exception.getMessage() != null
						&& exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro não pode ser removido por " + " estar associado.",""));
				
				} else if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Registro foi atualizado ou excluído por outro usuário." + " Consulte novamente.", ""));
				} else {
					// Avisar o usuário do erro
					FacesContext.getCurrentInstance().addMessage("msg", new 
							FacesMessage(FacesMessage.SEVERITY_FATAL, "O sistema se recuperou de um erro inesperado.", ""));
					
					// Tranquilizar o usuário para continuar utilizando o sistema
					FacesContext.getCurrentInstance().addMessage("msg", new 
							FacesMessage(FacesMessage.SEVERITY_INFO, "Você pode continuar utilizando o sistema normalmente!", ""));
					
					FacesContext.getCurrentInstance().addMessage("msg", new 
							FacesMessage(FacesMessage.SEVERITY_FATAL, "O erro foi causado por:\n" + exception.getMessage(), ""));
					
					// alert caso não haja redirecionamento da página (PrimeFaces)
					RequestContext.getCurrentInstance().
					execute("alert('O sistema se recuperou de um erro inesperado')");
						
					RequestContext.getCurrentInstance().
					showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro inesperado."));
					
					// Redirecionar para página de erro
					navigationHandler.handleNavigation(facesContext, null, 
							"/error/error.jsf?faces-redirect=true&expired=true");
				}
				
				// Renderizar página de erro e exibir as mensagens
				facesContext.renderResponse();
				
			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				
				// imprimir erro no console
				exception.printStackTrace();
				
				iterator.remove();
			}
		}
		
		getWrapped().handle();
	}

}
