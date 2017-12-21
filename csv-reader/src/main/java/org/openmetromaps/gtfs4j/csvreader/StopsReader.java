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

package org.openmetromaps.gtfs4j.csvreader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.openmetromaps.gtfs4j.Util;
import org.openmetromaps.gtfs4j.model.Stop;

import au.com.bytecode.opencsv.CSVReader;

public class StopsReader
{

	private CSVReader csvReader;

	private int idxId;
	private int idxName;
	private int idxLon;
	private int idxLat;

	public StopsReader(Reader reader) throws IOException
	{
		csvReader = new CSVReader(reader, ',', '"');

		String[] head = csvReader.readNext();

		idxId = Util.getIndex(head, "stop_id");
		idxName = Util.getIndex(head, "stop_name");
		idxLon = Util.getIndex(head, "stop_lon");
		idxLat = Util.getIndex(head, "stop_lat");
	}

	public List<Stop> readAll() throws IOException
	{
		List<Stop> stops = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			String id = parts[idxId];
			String name = parts[idxName];
			String lon = parts[idxLon];
			String lat = parts[idxLat];
			stops.add(new Stop(id, name, lat, lon));
		}

		csvReader.close();

		return stops;
	}

}
