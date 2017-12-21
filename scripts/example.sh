#!/bin/bash

DIR=$(dirname $0)
CMD="$DIR/gtfs4j.sh"
CLASS="org.openmetromaps.gtfs4j.cli.Test"

exec "$CMD" "$CLASS" "$@"
