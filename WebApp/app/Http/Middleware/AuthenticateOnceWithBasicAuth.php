<?php

namespace App\Http\Middleware;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Closure, Request, Response;

class AuthenticateOnceWithBasicAuth
{

/**
* Handle an incoming request.
*
* @param  \Illuminate\Http\Request  $request
* @param  \Closure  $next
* @return mixed
*/
public function handle($request, Closure $next)

{


$username = Request::getUser();
$user = DB::table('users')->where('username', $username)->first();
if($user->admin != 1)
{

$headers = array('WWW-Authenticate' => 'Basic');
return Response::make('Invalid credentials.', 401, $headers);

}
return $next($request);

}

}
