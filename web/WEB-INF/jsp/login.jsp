<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/resources/css/login.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <div>
      <form class="form-signin" method="POST">
          <img class="mb-4" src="<%= request.getContextPath() %>/resources/imagens/logo.png" alt="" width="200" height="80">
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" required>
        <label for="inputPassword" class="sr-only">Senha</label>
        <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Senha" required>
        <div class="checkbox mb-3">
        </div>
        <button class="btn btn-lg btn-block" type="submit" value="login" onclick=" form.action='login';">Entrar</button>
      </form>
      <form class="form-signin">
        <button class="btn btn-block btn-cinza" type="submit" value="cadastro.jsp" onclick="form.action='login/new';">Cadastro</button>
      </form>
      <p class="mt-5 mb-3 text-muted">Canbatti &copy; 2020</p>
    </div>
  </body>
</html>
