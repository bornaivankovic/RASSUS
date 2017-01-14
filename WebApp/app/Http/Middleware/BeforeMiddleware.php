<?php
namespace App\Http\Middleware;
use Closure;
use DateTime;
use DB;
use Illuminate\Support\Facades\Auth;
class BeforeMiddleware
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
        // If the user is logged in we want to update their last_active_at timestamp
        // so that we can discern that the user is online.
        if( $user = Auth::user() )
        {
            $user->last_active_at = new DateTime;
            $user->save();
        }
        return $next($request);
    }
}
