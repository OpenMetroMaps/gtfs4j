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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.model.Route;

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

	private Set<String> routeIds = new HashSet<>();

	public void execute() throws IOException
	{
		System.out.println("input: " + pathInput);
		System.out.println("output: " + pathOutput);

		for (String patternSpec : patternsSpecs) {
			System.out.println("pattern: " + patternSpec);
		}

		for (String patternSpec : patternsSpecs) {
			Pattern pattern = Pattern.compile(patternSpec);
			patterns.add(pattern);
		}

		long size = Files.size(pathInput);
		System.out.println(String.format("Input file size", "%d bytes", size));

		ZipFile zip = new ZipFile(pathInput.toFile());

		List<Route> routes = filterRoutes(zip);
		System.out.println(String.format("number of routes: %d / %d",
				routeIds.size(), routes.size()));

		zip.close();
	}

	private List<Route> filterRoutes(ZipFile zip) throws IOException
	{
		ZipEntry entryRoutes = zip.getEntry(GtfsFiles.ROUTES.getFilename());
		InputStream isRoutes = zip.getInputStream(entryRoutes);
		InputStreamReader isrRoutes = new InputStreamReader(isRoutes);
		RoutesReader routesReader = new RoutesReader(isrRoutes);
		List<Route> routes = routesReader.readAll();

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

		return routes;
	}

}
