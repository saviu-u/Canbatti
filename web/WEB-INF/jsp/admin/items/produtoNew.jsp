<%@page import="java.util.Objects"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="">

    <title>Checkout example for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/resources/css/cadastro.css" rel="stylesheet">
  </head>

  <body class="bg-light">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
        <a class="navbar-brand"> <img class="logo" src="<%= request.getContextPath() %>/resources/imagens/logo.png" width="100" height="40"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
       <ul class="navbar-nav sair">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="/Canbatti/login/logout/">Sign out</a>
        </li>
       </ul>
      <div class="collapse navbar-collapse" id="navbarCollapse">
      </div>
    </nav>
  </br></br></br>
    <div class="container">
      <div class="row">
        <div class="col-md-8 order-md-1">
          <form class="needs-validation" method="POST" novalidate>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">Nome Produto</label>
                <input type="text" class="form-control" id="nomeProd" placeholder="" name="nomeProd" value="<%= Objects.toString(request.getAttribute("nomeProd"), "") %>">
                <div class="invalid-feedback">
                  Insira um nome válido.
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="sexo">Categoria</label>
              <select class="form-control" id="tipoProd" placeholder="" name="tipoProd" required>
                <option value="" selected>Selecione...</option>
                <option value="Pão">Pão</option> 
                <option value="Carne" >Carne</option>
                <option value="Acompanhamento">Acompanhamento</option>
                <option value="Molho">Molho</option>
                <option value="Bebida">Bebida</option>
              </select>
              <div class="invalid-feedback">
                Selecione a categoria.
              </div>
            </div>
            <div class="mb-3">
              <label for="email">Valor</label>
              <input type="text" class="form-control" id="precoProd" placeholder="" name="precoProd" value="<%= Objects.toString(request.getAttribute("precoProd"), "") %>">
              <div class="invalid-feedback">
                Insira o valor.
              </div>
            </div>
            <div class="mb-3">
              <label for="email">Quantidade</label>
              <input type="text" class="form-control" id="quantidade" placeholder="" name="quantidade" value="<%= Objects.toString(request.getAttribute("quantidade"), "") %>">
              <div class="invalid-feedback">
                Insira o quantidade.
              </div>
            </div>
            <div class="mb-3">
              <label for="cidade">Descrição</label>
              <input type="text" class="form-control" id="descProd" placeholder="" name="descProc" value="<%= Objects.toString(request.getAttribute("descProd"), "") %>">
            </div>
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Salvar</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-small">
        <p class="mb-1">Canbatti &copy; 2020</p>
      </footer>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../../../assets/js/vendor/popper.min.js"></script>
    <script src="../../../../dist/js/bootstrap.min.js"></script>
    <script src="../../../../assets/js/vendor/holder.min.js"></script>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
      window.onload = function(){
          var categoria = "<%= Objects.toString(request.getAttribute("tipoProd"), "") %>";
          if (categoria){
              document.getElementById('tipoProd').value = categoria;
          }
      };
    </script>
  </body>
</html>
