<?php
use Illuminate\Support\Facades\DB;
use App\User;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| This file is where you may define all of the routes that are handled
| by your application. Just tell Laravel the URIs it should respond
| to using a Closure or controller method. Build something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::group(['middleware' => ['before']], function(){
    Route::get('profile', function () {
      if(Auth::check()){
        if (Auth::user()->admin == 1) {
          return redirect('admin');
        } else {
          return redirect('user');
        }
      }
      return redirect('/');

    });

    Route::get('admin', function () {
        $user_num = App\User::where('admin', 0)->count();
        $project_num = DB::table('projects')->count();
        $users = App\User::where('admin', 0)->paginate(8, ['*'], 'users');
        $online_users = \App\User::online()->get();



        $projects = App\Project::paginate(4, ['*'], 'projects');
        $projects_taken = App\Project::where('taken', 1)->count();
        $project_stat = 0;
        if ($projects_taken == 0) {
          $project_stat = 0;
        } else {
          $project_stat = ($projects_taken/$project_num)*100;
        }

        return view('admin.index', [
          'user_num' => $user_num,
          'project_num' => $project_num,
          'project_stat' => $project_stat,
          'users' => $users,
          'projects' => $projects,
          'online_users' => $online_users,
        ]);
    })->middleware('authAdmin');

    Route::get('user', function () {
      $user_num = App\User::where('admin', 0)->count();
      $project_num = DB::table('projects')->count();
      $users = App\User::where('admin', 0)->paginate(8, ['*'], 'users');
      $online_users = \App\User::online()->get();

      $projects = App\Project::paginate(10, ['*'], 'projects');
      $projects_taken = App\Project::where('taken', 1)->count();
      $project_stat = 0;
      if ($projects_taken == 0) {
        $project_stat = 0;
      } else {
        $project_stat = ($projects_taken/$project_num)*100;
      }
        return view('user.index', [
          'user_num' => $user_num,
          'project_num' => $project_num,
          'project_stat' => $project_stat,
          'users' => $users,
          'projects' => $projects,
          'online_users' => $online_users,
        ]);
    });

});



Route::get('register', function () {
    return view('auth.register');
});

Route::get('logout', 'Auth\LoginController@logout');

/* Redirect /home request to actual homepage */
Route::get('home', function () {
    return view('home');
});

Route::get('/', function () {
    return view('welcome');
});

Route::get('auth/{provider}', 'Auth\RegisterController@redirectToProvider');
Route::get('auth/{provider}/callback', 'Auth\RegisterController@handleProviderCallback');

Route::group(['middleware' => ['authAdmin']], function() {
  Route::resource('/admin/projects','ProjectController');
  Route::post ( '/editItem', 'ProjectController@update' );
  Route::post ( '/addItem', 'ProjectController@store' );
  Route::post ( '/deleteItem', 'ProjectController@destroy' );
  Route::post ( '/applyItem', 'ProjectController@apply' );
});
