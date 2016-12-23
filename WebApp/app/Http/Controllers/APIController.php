<?php

namespace App\Http\Controllers;
use App\Project;
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
        $projects_json = json_encode(DB::table('projects')->get());
        return response($projects_json)
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
      //$data = $request->json()->all();
      $json_data = $request->getContent();
      $json_decoded = json_decode($json_data);

      $validation_errors = [];

      foreach ($json_decoded as $value) {


        $validator = Validator::make((array)$value, [
        'title' => 'required',
        'description' => 'required',
        'size' => 'required',
        'taken'  => 'required'
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

        $project->save();
      }

      return response("Success")
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

      $project_json = json_encode(DB::table('projects')->where('id', $id)->first());
      return response($project_json)
          ->withHeaders([
              'Content-Type' => 'application/json',
          ]);
    }

    public function update(Request $request, $id)
    {
        $data = $request->json()->all();

        $project = new Project;

        $project->title = $data['projects']['title'];
        $project->description = $data['projects']['description'];
        $project->size = $data['projects']['size'];
        $project->taken = $data['projects']['taken'];

        DB::table('projects')
            ->where('id', $id)
            ->update(['title' => $project->title,
                      'description' => $project->description,
                      'size' => $project->size,
                      'taken' => $project->taken]);

        return "Success";
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function delete($id)
    {
        DB::table('projects')->where('id', '=', $id)->delete();
        return $id;
    }
}
