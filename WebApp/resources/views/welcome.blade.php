<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap and default laravel variables -->
    <link href="{{ asset('css/app.css') }}" rel="stylesheet" type="text/css" >

    <!-- Page specific styles -->
    <link href="{{ asset('css/home.css') }}" rel="stylesheet" type="text/css" >

    <title>Home</title>
</head>

<body>
  <nav class="navbar navbar-default" id="nav">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" id="mobile-menu-btn" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" id="logo" href="/">
        <img src="{{ asset('images/logo/logo64.png') }}" alt="" />
      </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right" id="buttons-container">
        <button type="button" class="btn btn-default navbar-btn" id="sign-in-btn">Prijava</button>
        <button type="button" class="btn btn-default navbar-btn" id="register-btn">Registracija</button>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid" id="main-section">
  <div class="row text-center">
    <div class="col-xs-12" id="main-heading-container">
      <h1>Apply.io</h1>
      <h3>Sustav za prijavu i registraciju projekata</h3>
    </div>

  </div>
</div>
</body>

</html>
