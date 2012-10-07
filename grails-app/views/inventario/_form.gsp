<%@ page import="pruebahelados.Inventario" %>



<div class="fieldcontain ${hasErrors(bean: inventarioInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="inventario.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" type="number" value="${inventarioInstance.cantidad}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: inventarioInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="inventario.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${inventarioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inventarioInstance, field: 'precio', 'error')} required">
	<label for="precio">
		<g:message code="inventario.precio.label" default="Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precio" value="${fieldValue(bean: inventarioInstance, field: 'precio')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: inventarioInstance, field: 'referencia', 'error')} required">
	<label for="referencia">
		<g:message code="inventario.referencia.label" default="Referencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="referencia" type="number" value="${inventarioInstance.referencia}" required=""/>
</div>

