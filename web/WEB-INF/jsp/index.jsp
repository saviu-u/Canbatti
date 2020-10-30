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
          <table class="table table-bordered">
            <thead>
              <tr>
                <th class="th" scope="col">Selecionado(s)</th>
                <th scope="col">Pão</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input class="selecionado" type="radio" value="selecionado"></input>
                </td>
                <td>Pão de Hamburger</td>
                <td>6.00</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                        <option value="">2</option>
                  </select>
                </td>
              </tr>
            </tbody>
            <thead>
              <tr>
                <th scope="col">Selecionado(s)</th>
                <th scope="col">Carne</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Picanha</td>
                <td>5.00</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
            </tbody>
            <thead>
              <tr>
                <th scope="col">Selecionado(s)</th>
                <th scope="col">Acompanhamento</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Salada</td>
                <td>2.00</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Batata frita</td>
                <td>3.00</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
            </tbody>
            <thead>
              <tr>
                <th scope="col">Selecionado(s)</th>
                <th scope="col">Molho</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Maionese</td>
                <td>0.50</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Barbecue</td>
                <td>0.50</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
            </tbody>
            <thead>
              <tr>
                <th scope="col">Selecionado(s)</th>
                <th scope="col">Bebida</th>
                <th scope="col">Valor</th>
                <th scope="col">Quantidade</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input class="selecionado" type="checkbox" value="selecionado"></input>
                </td>
                <td>Coca cola 2L</td>
                <td>8.00</td>
                <td>
                    <select class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <option value="" selected>1</option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>  
          <div class="col-md-4 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-muted">Carrinho</span>
              <span class="badge badge-secondary badge-pill">3</span>
            </h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">Product name</h6>
                  <small class="text-muted">Brief description</small>
                </div>
                <span class="text-muted">$12</span>
                <a href="teste" class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <span>Total (R$)</span>
                <strong class="valor">$20</strong>
              </li>
            </ul>
          </div>
        </div>
        </div>
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
