<%@ page import="pruebahelados.Helado" %>



<div class="fieldcontain ${hasErrors(bean: heladoInstance, field: 'corol', 'error')} required">
	<label for="corol">
		<g:message code="helado.corol.label" default="Corol" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: heladoInstance, field: 'sabor', 'error')} ">
	<label for="sabor">
		<g:message code="helado.sabor.label" default="Sabor" />
		
	</label>
	<g:textField name="sabor" value="${heladoInstance?.sabor}"/>
</div>

