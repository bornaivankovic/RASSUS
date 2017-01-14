<?php

namespace App;

use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

use Carbon\Carbon;

use Illuminate\Auth\Passwords\CanResetPassword;
use Illuminate\Contracts\Auth\Authenticatable as AuthenticatableContract;
use Illuminate\Contracts\Auth\CanResetPassword as CanResetPasswordContract;

use Illuminate\Support\Str;

class User extends Authenticatable implements AuthenticatableContract, CanResetPasswordContract
{
    use Notifiable;
    use CanResetPassword;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'name', 'email', 'password', 'admin', 'last_active_at'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password','remember_token'
    ];

    /**
     * The attributes that should be mutated to dates.
     *
     * @var array
     */
    protected $dates = [
        'last_active_at'
    ];
    /**
     * The accessors to append to the model's array form.
     *
     * @var array
     */
    protected $appends = [
        'online',
    ];

    function socialProviders()
    {
        return $this->hasMany(SocialProvider::class);
    }

    /**
     * Return if the user is currently online.
     *
     * @return bool
     */
    public function getOnlineAttribute()
    {
        return $this->isOnline();
    }

    /**
     * Return if the user is currently online.
     *
     * @return bool
     */
    public function isOnline()
    {
        return $this->last_active_at > Carbon::now()->subMinutes(3)->toDateTimeString();
    }

    /**
     * Fetch users that have a company profile.
     *
     * @param $query
     *
     * @return mixed
     */
    public function scopeOnline( $query )
    {
        $start = Carbon::now()->subMinutes(3)->toDateTimeString();
        $end   = Carbon::now()->toDateTimeString();

        return $query->whereBetween('last_active_at', [$start, $end]);
    }
}
