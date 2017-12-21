#!/bin/bash

DIR=$(dirname $0)
CMD="$DIR/gtfs4j.sh"
CLASS="de.topobyte.Test"

exec "$CMD" "$CLASS" "$@"
