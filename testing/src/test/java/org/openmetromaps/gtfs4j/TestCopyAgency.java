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

package org.openmetromaps.gtfs4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.Agencies;
import org.openmetromaps.gtfs4j.csvreader.AgencyReader;
import org.openmetromaps.gtfs4j.csvreader.Test;
import org.openmetromaps.gtfs4j.csvwriter.AgencyWriter;
import org.openmetromaps.gtfs4j.model.Agency;

public class TestCopyAgency
{

	public static void main(String[] args) throws IOException
	{
		AgencyReader reader = Test.agencyReader();
		List<Agency> data = reader.readAll();
		List<Agencies> fields = reader.getFields();

		Path output = Paths.get("/tmp/agency.txt");
		AgencyWriter writer = new AgencyWriter(
				Files.newBufferedWriter(output, StandardCharsets.UTF_8),
				fields);

		for (Agency agency : data) {
			writer.write(agency);
		}

		writer.close();
	}

}
