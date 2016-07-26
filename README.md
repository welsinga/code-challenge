# Challenge

## Goal
To load data from the provided API endpoint and display it to the user in a GridLayout.

## Solution
* Using Retrofit to load the JSON objects from the endpoint and convert them to plain old Java objects (POJOs).
* Using GSON to convert the JSON to plain old Java objects (POJOs).
* Using RxJava to make think easy to use.
* Using Picasso, to load the icon and automatically handle caching for the purposes of view recycling in my GridLayout.

## Remarks
* Decided to only show the icon, name, start and end date as the endpoint didn't provide venue data.
* Is compatible with multiple screen sizes.
* Error handling is added.

## TODO's
* Save state when changing orientation.
* Show visual empty state.
* Add tests.
