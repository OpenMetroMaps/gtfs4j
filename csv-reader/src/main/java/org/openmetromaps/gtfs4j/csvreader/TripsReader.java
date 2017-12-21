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
import org.openmetromaps.gtfs4j.model.Trip;

import au.com.bytecode.opencsv.CSVReader;

public class TripsReader
{

	private CSVReader csvReader;

	private int idxRouteId;
	private int idxServiceId;
	private int idxId;

	public TripsReader(Reader reader) throws IOException
	{
		csvReader = Util.defaultCsvReader(reader);

		String[] head = csvReader.readNext();

		idxRouteId = Util.getIndex(head, "route_id");
		idxServiceId = Util.getIndex(head, "service_id");
		idxId = Util.getIndex(head, "trip_id");
	}

	public List<Trip> readAll() throws IOException
	{
		List<Trip> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			String routeId = parts[idxRouteId];
			String serviceId = parts[idxServiceId];
			String id = parts[idxId];
			routes.add(new Trip(routeId, serviceId, id));
		}

		csvReader.close();

		return routes;
	}

}
