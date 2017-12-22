# About

A Java library for working with GTFS static data.
See the [GTFS Reference](http://gtfs.org/reference/) for details on the
format specification.

# License

This library is released under the terms of the GNU Lesser General Public
License.

See  [LGPL.md](LGPL.md) and [GPL.md](GPL.md) for details.

# Library

NOTE: the artifacts are not published yet. Once they are, the following will
work.

You can use this library via Maven. To use it from a Gradle project,
add our Maven server to your repository list:

    repositories {
        maven { url 'http://mvn.topobyte.de' }
    }

Afterwards, declare some dependencies:

    dependencies {
        compile 'org.openmetromaps.gtfs4j:gtfs4j-csv-reader:0.0.1'
        compile 'org.openmetromaps.gtfs4j:gtfs4j-csv-writer:0.0.1'
        compile 'org.openmetromaps.gtfs4j:gtfs4j-csv-util:0.0.1'
    }

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

Use the `show-routes` task to display information about routes:

    gtfs4j-cli show-routes --input /tmp/gtfs/test.zip

Use the `filter-routes` task to extract a subset from an existing data file
that will contain all data belonging to some selected routes.
Routes can be matched using regular expressions on their names and on
their agency by id:

    gtfs4j-cli filter-routes
        --input /tmp/gtfs/test.zip --output /tmp/gtfs/some.zip
        --agencies 1,564

    gtfs4j-cli filter-routes
        --input /tmp/gtfs/test.zip --output /tmp/gtfs/some.zip
        --pattern "U[0-9]+" --pattern "S[0-9]+"

    gtfs4j-cli filter-routes
        --input /tmp/gtfs/test.zip --output /tmp/gtfs/some.zip
        --agencies 1,564 --pattern "U[0-9]+" --pattern "S[0-9]+"

# ToDo list

* FilterRoutes: write filtered data to output zip file
* Readers and Writers for remaining files and data types
* Implement data iteration that does not require us to read all data into
  memory at once. (Iterator, Iterable, Callback)
* Add proper tests with test data and assertions
