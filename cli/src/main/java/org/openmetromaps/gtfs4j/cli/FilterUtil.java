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

package org.openmetromaps.gtfs4j.cli;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.AgencyReader;
import org.openmetromaps.gtfs4j.csvreader.CalendarReader;
import org.openmetromaps.gtfs4j.csvreader.StopTimesReader;
import org.openmetromaps.gtfs4j.csvreader.StopsReader;
import org.openmetromaps.gtfs4j.csvreader.TripsReader;
import org.openmetromaps.gtfs4j.csvwriter.AgencyWriter;
import org.openmetromaps.gtfs4j.csvwriter.CalendarWriter;
import org.openmetromaps.gtfs4j.csvwriter.StopTimesWriter;
import org.openmetromaps.gtfs4j.csvwriter.StopsWriter;
import org.openmetromaps.gtfs4j.csvwriter.TripsWriter;
import org.openmetromaps.gtfs4j.model.Agency;
import org.openmetromaps.gtfs4j.model.Calendar;
import org.openmetromaps.gtfs4j.model.Stop;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class FilterUtil
{

	public static void filterAgencies(ZipFile zipInput,
			ZipOutputStream zipOutput, Set<String> agencyIds) throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.AGENCY);
		AgencyReader reader = new AgencyReader(isr);
		List<Agency> data = reader.readAll();

		CliUtil.putEntry(zipOutput, GtfsFiles.AGENCY);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		@SuppressWarnings("resource")
		AgencyWriter writer = new AgencyWriter(osw, reader.getFields());

		int numAgencies = 0;

		for (Agency agency : data) {
			if (agencyIds.contains(agency.getId())) {
				numAgencies++;
				writer.write(agency);
			}
		}

		writer.flush();
		CliUtil.closeEntry(zipOutput);

		System.out.println(String.format("number of agencies: %d / %d",
				numAgencies, data.size()));
	}

	public static void filterTrips(ZipFile zipInput, ZipOutputStream zipOutput,
			Set<String> routeIds, Set<String> tripIds, Set<String> serviceIds)
			throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.TRIPS);
		TripsReader reader = new TripsReader(isr);
		List<Trip> data = reader.readAll();

		CliUtil.putEntry(zipOutput, GtfsFiles.TRIPS);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		@SuppressWarnings("resource")
		TripsWriter writer = new TripsWriter(osw, reader.getFields());

		for (Trip trip : data) {
			if (routeIds.contains(trip.getRouteId())) {
				tripIds.add(trip.getId());
				serviceIds.add(trip.getServiceId());
				writer.write(trip);
			}
		}

		writer.flush();
		CliUtil.closeEntry(zipOutput);

		System.out.println(String.format("number of trips: %d / %d",
				tripIds.size(), data.size()));
	}

	public static void filterCalendars(ZipFile zipInput,
			ZipOutputStream zipOutput, Set<String> serviceIds)
			throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.CALENDAR);
		CalendarReader reader = new CalendarReader(isr);
		List<Calendar> data = reader.readAll();

		CliUtil.putEntry(zipOutput, GtfsFiles.CALENDAR);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		@SuppressWarnings("resource")
		CalendarWriter writer = new CalendarWriter(osw, reader.getFields());

		int numCalendars = 0;

		for (Calendar calendar : data) {
			if (serviceIds.contains(calendar.getServiceId())) {
				numCalendars++;
				writer.write(calendar);
			}
		}

		writer.flush();
		CliUtil.closeEntry(zipOutput);

		System.out.println(String.format("number of calendars: %d / %d",
				numCalendars, data.size()));
	}

	public static void filterStopTimes(ZipFile zipInput,
			ZipOutputStream zipOutput, Set<String> tripIds, Set<String> stopIds)
			throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.STOP_TIMES);
		StopTimesReader reader = new StopTimesReader(isr);
		List<StopTime> data = reader.readAll();

		CliUtil.putEntry(zipOutput, GtfsFiles.STOP_TIMES);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		@SuppressWarnings("resource")
		StopTimesWriter writer = new StopTimesWriter(osw, reader.getFields());

		int numStopTimes = 0;

		for (StopTime stopTime : data) {
			if (tripIds.contains(stopTime.getTripId())) {
				numStopTimes++;
				stopIds.add(stopTime.getStopId());
				writer.write(stopTime);
			}
		}

		writer.flush();
		CliUtil.closeEntry(zipOutput);

		System.out.println(String.format("number of stop times: %d / %d",
				numStopTimes, data.size()));
		System.out
				.println(String.format("number of stops: %d", stopIds.size()));
	}

	public static void determineParentStations(ZipFile zipInput,
			Set<String> stopIds, Set<String> parentStationIds)
			throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.STOPS);
		StopsReader reader = new StopsReader(isr);
		List<Stop> data = reader.readAll();

		for (Stop stop : data) {
			if (stopIds.contains(stop.getId())) {
				if (stop.getParentStation() != null
						&& !stop.getParentStation().isEmpty()) {
					parentStationIds.add(stop.getParentStation());
				}
			}
		}

		System.out.println(String.format("number of parent stations: %d",
				parentStationIds.size()));
	}

	public static void filterStops(ZipFile zipInput, ZipOutputStream zipOutput,
			Set<String> stopIds, Set<String> parentStationIds)
			throws IOException
	{
		InputStreamReader isr = CliUtil.reader(zipInput, GtfsFiles.STOPS);
		StopsReader reader = new StopsReader(isr);
		List<Stop> data = reader.readAll();

		CliUtil.putEntry(zipOutput, GtfsFiles.STOPS);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		@SuppressWarnings("resource")
		StopsWriter writer = new StopsWriter(osw, reader.getFields());

		int numStops = 0;

		for (Stop stop : data) {
			if (parentStationIds.contains(stop.getId())
					|| stopIds.contains(stop.getId())) {
				numStops++;
				writer.write(stop);
			}
		}

		writer.flush();
		CliUtil.closeEntry(zipOutput);

		System.out.println(String.format("number of stops: %d / %d", numStops,
				data.size()));
	}

}