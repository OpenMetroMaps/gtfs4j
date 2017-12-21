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
import org.openmetromaps.gtfs4j.model.Route;

import au.com.bytecode.opencsv.CSVReader;

public class RoutesReader
{

	private CSVReader csvReader;

	private int idxId;
	private int idxShortName;
	private int idxLongName;
	private int idxType;

	public RoutesReader(Reader reader) throws IOException
	{
		csvReader = new CSVReader(reader, ',', '"');

		String[] head = csvReader.readNext();

		idxId = Util.getIndex(head, "route_id");
		idxShortName = Util.getIndex(head, "route_short_name");
		idxLongName = Util.getIndex(head, "route_long_name");
		idxType = Util.getIndex(head, "route_type");
	}

	public List<Route> readAll() throws IOException
	{
		List<Route> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			String id = parts[idxId];
			String shortName = parts[idxShortName];
			String longName = parts[idxLongName];
			String type = parts[idxType];
			routes.add(new Route(id, shortName, longName, type));
		}

		csvReader.close();

		return routes;
	}

}
