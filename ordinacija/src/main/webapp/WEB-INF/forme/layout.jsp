<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
        <title>Ordinacija</title>
        <base href="http://localhost:8080/ordinacija/" />
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" type="text/css"/>
        <link rel="stylesheet" href="https://bootswatch.com/united/bootstrap.css"/>
    </head>
    <body style="padding-top: 50px; padding-bottom: 50px">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <ul class="nav navbar-nav">
                    <c:if test="${sessionScope.stomatolog != null}">
                        <li><a href="intervencija">Intervencije</a></li> 
                        <li><a href="intervencija/rezervacija">Rezervacije</a></li> 
                        <li><a href="pacijent">Pacijenti</a></li> 
                        <li><a href="usluga">Usluge</a></li> 
                        <li><a href="grad">Gradovi</a></li>
                        <li><a href="odjava">Odjava</a></li>
                        </c:if>
                        <c:if test="${sessionScope.stomatolog == null}">
                        <li><a href="prijava">Prijava</a></li>
                        </c:if>
                </ul>
            </div>
        </div>

        <div style="padding-bottom: 100px"></div>

        <div class="container">  
            <%
                if ((String) request.getAttribute("view") != "") {
                    pageContext.include((String) request.getAttribute("view") + ".jsp");
                }
            %>
        </div>

        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

    </body>
</html>
