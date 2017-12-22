# About

A Java library for working with GTFS static data.
See the [GTFS Reference](http://gtfs.org/reference/) for details on the
format specification.

# License

This library is released under the terms of the GNU Lesser General Public
License.

See  [LGPL.md](LGPL.md) and [GPL.md](GPL.md) for details.

# CLI

The main script is `gtfs4j-cli`. It is contained in the `scripts` directory
and can be executed from the main directory like this:
`./scripts/gtfs4j-cli`. Alternatively, add the `scripts` directory to your
`PATH` environment variable in order to run `gtfs4j-cli` without specifying
its location each time. The following examples assume you have done that.

Use the `show-info` task to display some basic data about a GTFS zip file:

    gtfs4j-cli show-info --input /tmp/gtfs/test.zip

Use the `show-agencies` task to display information about agencies:

    gtfs4j-cli show-agencies --input /tmp/gtfs/test.zip

Use the `filter-routes` task to extract a subset from an existing data file
that will contain all data belonging to some selected routes.
Routes can be matched using regular expressions:

    gtfs4j-cli filter-routes --input /tmp/gtfs/test.zip
        --output /tmp/gtfs/some.zip --pattern "U[0-9]+" --pattern "S[0-9]+"
