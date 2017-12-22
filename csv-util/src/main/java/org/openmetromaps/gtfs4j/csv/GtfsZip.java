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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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

public class GtfsZip
{

	private ZipFile zip;

	public GtfsZip(ZipFile zip)
	{
		this.zip = zip;
	}

	private InputStreamReader reader(GtfsFiles file) throws IOException
	{
		ZipEntry entry = zip.getEntry(file.getFilename());
		InputStream is = zip.getInputStream(entry);
		return new InputStreamReader(is, StandardCharsets.UTF_8);
	}

	public AgencyReader createAgencyReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.AGENCY);
		AgencyReader reader = new AgencyReader(isr);
		return reader;
	}

	public StopsReader createStopsReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOPS);
		StopsReader reader = new StopsReader(isr);
		return reader;
	}

	public RoutesReader createRoutesReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.ROUTES);
		RoutesReader reader = new RoutesReader(isr);
		return reader;
	}

	public TripsReader createTripsReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.TRIPS);
		TripsReader reader = new TripsReader(isr);
		return reader;
	}

	public StopTimesReader createStopTimesReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOP_TIMES);
		StopTimesReader reader = new StopTimesReader(isr);
		return reader;
	}

	public CalendarReader createCalendarReader() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.CALENDAR);
		CalendarReader reader = new CalendarReader(isr);
		return reader;
	}

	public List<Agency> readAgency() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.AGENCY);
		AgencyReader reader = new AgencyReader(isr);
		List<Agency> data = reader.readAll();
		reader.close();
		return data;
	}

	public List<Stop> readStops() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOPS);
		StopsReader reader = new StopsReader(isr);
		List<Stop> data = reader.readAll();
		reader.close();
		return data;
	}

	public List<Route> readRoutes() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.ROUTES);
		RoutesReader reader = new RoutesReader(isr);
		List<Route> data = reader.readAll();
		reader.close();
		return data;
	}

	public List<Trip> readTrips() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.TRIPS);
		TripsReader reader = new TripsReader(isr);
		List<Trip> data = reader.readAll();
		reader.close();
		return data;
	}

	public List<StopTime> readStopTimes() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOP_TIMES);
		StopTimesReader reader = new StopTimesReader(isr);
		List<StopTime> data = reader.readAll();
		reader.close();
		return data;
	}

	public List<Calendar> readCalendar() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.CALENDAR);
		CalendarReader reader = new CalendarReader(isr);
		List<Calendar> data = reader.readAll();
		reader.close();
		return data;
	}

}
