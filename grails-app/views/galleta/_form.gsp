<%@ page import="pruebahelados.Galleta" %>



<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="galleta.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" type="number" value="${galletaInstance.cantidad}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="galleta.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${galletaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'precio', 'error')} required">
	<label for="precio">
		<g:message code="galleta.precio.label" default="Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precio" value="${fieldValue(bean: galletaInstance, field: 'precio')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'referencia', 'error')} required">
	<label for="referencia">
		<g:message code="galleta.referencia.label" default="Referencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="referencia" type="number" value="${galletaInstance.referencia}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'tama?o', 'error')} ">
	<label for="tama?o">
		<g:message code="galleta.tama?o.label" default="Tamao" />
		
	</label>
	<g:textField name="tama?o" value="${galletaInstance?.tama?o}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: galletaInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="galleta.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${galletaInstance?.tipo}"/>
</div>

