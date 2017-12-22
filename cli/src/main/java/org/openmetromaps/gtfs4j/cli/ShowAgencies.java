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
import java.util.List;
import java.util.zip.ZipFile;

import org.openmetromaps.gtfs4j.csv.GtfsFiles;
import org.openmetromaps.gtfs4j.csvreader.AgencyReader;
import org.openmetromaps.gtfs4j.model.Agency;

public class ShowAgencies
{

	private Path pathInput;

	public ShowAgencies(Path pathInput)
	{
		this.pathInput = pathInput;
	}

	public void execute() throws IOException
	{
		ZipFile zip = new ZipFile(pathInput.toFile());
		InputStreamReader isr = CliUtil.reader(zip, GtfsFiles.AGENCY);
		AgencyReader reader = new AgencyReader(isr);
		List<Agency> data = reader.readAll();
		reader.close();

		zip.close();

		for (Agency object : data) {
			System.out.println(
					String.format("%s: %s", object.getId(), object.getName()));
		}
	}

}
