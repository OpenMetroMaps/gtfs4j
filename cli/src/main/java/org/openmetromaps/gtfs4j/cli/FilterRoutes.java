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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.csvwriter.RoutesWriter;
import org.openmetromaps.gtfs4j.model.Route;

public class FilterRoutes
{

	private Path pathInput;
	private Path pathOutput;
	private List<String> agencyIds;
	private List<String> patternsSpecs;

	private Set<String> agencyIdSet = new HashSet<>();
	private Set<String> referencedAgencyIds = new HashSet<>();

	public FilterRoutes(Path pathInput, Path pathOutput, List<String> agencyIds,
			List<String> patterns)
	{
		this.pathInput = pathInput;
		this.pathOutput = pathOutput;
		this.agencyIds = agencyIds;
		this.patternsSpecs = patterns;

		agencyIdSet.addAll(agencyIds);
	}

	private List<Pattern> patterns = new ArrayList<>();

	private ZipFile zipInput;
	private ZipOutputStream zipOutput;

	private Set<String> routeIds = new HashSet<>();
	private Set<String> tripIds = new HashSet<>();
	private Set<String> serviceIds = new HashSet<>();
	private Set<String> stopIds = new HashSet<>();
	private Set<String> parentStationIds = new HashSet<>();

	public void execute() throws IOException
	{
		System.out.println("input: " + pathInput);
		System.out.println("output: " + pathOutput);

		if (agencyIds.isEmpty()) {
			System.out.println("no agencies specified");
		}
		for (String agencyId : agencyIds) {
			System.out.println("agency id: " + agencyId);
		}

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

		zipInput = new ZipFile(pathInput.toFile());

		OutputStream os = Files.newOutputStream(pathOutput);
		zipOutput = new ZipOutputStream(os);

		filter();

		zipInput.close();
		zipOutput.close();
	}

	private void filter() throws IOException
	{
		System.out.println("filtering routes...");
		filterRoutes();

		System.out.println("filtering agencies...");
		FilterUtil.filterAgencies(zipInput, zipOutput, referencedAgencyIds);

		System.out.println("filtering trips...");
		FilterUtil.filterTrips(zipInput, zipOutput, routeIds, tripIds,
				serviceIds);

		System.out.println("filtering calendars");
		FilterUtil.filterCalendars(zipInput, zipOutput, serviceIds);

		System.out.println("filtering stop times...");
		FilterUtil.filterStopTimes(zipInput, zipOutput, tripIds, stopIds);

		System.out.println("determining parent stations...");
		FilterUtil.determineParentStations(zipInput, stopIds, parentStationIds);

		System.out.println("filtering stops...");
		FilterUtil.filterStops(zipInput, zipOutput, stopIds, parentStationIds);
	}

	private InputStreamReader reader(GtfsFiles file) throws IOException
	{
		return CliUtil.reader(zipInput, file);
	}

	private void putEntry(GtfsFiles file) throws IOException
	{
		CliUtil.putEntry(zipOutput, file);
	}

	private void closeEntry() throws IOException
	{
		CliUtil.closeEntry(zipOutput);
	}

	private void filterRoutes() throws IOException
	{
		InputStreamReader isr = reader(GtfsFiles.ROUTES);
		RoutesReader reader = new RoutesReader(isr);
		List<Route> routes = reader.readAll();

		putEntry(GtfsFiles.ROUTES);
		OutputStreamWriter osw = new OutputStreamWriter(zipOutput);
		RoutesWriter writer = new RoutesWriter(osw, reader.getFields());

		for (Route route : routes) {
			String shortName = route.getShortName();

			if (patterns.isEmpty() && agencyIds.isEmpty()) {
				use(writer, route);
				continue;
			}

			boolean use = true;

			if (!agencyIds.isEmpty()) {
				use &= agencyIdSet.contains(route.getAgencyId());
			}

			if (!patterns.isEmpty()) {
				boolean somePatternFits = false;
				for (Pattern pattern : patterns) {
					if (pattern.matcher(shortName).matches()) {
						somePatternFits = true;
						break;
					}
				}
				use &= somePatternFits;
			}

			if (use) {
				use(writer, route);
			}
		}

		System.out.println(String.format("number of routes: %d / %d",
				routeIds.size(), routes.size()));

		writer.flush();
		closeEntry();
	}

	private void use(RoutesWriter writer, Route route)
	{
		System.out.println(
				String.format("%s: %s", route.getId(), route.getShortName()));
		routeIds.add(route.getId());
		writer.write(route);
		referencedAgencyIds.add(route.getAgencyId());
	}

}
