# KW Todo

[![.github/workflows/gradle.yml](https://github.com/efrenospino/kotlin-wasm-todo/actions/workflows/gradle.yml/badge.svg)](https://github.com/efrenospino/kotlin-wasm-todo/actions/workflows/gradle.yml)

A Kotlin WASM Todo Application. Built with Kotlin Multiplatform with WASM as the only target in mind. The main goal is
to try Kotlin Multiplatform for web, specifically for WASM, to build a very simple app.

## Features

- CRUD TODOs
- Search TODOs

## Screenshots

![img.png](images/img.png)

## Stack

- Kotlin as main language
- Compose multiplatform for the UI
- Ktor for networking
- Postgres (supabase) for data

## Architecture

The application is totally written in Kotlin. Using Kotlin Multiplatform **expected** and **actual** declarations
enables the project to use platform-specific APIs, in this case, the use of the Supabase SDK for WASM.

The whole UI code is build in `composeApp/src/commonMain/kotlin/dev/efrenospino/kwtodo/ui` where we only Compose
Multiplatform is used.

[Supabase](https://supabase.com/)/[PostgREST](https://postgrest.org/en/v12/) are used for database and API.

## Running in development

### Starting Supabase

1. Download the Supabase CLI following the steps in
   the [installation guide](https://supabase.com/docs/guides/cli/getting-started#installing-the-supabase-cli)
2. Run `supabase start` in the root directory of the app

### Launch App

Before starting the app, make sure you are using the corresponding keys for accessing your Supabase database and update
your **local.properties** file:

```
supabase.url=***
supabase.key=***
```

After Supabase is set up, simply run this **gradle** command (or use InteliJ Run button):

```
./gradlew wasmJsBrowserDevelopmentRun -t
```

And the app will be available at http://localhost:8080/