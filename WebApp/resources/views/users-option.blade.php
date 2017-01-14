<?php
use Illuminate\Support\Facades\DB;
use App\User;

$users = App\User::where('admin', 0);
?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>


    @foreach ($users as $user)
      <?php echo $user->name ?>
    @endforeach
  </body>
</html>
