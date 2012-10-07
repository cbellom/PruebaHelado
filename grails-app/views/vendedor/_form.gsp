<%@ page import="pruebahelados.Vendedor" %>



<div class="fieldcontain ${hasErrors(bean: vendedorInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="vendedor.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${vendedorInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vendedorInstance, field: 'salario', 'error')} required">
	<label for="salario">
		<g:message code="vendedor.salario.label" default="Salario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="salario" value="${fieldValue(bean: vendedorInstance, field: 'salario')}" required=""/>
</div>

