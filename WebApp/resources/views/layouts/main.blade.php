<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Page specific styles -->
    <link href="/css/app.css" rel="stylesheet">
    <link href="{{ asset('css/bootstrap-social.css') }}" rel="stylesheet" type="text/css" >

    @if (Route::currentRouteName() == "login")
      <link href="{{ asset('css/login.css') }}" rel="stylesheet" type="text/css" >
    @endif
    @if (Route::currentRouteName() == "register")
      <link href="{{ asset('css/register.css') }}" rel="stylesheet" type="text/css" >
    @endif
    <link href="{{ asset('css/normalize.css') }}" rel="stylesheet" type="text/css" >
    <link href="{{ asset('css/main.css') }}" rel="stylesheet" type="text/css" >


    <link href="https://fonts.googleapis.com/css?family=Acme" rel="stylesheet">

    <title>Home</title>
</head>

<body class="landing">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<h1><a href="/">APPLY.IO</a></h1>
			<nav id="nav">
				<ul>
					<li class="special">
						<a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="/">Početna</a></li>
								<li><a href="#three">O aplikaciji</a></li>
								<li><a href="elements.html">Kontakt</a></li>
								<li><a href="/register">Registracija</a></li>
								<li><a href="/login">Prijava</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</header>
    <section id="banner">
      @yield('content')
    </section>


		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
				<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
				<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
			</ul>
			<ul class="copyright">
				<li>&copy; FER 2016</li>
				<li>Developed by: <a href="http://www.fer.unizg.hr/">FER 2016.</a></li>
			</ul>
		</footer>
	</div>
  <!-- Scripts -->
  <script type="text/javascript" src="{!! asset('js/jquery.min.js') !!}"></script>
  <script type="text/javascript" src="{!! asset('js/jquery.scrollex.min.js') !!}"></script>
  <script type="text/javascript" src="{!! asset('js/jquery.scrolly.min.js') !!}"></script>
  <script type="text/javascript" src="{!! asset('js/skel.min.js') !!}"></script>
  <script type="text/javascript" src="{!! asset('js/util.js') !!}"></script>
  <script type="text/javascript" src="{!! asset('js/main.js') !!}"></script>
  <!--[if lte IE 8]><script src="{!! asset('js/ie/main.js') !!}"></script><![endif]-->
</body>

</html>
