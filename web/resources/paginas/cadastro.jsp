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
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/form-validation.css" rel="stylesheet">
  </head>

  <body class="bg-light">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand text-center" href="#">Canbatti</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
      </div>
    </nav>
  </br></br></br>
    <div class="container">
      <div class="row">
        <div class="col-md-8 order-md-1">
          <form class="needs-validation" novalidate>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">Nome Completo</label>
                <input type="text" class="form-control" id="nome" placeholder="" value="" required>
                <div class="invalid-feedback">
                  Insira um nome válido.
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="cpf">CPF</label>
              <input type="text" class="form-control" id="cpf" required>
              <div class="invalid-feedback">
                Insira o CPF.
              </div>
            </div>
            <div class="mb-3">
              <label for="email">Email</label>
              <input type="text" class="form-control" id="email" placeholder="" required>
              <div class="invalid-feedback">
                Insira o email.
              </div>
            </div>
            <div class="mb-3">
              <label for="telefone1">Telefone 1</label>
              <input type="text" class="form-control" id="telefone1" placeholder="" required>
              <div class="invalid-feedback">
                Insira o telefone.
              </div>
            </div>
            <div class="mb-3">
              <label for="telefone2">Telefone 2<span class="text-muted">(Opcional)</span></label>
              <input type="text" class="form-control" id="telefone2" placeholder="">
            </div>
            <div class="mb-3">
              <label for="estado">Estado</label>
              <input type="text" class="form-control" id="estado" placeholder="" required>
              <div class="invalid-feedback">
                Insira o estado(UF).
              </div>
            </div>
            <div class="mb-3">
              <label for="cidade">Cidade</label>
              <input type="text" class="form-control" id="cidade" placeholder="" required>
              <div class="invalid-feedback">
                Insira a cidade.
              </div>
            </div>
            <div class="mb-3">
              <label for="bairro">Bairro</label>
              <input type="text" class="form-control" id="bairro" placeholder="" required>
              <div class="invalid-feedback">
                Insira o bairro.
              </div>
            </div>
            <div class="mb-3">
              <label for="rua">Rua</label>
              <input type="text" class="form-control" id="address" placeholder="" required>
              <div class="invalid-feedback">
                Insira a rua.
              </div>
            </div>
            <div class="mb-3">
              <label for="num_residencia">Número Residência</label>
              <input type="text" class="form-control" id="num_residencia" placeholder="" required>
              <div class="invalid-feedback">
                Insira o número da residência.
              </div>
            </div>
            <div class="mb-3">
              <label for="complemento">Complemento<span class="text-muted">(Opcional)</span></label>
              <input type="text" class="form-control" id="complemento" placeholder="">
            </div>
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Cadastrar</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
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
    </script>
  </body>
</html>
