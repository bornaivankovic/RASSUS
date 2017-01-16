<?php

namespace App\Http\Controllers;
use App\Project;
use Illuminate\Http\Request;
use Validator;
use Response;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;



class ProjectController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $projects = Project::all();
        return view('admin.projects.index',['projects' => $projects]);
    }
    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

      $rules = array(
        'title' => 'required',
        'description' => 'required',
        'size' => 'required',
        'mentor' => 'required',
      );
      // for Validator
      $validator = Validator::make ( Input::all (), $rules );

      if ($validator->fails())
        return Response::json(array('errors' => $validator->getMessageBag()->toArray()));
      else {
        $project = new Project();
        $project->title = $request->title;
        $project->description = $request->description;
        $project->taken = 0;
        $project->size = $request->size;
        $project->mentor = $request->mentor;
        if(isset($request->team)){
          $project->team = $request->team;
        }

        $project->save();

        return response()->json($project);
      }



    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit(Request $request, $id)
    {

    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
      $project = Project::find($request->id);

      $project->title = $request->title;
      $project->description = $request->description;
      $project->taken = $request->taken;
      $project->size = $request->size;
      $project->mentor = $request->mentor;
      $project->team = $request->team;

      $project->save();

      return response()->json($project);
    }

    public function apply(Request $request)
    {
        $project = Project::find($request->id);
        if ($project->taken == 1) {
          return response([
            'errors' => "Neuspješna prijava. Projekt zauzet!"
          ]);
        }
          $project->taken = 1;
          if ($project->size == 1) {
            if ($request->team) {
              $project->team = $request->team[0];
            } else {
              return response([
                'errors' => "Neuspješna prijava. Nedovoljan broj članova!"
              ]);
            }
          } else {
            if (count($request->team) != $project->size) {
              return response([
                'errors' => "Neuspješna prijava. Nedovoljan broj članova!"
              ]);
            }
            $project->team = implode(", ",$request->team);
          }
          $project->save();
          return response([
            'success' => $project->title,
          ]);
    }



    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request)
    {
      Project::find($request->id)->delete();
      return response()->json();
    }

    public function getUsers(){
      $users = $titles = DB::table('users')->where('admin', '0')->pluck('name');
      return response()->json($users);
    }
}
