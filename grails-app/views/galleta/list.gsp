
<%@ page import="pruebahelados.Galleta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'galleta.label', default: 'Galleta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-galleta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-galleta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cantidad" title="${message(code: 'galleta.cantidad.label', default: 'Cantidad')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'galleta.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="precio" title="${message(code: 'galleta.precio.label', default: 'Precio')}" />
					
						<g:sortableColumn property="referencia" title="${message(code: 'galleta.referencia.label', default: 'Referencia')}" />
					
						<g:sortableColumn property="tama?o" title="${message(code: 'galleta.tama?o.label', default: 'Tamao')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'galleta.tipo.label', default: 'Tipo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${galletaInstanceList}" status="i" var="galletaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${galletaInstance.id}">${fieldValue(bean: galletaInstance, field: "cantidad")}</g:link></td>
					
						<td>${fieldValue(bean: galletaInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: galletaInstance, field: "precio")}</td>
					
						<td>${fieldValue(bean: galletaInstance, field: "referencia")}</td>
					
						<td>${fieldValue(bean: galletaInstance, field: "tama?o")}</td>
					
						<td>${fieldValue(bean: galletaInstance, field: "tipo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${galletaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
