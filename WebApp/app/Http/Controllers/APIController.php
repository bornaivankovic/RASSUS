<?php

namespace App\Http\Controllers;
use App\Project;
use App\User;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Contracts\Routing\ResponseFactory;
use Validator;
use Illuminate\Http\Response;

class APIController extends Controller
{
    /**
     * Returns all projects
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $projects = Project::all();

        return response($projects)
            ->withHeaders([
                'Content-Type' => 'application/json',
            ]);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
      $request_body = $request->getContent();

      //validacija za prazan objekt
      if(empty($request_body)) {
        return response("Invalid request format", 400);
      }

      $json_decoded = json_decode($request_body);

      //validacija za neispravan JSON format
      if(is_null($json_decoded)) {
        return response("Invalid request format", 400);
      }

      foreach ($json_decoded as $value) {

        //validacija za nepotpun JSON format
        $validator = Validator::make((array)$value, [
        'title' => 'required',
        'description' => 'required',
        'size' => 'required',
        'taken'  => 'required',
        'mentor'  => 'required'
        ]);

        if ($validator->fails()) {
          array_push($validation_errors, $validator->errors());
         }

      }

      if(!empty($validation_errors)){
        return response([
              'message' => "Invalid format",
              'errors' => $validation_errors
              ],
              400)
                  ->withHeaders([
                      'Content-Type' => 'application/json',
                  ]);
      }



      foreach ($json_decoded as $value) {

        $project = new Project;

        $project->title = $value->title;
        $project->description = $value->description;
        $project->size = $value->size;
        $project->taken = $value->taken;
        $project->mentor = $value->mentor;
        if (isset($value->team)) {
          $project->team = $value->team;
        } else {
          $project->team = '';
        }

        $project->save();
      }

      return response("", 200)
          ->withHeaders([
              'Content-Type' => 'application/json',
          ]);

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {

      $project = Project::find($id);

      if(empty($project)){
        return response("", 404);
      } else {
        return response($project)
            ->withHeaders([
                'Content-Type' => 'application/json',
            ]);
      }

    }

    public function update(Request $request, $id)
    {

      $project = Project::find($id);
      $request_body = $request->getContent();

      if(empty($project)){
        return response("", 404);
      }

      //validacija za prazan objekt
      if(empty($request_body)) {
        return response("Empty request_body", 400);
      }

      $json_decoded = json_decode($request_body);

      //validacija za neispravan JSON format
      if(is_null($json_decoded)) {
        return response("Invalid JSON format", 400);
      }


        foreach ($json_decoded as $value) {
          if(isset($value->title)){
              $project->title = $value->title;
          }
          elseif (isset($value->description)) {
              $project->description = $value->description;
          }
          elseif (isset($value->size)) {
            $project->size = $value->size;
          }
          elseif (isset($value->taken)) {
            $project->taken = $value->taken;
          }
          elseif (isset($value->mentor)) {
            $project->mentor = $value->mentor;
          }
          elseif (isset($value->team)) {
            $project->team = $value->team;
          }

        }

        $project->save();

        return response("Updated", 200)
            ->withHeaders([
                'Content-Type' => 'application/json',
            ]);

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function delete($id)
    {
        $project = Project::find($id);

        if(empty($project)){
          return response("", 404);
        } else {
          $project->delete();
          return response("", 200);
        }
    }

    public function checkIfAdmin(Request $request, $email) {
      $user = User::where('email', $email)->first();

      if(isset($user)){
        if($user->admin == 1) {
          return response()->json([
              'admin' => true,
          ]);
        } else {
          return response()->json([
              'admin' => false,
          ]);
        }
      } else {
        return response("", 404);
      }

    }
}
