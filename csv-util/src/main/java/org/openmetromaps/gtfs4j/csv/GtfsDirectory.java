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

package org.openmetromaps.gtfs4j.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

public class GtfsDirectory
{

	private Path path;

	public GtfsDirectory(Path path)
	{
		this.path = path;
	}

	private BufferedReader reader(Path path) throws IOException
	{
		return Files.newBufferedReader(path, StandardCharsets.UTF_8);
	}

	private Path path(GtfsFiles gtfsFile)
	{
		return path.resolve(gtfsFile.getFilename());
	}

	public AgencyReader createAgencyReader() throws IOException
	{
		Path path = path(GtfsFiles.AGENCY);
		BufferedReader br = reader(path);

		AgencyReader reader = new AgencyReader(br);
		return reader;
	}

	public StopsReader createStopsReader() throws IOException
	{
		Path path = path(GtfsFiles.STOPS);
		BufferedReader br = reader(path);

		StopsReader reader = new StopsReader(br);
		return reader;
	}

	public RoutesReader createRoutesReader() throws IOException
	{
		Path path = path(GtfsFiles.ROUTES);
		BufferedReader br = reader(path);

		RoutesReader reader = new RoutesReader(br);
		return reader;
	}

	public TripsReader createTripsReader() throws IOException
	{
		Path path = path(GtfsFiles.TRIPS);
		BufferedReader br = reader(path);

		TripsReader reader = new TripsReader(br);
		return reader;
	}

	public StopTimesReader createStopTimesReader() throws IOException
	{
		Path path = path(GtfsFiles.STOP_TIMES);
		BufferedReader br = reader(path);

		StopTimesReader reader = new StopTimesReader(br);
		return reader;
	}

	public CalendarReader createCalendarReader() throws IOException
	{
		Path path = path(GtfsFiles.CALENDAR);
		BufferedReader br = reader(path);

		CalendarReader reader = new CalendarReader(br);
		return reader;
	}

	public List<Agency> readAgency() throws IOException
	{
		Path path = path(GtfsFiles.AGENCY);
		BufferedReader br = reader(path);

		AgencyReader reader = new AgencyReader(br);
		List<Agency> list = reader.readAll();
		reader.close();
		return list;
	}

	public List<Stop> readStops() throws IOException
	{
		Path path = path(GtfsFiles.STOPS);
		BufferedReader br = reader(path);

		StopsReader reader = new StopsReader(br);
		List<Stop> list = reader.readAll();
		reader.close();
		return list;
	}

	public List<Route> readRoutes() throws IOException
	{
		Path path = path(GtfsFiles.ROUTES);
		BufferedReader br = reader(path);

		RoutesReader reader = new RoutesReader(br);
		List<Route> list = reader.readAll();
		reader.close();
		return list;
	}

	public List<Trip> readTrips() throws IOException
	{
		Path path = path(GtfsFiles.TRIPS);
		BufferedReader br = reader(path);

		TripsReader reader = new TripsReader(br);
		List<Trip> list = reader.readAll();
		reader.close();
		return list;
	}

	public List<StopTime> readStopTimes() throws IOException
	{
		Path path = path(GtfsFiles.STOP_TIMES);
		BufferedReader br = reader(path);

		StopTimesReader reader = new StopTimesReader(br);
		List<StopTime> list = reader.readAll();
		reader.close();
		return list;
	}

	public List<Calendar> readCalendar() throws IOException
	{
		Path path = path(GtfsFiles.CALENDAR);
		BufferedReader br = reader(path);

		CalendarReader reader = new CalendarReader(br);
		List<Calendar> list = reader.readAll();
		reader.close();
		return list;
	}

}
