<%@ page import="pruebahelados.Cliente" %>



<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'descipcion', 'error')} ">
	<label for="descipcion">
		<g:message code="cliente.descipcion.label" default="Descipcion" />
		
	</label>
	<g:textField name="descipcion" value="${clienteInstance?.descipcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'saldo', 'error')} required">
	<label for="saldo">
		<g:message code="cliente.saldo.label" default="Saldo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="saldo" value="${fieldValue(bean: clienteInstance, field: 'saldo')}" required=""/>
</div>

