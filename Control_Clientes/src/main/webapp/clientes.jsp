
<!DOCTYPE html>
<html>
    <head>
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

        <!-- font awesome -->
        <script src="https://kit.fontawesome.com/cf7d1b473b.js" crossorigin="anonymous"></script>
        <title>Control de Clientes</title>
    </head>
    <body>

        <!-- Cabecero -->
        <jsp:include page="WEB-INF/paginas/comunes/cabecero.jsp" />

        <!-- Sección botones de navegacion -->
        <jsp:include page="WEB-INF/paginas/comunes/botonesNavegacion.jsp" />

        <!-- Listado clientes -->
        <jsp:include page="WEB-INF/paginas/cliente/listadoClientes.jsp" />

        <!-- pie de pagina -->
        <jsp:include page="WEB-INF/paginas/comunes/piePagina.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
