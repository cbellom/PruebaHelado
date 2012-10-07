
<%@ page import="pruebahelados.Helado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'helado.label', default: 'Helado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-helado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-helado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="corol" title="${message(code: 'helado.corol.label', default: 'Corol')}" />
					
						<g:sortableColumn property="sabor" title="${message(code: 'helado.sabor.label', default: 'Sabor')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${heladoInstanceList}" status="i" var="heladoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${heladoInstance.id}">${fieldValue(bean: heladoInstance, field: "corol")}</g:link></td>
					
						<td>${fieldValue(bean: heladoInstance, field: "sabor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${heladoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
