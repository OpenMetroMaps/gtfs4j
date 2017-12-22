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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.AgencyReader;
import org.openmetromaps.gtfs4j.csvreader.CalendarReader;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.csvreader.StopTimesReader;
import org.openmetromaps.gtfs4j.csvreader.StopsReader;
import org.openmetromaps.gtfs4j.csvreader.TripsReader;
import org.openmetromaps.gtfs4j.model.Agency;
import org.openmetromaps.gtfs4j.model.Calendar;
import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.Stop;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class Test
{

	public static Path path = Paths.get("/tmp/gtfs");

	private static BufferedReader reader(Path path) throws IOException
	{
		return Files.newBufferedReader(path, StandardCharsets.UTF_8);
	}

	public static List<Agency> readAgency() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_AGENCY);
		BufferedReader br = reader(path);

		AgencyReader reader = new AgencyReader(br);
		List<Agency> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Route> readRoutes() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_ROUTES);
		BufferedReader br = reader(path);

		RoutesReader reader = new RoutesReader(br);
		List<Route> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Trip> readTrips() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_TRIPS);
		BufferedReader br = reader(path);

		TripsReader reader = new TripsReader(br);
		List<Trip> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Stop> readStops() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_STOPS);
		BufferedReader br = reader(path);

		StopsReader reader = new StopsReader(br);
		List<Stop> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<StopTime> readStopTimes() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_STOP_TIMES);
		BufferedReader br = reader(path);

		StopTimesReader reader = new StopTimesReader(br);
		List<StopTime> list = reader.readAll();
		br.close();
		return list;
	}

	public static List<Calendar> readCalendar() throws IOException
	{
		Path path = Test.path.resolve(GtfsFiles.NAME_CALENDAR);
		BufferedReader br = reader(path);

		CalendarReader reader = new CalendarReader(br);
		List<Calendar> list = reader.readAll();
		br.close();
		return list;
	}

}
