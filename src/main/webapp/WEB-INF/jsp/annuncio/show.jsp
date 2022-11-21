<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Dettaglio Annuncio</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Visualizza dattaglio Annuncio</h5> 
				    </div>
				    <div class='card-body'>
				    
						<dl class="row">
						  <dt class="col-sm-3 text-right">Id:</dt>
						  <dd class="col-sm-9">${show_annuncio_attr.id}</dd>
			    		</dl>
			    		
			    		<dl class="row">
						  <dt class="col-sm-3 text-right">Testo Annuncio</dt>
						  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
			    		</dl>
			    		
			    		<dl class="row">
						  <dt class="col-sm-3 text-right">Prezzo:</dt>
						  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
			    		</dl>
			    		
			    		<dl class="row">
						  <dt class="col-sm-3 text-right">Aperto:</dt>
						  <dd class="col-sm-9">${show_annuncio_attr.aperto}</dd>
			    		</dl>
							
  						<dl class="row">
						  <dt class="col-sm-3 text-right">Data Creazione:</dt>
						  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
			    		</dl>
			    		
			    		<dl class="row">
						  <dt class="col-sm-3 text-right">Utente che ha creato l'annuncio:</dt>
						  <dd class="col-sm-12"><br></dd>
	
						  	<dd class="col-sm-12">${utente_annuncio_attr.nome} ${utente_annuncio_attr.cognome}</dd>
						  
			    		</dl>
				    	
				    	<div class='card-footer'>
				    	
				     <sec:authorize access="isAuthenticated()">
			        <form action="${pageContext.request.contextPath}/acquisto/confermaAcquisto" method="post">
					    		<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
					    		<input type="hidden" name="utenteId" id="utenteId" value="${userInfo.id}">
						    	<button type="submit" name="idAnnuncio" id="idAnnuncio" class="btn btn-primary">Conferma Acquisto</button>
						        
					</form>
				</sec:authorize>
			        		
			        		
			     		   	<a href="${pageContext.request.contextPath }/home/" class='btn btn-outline-secondary' style='width:80px'>
			            		<i class='fa fa-chevron-left'></i> Back
			        		</a>
			    		</div>
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>