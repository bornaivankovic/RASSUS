<?php

return [

    /*
    |--------------------------------------------------------------------------
    | Third Party Services
    |--------------------------------------------------------------------------
    |
    | This file is for storing the credentials for third party services such
    | as Stripe, Mailgun, SparkPost and others. This file provides a sane
    | default location for this type of information, allowing packages
    | to have a conventional place to find your various credentials.
    |
    */

    'mailgun' => [
        'domain' => env('MAILGUN_DOMAIN'),
        'secret' => env('MAILGUN_SECRET'),
    ],

    'ses' => [
        'key' => env('SES_KEY'),
        'secret' => env('SES_SECRET'),
        'region' => 'us-east-1',
    ],

    'sparkpost' => [
        'secret' => env('SPARKPOST_SECRET'),
    ],

    'stripe' => [
        'model' => App\User::class,
        'key' => env('STRIPE_KEY'),
        'secret' => env('STRIPE_SECRET'),
    ],
    
    'google' => [
    'client_id' => '466732607829-7bp00froa19d8og5jmao2c9rbqcqgrul.apps.googleusercontent.com',
    'client_secret' => '9s0kRHNj9nZOZm-AsIceO9E2',
    'redirect' => 'http://localhost:8000/auth/google/callback',
    ],

    'facebook' => [
    'client_id' => '225689701212748',
    'client_secret' => 'cf07b341c7af298deb98304ef0045ffe',
    'redirect' => 'http://localhost:8000/auth/facebook/callback',
    ],

    'twitter' => [
    'client_id' => 'gh9VZLDAHmQjDgDl6dTGRsb4f',
    'client_secret' => 'i8ru3dmNlcNGRpFvtzJ1XSXQSlInQznu0aCNsowNZh0vApH3L8',
    'redirect' => 'http://localhost:8000/auth/twitter/callback',
    ],

];
