// Copyright 2017 Sebastian Kuerten
//
// This file is part of OpenMetroMaps.
//
// OpenMetroMaps is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// OpenMetroMaps is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with OpenMetroMaps. If not, see <http://www.gnu.org/licenses/>.

package org.openmetromaps.gtfs4j.cli;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.csvreader.StopTimesReader;
import org.openmetromaps.gtfs4j.csvreader.StopsReader;
import org.openmetromaps.gtfs4j.csvreader.TripsReader;
import org.openmetromaps.gtfs4j.model.Route;
import org.openmetromaps.gtfs4j.model.Stop;
import org.openmetromaps.gtfs4j.model.StopTime;
import org.openmetromaps.gtfs4j.model.Trip;

public class FilterRoutes
{

	private Path pathInput;
	private Path pathOutput;
	private List<String> patternsSpecs;

	public FilterRoutes(Path pathInput, Path pathOutput, List<String> patterns)
	{
		this.pathInput = pathInput;
		this.pathOutput = pathOutput;
		this.patternsSpecs = patterns;
	}

	private List<Pattern> patterns = new ArrayList<>();

	private ZipFile zip;

	private Set<String> routeIds = new HashSet<>();
	private Set<String> tripIds = new HashSet<>();
	private Set<String> stopIds = new HashSet<>();
	private int numStopTimes = 0;
	private Set<String> parentStationIds = new HashSet<>();

	public void execute() throws IOException
	{
		System.out.println("input: " + pathInput);
		System.out.println("output: " + pathOutput);

		if (patternsSpecs.isEmpty()) {
			System.out.println("no patterns specified");
		}
		for (String patternSpec : patternsSpecs) {
			System.out.println("pattern: " + patternSpec);
		}

		for (String patternSpec : patternsSpecs) {
			Pattern pattern = Pattern.compile(patternSpec);
			patterns.add(pattern);
		}

		long size = Files.size(pathInput);
		System.out.println(String.format("Input file size: %d bytes", size));

		zip = new ZipFile(pathInput.toFile());

		filter();

		zip.close();
	}

	private void filter() throws IOException
	{
		filterRoutes();

		filterTrips();

		filterStopTimes();

		filterStops();
	}

	private InputStreamReader reader(GtfsFiles file) throws IOException
	{
		return CliUtil.reader(zip, file);
	}

	private void filterRoutes() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.ROUTES);
		RoutesReader reader = new RoutesReader(isr);
		List<Route> routes = reader.readAll();

		for (Route route : routes) {
			String shortName = route.getShortName();

			boolean use = false;
			for (Pattern pattern : patterns) {
				if (pattern.matcher(shortName).matches()) {
					use = true;
					break;
				}
			}

			if (!use) {
				continue;
			}

			System.out
					.println(String.format("%s: %s", route.getId(), shortName));
			routeIds.add(route.getId());
		}

		System.out.println(String.format("number of routes: %d / %d",
				routeIds.size(), routes.size()));
	}

	private void filterTrips() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.TRIPS);
		TripsReader reader = new TripsReader(isr);

		List<Trip> trips = reader.readAll();
		for (Trip trip : trips) {
			if (routeIds.contains(trip.getRouteId())) {
				tripIds.add(trip.getId());
			}
		}

		System.out.println(String.format("number of trips: %d / %d",
				tripIds.size(), trips.size()));
	}

	private void filterStopTimes() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOP_TIMES);
		StopTimesReader reader = new StopTimesReader(isr);
		List<StopTime> stopTimes = reader.readAll();

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

	private void filterStops() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.STOPS);
		StopsReader reader = new StopsReader(isr);
		List<Stop> stops = reader.readAll();

		for (Stop stop : stops) {
			if (stopIds.contains(stop.getId())) {
				System.out.println(stop.getName());
				if (stop.getParentStation() != null
						&& !stop.getParentStation().isEmpty()) {
					parentStationIds.add(stop.getParentStation());
				}
			}
		}

		System.out.println(String.format("number of parent stations: %d",
				parentStationIds.size()));
	}

}
