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
import java.nio.file.Path;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.GtfsZip;
import org.openmetromaps.gtfs4j.model.Stop;

public class ShowStations
{

	private Path pathInput;

	public ShowStations(Path pathInput)
	{
		this.pathInput = pathInput;
	}

	public void execute() throws IOException
	{
		GtfsZip zip = new GtfsZip(pathInput);

		List<Stop> data = zip.readStops();

		zip.close();

		for (Stop object : data) {
			String name = object.getName();

			StringBuilder line = new StringBuilder();
			line.append(name);

			boolean isParent = object.getLocationType().equals("1");
			if (isParent) {
				System.out.println(line.toString());
			}
		}
	}

}
