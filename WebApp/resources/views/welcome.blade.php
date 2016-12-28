<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Page specific styles -->
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
			<h1><a href="index.html">APPLY.IO</a></h1>
			<nav id="nav">
				<ul>
					<li class="special">
						<a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="index.html">Početna</a></li>
								<li><a href="#three">O aplikaciji</a></li>
								<li><a href="elements.html">Kontakt</a></li>
								<li><a href="#">Registracija</a></li>
								<li><a href="login.html">Prijava</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</header>

		<!-- Banner -->
		<section id="banner">
			<div class="logo">
				<img src="images/student.png" alt="" />
			</div>
			<div class="inner">
				<h2>APPLY.IO</h2>
				<p>Sustav<br /> za prijavu i registraciju<br /> projekata
					<a href="#"></a></p>
				<p class="android-paragraph"> Dostupno i za Android uređaje</p>
				<ul class="actions">
					<li>
						<a href="#" class="button special download"><img src="images/android.png" alt="" />Preuzmi</a>
					</li>
				</ul>
			</div>
			<a href="#three" class="more scrolly" id="scroll-btn">O aplikaciji</a>
		</section>
		<!-- Three -->
		<section id="three" class="wrapper style3 special">
			<div class="inner">
				<header class="major">
					<h2>Accumsan mus tortor nunc aliquet</h2>
					<p>Aliquam ut ex ut augue consectetur interdum. Donec amet imperdiet eleifend<br /> fringilla tincidunt. Nullam dui leo Aenean mi ligula, rhoncus ullamcorper.</p>
				</header>
				<ul class="features">
					<li class="icon fa-paper-plane-o">
						<h3>Arcu accumsan</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
					<li class="icon fa-laptop">
						<h3>Ac Augue Eget</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
					<li class="icon fa-code">
						<h3>Mus Scelerisque</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
					<li class="icon fa-headphones">
						<h3>Mauris Imperdiet</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
					<li class="icon fa-heart-o">
						<h3>Aenean Primis</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
					<li class="icon fa-flag-o">
						<h3>Tortor Ut</h3>
						<p>Augue consectetur sed interdum imperdiet et ipsum. Mauris lorem tincidunt nullam amet leo Aenean ligula consequat consequat.</p>
					</li>
				</ul>
			</div>
		</section>

		<!-- CTA -->
		<section id="cta" class="wrapper style4">
			<div id='browser'>
				<div id='browser-bar'>
					<div class='circles'></div>
					<div class='circles'></div>
					<div class='circles'></div>
					<p>Kontaktirajte nas</p>
					<span class='arrow entypo-resize-full'></span>
				</div>
				<div id='content'>

				</div>
			</div>
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
