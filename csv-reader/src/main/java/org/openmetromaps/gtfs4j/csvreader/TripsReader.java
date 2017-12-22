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

package org.openmetromaps.gtfs4j.csvreader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.Trips;
import org.openmetromaps.gtfs4j.model.Trip;

public class TripsReader extends BaseReader<Trip, Trips>
{

	public TripsReader(Reader reader) throws IOException
	{
		super(reader, Trips.class);
	}

	@Override
	public List<Trip> readAll() throws IOException
	{
		List<Trip> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			Trip trip = parse(parts);
			routes.add(trip);
		}

		csvReader.close();

		return routes;
	}

	private Trip parse(String[] parts)
	{
		String routeId = parts[idx.get(Trips.ROUTE_ID)];
		String serviceId = parts[idx.get(Trips.SERVICE_ID)];
		String id = parts[idx.get(Trips.ID)];

		Trip trip = new Trip(routeId, serviceId, id);

		if (hasField(Trips.HEADSIGN)) {
			trip.setHeadsign(parts[idx.get(Trips.HEADSIGN)]);
		}
		if (hasField(Trips.SHORT_NAME)) {
			trip.setShortName(parts[idx.get(Trips.SHORT_NAME)]);
		}
		if (hasField(Trips.DIRECTION_ID)) {
			trip.setDirectionId(parts[idx.get(Trips.DIRECTION_ID)]);
		}
		if (hasField(Trips.BLOCK_ID)) {
			trip.setBlockId(parts[idx.get(Trips.BLOCK_ID)]);
		}
		if (hasField(Trips.SHAPE_ID)) {
			trip.setShapeId(parts[idx.get(Trips.SHAPE_ID)]);
		}
		if (hasField(Trips.WHEELCHAIR_ACCESSIBLE)) {
			trip.setWheelchairAccessible(
					parts[idx.get(Trips.WHEELCHAIR_ACCESSIBLE)]);
		}
		if (hasField(Trips.BIKES_ALLOWED)) {
			trip.setBikesAllowed(parts[idx.get(Trips.BIKES_ALLOWED)]);
		}

		return trip;
	}

}
