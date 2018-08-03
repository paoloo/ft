# ft
A simple clojure+clojurescript word scrambling test

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start Clojure backend + Clojurescript frontend, just type

    lein up

If, otherwise, want to startup the serices one by one:
- To start the api server for the application, run: `lein ring server`
- To start the same service but using a Heroku-style app: `lein with-profile production trampoline run`
- To run just clojurescript: `lein figwheel`

## Usage

- Endpoint: **POST /scramble**
- format: **input:** application/json, **output:** application/json
- Parameters: **word1**, a set of characteres that should be verified if can be rearranged into **word2**.
- exemples:

First, when first string characters can be rearranged to match the second one:
```bash
$ curl -XPOST -s -H 'Content-Type : application/json'\
       -d '{"word1":"rekqodlw", "word2":"world"}'\
       http://localhost:5000/scramble | jq '.'
{
  "scramble": true
}
```
Now, when it cannot:
```bash
$ curl -XPOST -s -H 'Content-Type : application/json'\
       -d '{"word1":"katas", "word2":"steak"}'\
       http://localhost:5000/scramble | jq '.'
{
  "scramble": false
}
```

## Tests

lain test

## License

Copyright © 2018 Paolo Oliveira
