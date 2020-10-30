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
    <link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/resources/css/adm.css" rel="stylesheet">
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
                <a class="nav-link" href="/Canbatti/admin">
                  <span data-feather="home"></span>
                  Dashboard
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/Canbatti/admin/item">
                  <span data-feather="shopping-cart"></span>
                  Produtos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="users"></span>
                  Pessoas
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
            <h1 class="h2">Pedidos</h1>
          </div>
            <div class="table-responsive">
              <table class="table table-striped table-sm">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Nome Pessoa</th>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Concluído</th>
                    <th>Opções</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>Teste 1</td>
                    <td>Pão de hamburger, costela, batata frita, maionese</td>
                    <td>50,00</td>
                    <td>
                        <input class="concluido" type="checkbox" value="concluido"></input>
                    </td>
                    <td>
                        <a href="admin/user/'${pes.idPes}'" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="teste" class="delete"><i class="material-icons" id="deletePedido" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                  </tr>
                   <tr>
                    <td>2</td>
                    <td>Teste 2</td>
                    <td>Pão de hamburger, picanha, salada, maionese</td>
                    <td>60,00</td>
                    <td>
                        <input class="concluido" type="checkbox" value="concluido"></input>
                    </td>
                    <td>
                        <a href="admin/user/'${pes.idPes}'" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="teste" class="delete"><i class="material-icons" id="deletePedido" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                  </tr>
                </tbody>
              </table>
              <nav aria-label="Navegação de página exemplo">
                <ul class="pagination justify-content-end">
                  <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Anterior</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#">Próximo</a>
                  </li>
                </ul>
              </nav>
            </div>
          </main>
        </div>
      </div>

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
