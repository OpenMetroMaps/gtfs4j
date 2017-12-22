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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.AgencyReader;
import org.openmetromaps.gtfs4j.csvreader.RoutesReader;
import org.openmetromaps.gtfs4j.model.Agency;
import org.openmetromaps.gtfs4j.model.Route;

public class ShowRoutes
{

	private Path pathInput;
	private boolean expandAgency;

	private Map<String, Agency> agencies = new HashMap<>();

	public ShowRoutes(Path pathInput, boolean expandAgency)
	{
		this.pathInput = pathInput;
		this.expandAgency = expandAgency;
	}

	public void execute() throws IOException
	{
		ZipFile zip = new ZipFile(pathInput.toFile());

		if (expandAgency) {
			loadAgencies(zip);
		}

		InputStreamReader isr = CliUtil.reader(zip, GtfsFiles.ROUTES);
		RoutesReader reader = new RoutesReader(isr);
		List<Route> data = reader.readAll();

		for (Route object : data) {
			String longName = object.getLongName();
			String shortName = object.getShortName();

			boolean hasLongName = longName != null && !longName.isEmpty();
			boolean hasShortName = shortName != null && !shortName.isEmpty();

			String name = null;
			if (hasLongName && hasShortName) {
				name = String.format("%s (%s)", longName, shortName);
			} else if (hasShortName) {
				name = shortName;
			} else if (hasLongName) {
				name = longName;
			}

			StringBuilder line = new StringBuilder();
			line.append(name);

			String agencyId = object.getAgencyId();
			if (agencyId != null) {
				line.append(", agency: ");
				if (!expandAgency) {
					line.append(agencyId);
				} else {
					Agency agency = agencies.get(agencyId);
					line.append(agency.getName());
				}
			}

			System.out.println(line.toString());
		}
	}

	private void loadAgencies(ZipFile zip) throws ZipException, IOException
	{
		InputStreamReader isr = CliUtil.reader(zip, GtfsFiles.AGENCY);
		AgencyReader reader = new AgencyReader(isr);
		List<Agency> data = reader.readAll();
		for (Agency agency : data) {
			String id = agency.getId();
			agencies.put(id, agency);
		}
	}

}
