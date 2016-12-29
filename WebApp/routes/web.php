<?php

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


/* Redirect /home request to actual homepage */
Route::get('home', function () {
    return redirect('/');
});

Route::get('admin', function () {
    return view('admin.index');
});

Route::get('/', function () {
    return view('welcome');
});

Route::group(['middleware' => ['web']], function() {
  Route::resource('/admin/projects','ProjectController');
  Route::post ( '/editItem', 'ProjectController@update' );
  Route::post ( '/addItem', 'ProjectController@store' );
  Route::post ( '/deleteItem', 'ProjectController@destroy' );
});
