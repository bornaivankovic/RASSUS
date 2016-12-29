<?php

namespace App\Http\Middleware;
use App\User;
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
  $user = User::where('username', $username)->first();

  if(empty($user)  || ($user->admin == 0)) {

    return response('Invalid credentials', 400)
              ->withHeaders([
                  'WWW-Authenticate' => 'Basic',
                ]);
  }
  return $next($request);

  }

}
