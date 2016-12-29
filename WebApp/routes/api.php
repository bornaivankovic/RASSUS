<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::get('projects', 'APIController@index');

Route::get('projects/{id}', 'APIController@show');

Route::post('projects', 'APIController@store')->middleware('auth.basic.once');

Route::delete('projects/{id}', 'APIController@delete')->middleware('auth.basic.once');

Route::put('projects/{id}', 'APIController@update')->middleware('auth.basic.once');
