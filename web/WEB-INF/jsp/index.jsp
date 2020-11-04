<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/adm.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  </head>

  <body>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
      <a class="" href="#"><img class="logo" src="<%= request.getContextPath() %>/resources/imagens/logo.png" width="100" height="40"></a>
      <ul class="navbar-nav sair">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="#">Sign out</a>
        </li>
      </ul>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
      </div>
    </nav>
      <br><br><br>
    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="home"></span>
                  Dashboard
                </a>
              </li>
            </ul>
          </div>
        </nav>

          <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
              <h1 class="h2">Pedidos</h1>
            </div>
          <div class="container">
                   <div class="row">
          <div class="col-md-8 order-md-1">
            <form class="needs-validation" method="POST" novalidate>
              <div class="mb-3">
                <table class="table table-bordered">
            <thead>
              <tr>
                <th scope="col">Categoria</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
                <%
                      for(Map<String, Object> param : (List<Map<String, Object>>) request.getAttribute("resources")){
                          
                  %>
                  <tr>
                    <td><%= param.get("nomeProd") %></td>
                    <td><%= param.get("precoProd") %></td>
                    <td>
                        <select class="btn btn-sm btn-outline-secondary dropdown-toggle" name="<%= param.get("idProd") %>">
                            <option value=0 selected>0</option>
                            <%
                                for(int i=1 ; i <= (Integer) param.get("quantidade") ; i++ ) {
                            %>
                            <option value=<%=i%>><%=i%></option>
                            <% } %>
                        </select>
                    </td>
                  </tr>
                  <%
                      }
                  %>
            </tbody>
          </table>  
              </div>  
              <hr class="mb-4">
              <button class="btn btn-primary btn-lg btn-block" type="submit">Pedir</button>
            </form>
          </div>
      </div>
   </main>    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../../../assets/js/vendor/popper.min.js"></script>
    <script src="../../../../dist/js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

    <!-- Graphs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script>
    </script>
  </body>
</html>
