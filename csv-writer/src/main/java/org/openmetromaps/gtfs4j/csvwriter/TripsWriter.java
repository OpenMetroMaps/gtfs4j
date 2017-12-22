// Copyright 2017 Sebastian Kuerten
//
// This file is part of gtfs4j.
//
// gtfs4j is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// gtfs4j is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with gtfs4j. If not, see <http://www.gnu.org/licenses/>.

package org.openmetromaps.gtfs4j.csvwriter;

import java.io.IOException;
import java.io.Writer;

import org.openmetromaps.gtfs4j.csv.Trips;
import org.openmetromaps.gtfs4j.model.Trip;

public class TripsWriter extends BaseWriter<Trip, Trips>
{

	public TripsWriter(Writer writer) throws IOException
	{
		super(writer, Trips.class);
	}

	@Override
	public void write(Trip object)
	{
		// TODO: implement
	}

}
