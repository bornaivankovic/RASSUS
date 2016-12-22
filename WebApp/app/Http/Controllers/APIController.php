<?php

namespace App\Http\Controllers;
use App\Project;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
//use Illuminate\Support\Facades\Request;
use Illuminate\Contracts\Routing\ResponseFactory;

class APIController extends Controller
{
    /**
     * Display a listing of the resource.
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
      $data = $request->json()->all();

      $project = new Project;

      $project->title = $data['projects']['title'];
      $project->description = $data['projects']['description'];
      $project->size = $data['projects']['size'];
      $project->taken = $data['projects']['taken'];

      $project->save();

      return "Success";
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
