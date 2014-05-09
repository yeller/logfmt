# logfmt

A Clojure library for emitting [logfmt](https://brandur.org/logfmt), a logging
format initially designed at heroku. Sample logfmt output:

```
method=get path=/ status=200 duration=0ms
```

## Installation

[clojars](http://clojars.org/logfmt)

## Usage

There are 4 main pieces to the api:

- `msg`      : takes a sequence of k/v pairs like so `(msg :foo 1)` and returns a string
- `map->msg` : turns a map into a logfmt string
- `out`      : takes a sequence of k/v pairs like so `(out :foo 1)` and prints them on stdout
- `err`      : takes a sequence of k/v pairs like so `(out :foo 1)` and prints them on stderr

The last two are macros that expand a reasonable amount of string concatenation
at compile time.

There's also a ring middleware that wraps logging in this style (to stdout), `logfmt.ring/wrap-logging`
Output looks like this:

```
method=get path=/ status=200 duration=0ms
```

This isn't meant to be an general purpose logging library or anything, it was
literally built for the environment [yeller](http://yellerapp.com) runs in -
all apps emit logs on stdout/stderr, and is piped into something else that does
log rotation, timestamp prepending etc. This is a very similar environment
to Heroku, so this may be useful there as well.

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License version 1.0
