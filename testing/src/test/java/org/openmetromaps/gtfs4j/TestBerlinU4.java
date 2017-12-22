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

package org.openmetromaps.gtfs4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openmetromaps.gtfs4j.csvreader.Test;
import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class TestBerlinU4
{

	public static void main(String[] args) throws IOException
	{
		List<Route> routes = Test.readRoutes();

		String routeId = null;

		for (Route route : routes) {
			if (route.getShortName().equals("U4")) {
				routeId = route.getId();
				System.out.println(String.format(
						"route id: %s, short: %s, long: %s, type: %s",
						route.getId(), route.getShortName(),
						route.getLongName(), route.getType()));
			}
		}

		List<String> tripIds = new ArrayList<>();

		List<Trip> trips = Test.readTrips();
		for (Trip trip : trips) {
			if (trip.getRouteId().equals(routeId)) {
				tripIds.add(trip.getId());
			}
		}

		System.out.println(String.format("found %d trips", tripIds.size()));

		String tripId = trips.get(0).getId();

		List<StopTime> stopTimes = Test.readStopTimes();
		for (StopTime stopTime : stopTimes) {
			if (stopTime.getTripId().equals(tripId)) {
				System.out.println(stopTime.getStopId());
			}
		}
	}

}
