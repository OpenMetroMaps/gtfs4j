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
import java.nio.file.Paths;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.GtfsDirectory;
import org.openmetromaps.gtfs4j.model.Agency;
import org.openmetromaps.gtfs4j.model.Calendar;
import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.Stop;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class Test
{

	public static GtfsDirectory dir = new GtfsDirectory(Paths.get("/tmp/gtfs"));

	public static List<Agency> readAgency() throws IOException
	{
		return dir.readAgency();
	}

	public static List<Stop> readStops() throws IOException
	{
		return dir.readStops();
	}

	public static List<Route> readRoutes() throws IOException
	{
		return dir.readRoutes();
	}

	public static List<Trip> readTrips() throws IOException
	{
		return dir.readTrips();
	}

	public static List<StopTime> readStopTimes() throws IOException
	{
		return dir.readStopTimes();
	}

	public static List<Calendar> readCalendar() throws IOException
	{
		return dir.readCalendar();
	}

}
