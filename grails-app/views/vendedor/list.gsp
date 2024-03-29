
<%@ page import="pruebahelados.Vendedor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vendedor.label', default: 'Vendedor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vendedor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-vendedor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'vendedor.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="salario" title="${message(code: 'vendedor.salario.label', default: 'Salario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${vendedorInstanceList}" status="i" var="vendedorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${vendedorInstance.id}">${fieldValue(bean: vendedorInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: vendedorInstance, field: "salario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${vendedorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
