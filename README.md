# About

A Java library for working with GTFS static data.
See the [GTFS Reference](http://gtfs.org/reference/) for details on the
format specification.

# License

This library is released under the terms of the GNU Lesser General Public
License.

See  [LGPL.md](LGPL.md) and [GPL.md](GPL.md) for details.

# CLI

Use the `show-info` task to display some basic data about a GTFS zip file:

    ./scripts/gtfs4j-cli show-info --input /tmp/gtfs/test.zip

Use the `filter-routes` task to extract a subset from an existing data file
that will contain all data belonging to some selected routes.
Routes can be matched using regular expressions:

    ./scripts/gtfs4j-cli filter-routes --input /tmp/gtfs/test.zip
        --output /tmp/gtfs/some.zip --pattern "U[0-9]+" --pattern "S[0-9]+"
