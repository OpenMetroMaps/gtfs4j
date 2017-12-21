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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class TestFilterSU
{

	public static void main(String[] args) throws IOException
	{
		List<Route> routes = Test.readRoutes();

		Pattern patternS = Pattern.compile("S[0-9]+");
		Pattern patternU = Pattern.compile("U[0-9]+");

		Set<String> routeIds = new HashSet<>();

		for (Route route : routes) {
			String shortName = route.getShortName();
			boolean isS = patternS.matcher(shortName).matches();
			boolean isU = patternU.matcher(shortName).matches();
			if (isS || isU) {
				System.out.println(
						String.format("%s: %s", route.getId(), shortName));
				routeIds.add(route.getId());
			}
		}

		System.out.println(String.format("number of routes: %d / %d",
				routeIds.size(), routes.size()));

		Set<String> tripIds = new HashSet<>();

		List<Trip> trips = Test.readTrips();
		for (Trip trip : trips) {
			if (routeIds.contains(trip.getRouteId())) {
				tripIds.add(trip.getId());
			}
		}

		System.out.println(String.format("number of trips: %d / %d",
				tripIds.size(), trips.size()));

		Set<String> stopIds = new HashSet<>();
		int numStopTimes = 0;

		List<StopTime> stopTimes = Test.readStopTimes();
		for (StopTime stopTime : stopTimes) {
			if (tripIds.contains(stopTime.getTripId())) {
				numStopTimes++;
				stopIds.add(stopTime.getStopId());
			}
		}

		System.out.println(String.format("number of stop times: %d / %d",
				numStopTimes, stopTimes.size()));
		System.out
				.println(String.format("number of stops: %d", stopIds.size()));
	}

}
