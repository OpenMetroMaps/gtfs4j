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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.openmetromaps.gtfs4j.csv.Routes;
import org.openmetromaps.gtfs4j.model.Route;

import au.com.bytecode.opencsv.CSVReader;

public class RoutesReader
{

	private CSVReader csvReader;

	private Map<Routes, Integer> idx = new EnumMap<>(Routes.class);

	public RoutesReader(Reader reader) throws IOException
	{
		csvReader = Util.defaultCsvReader(reader);

		String[] head = csvReader.readNext();

		for (Routes field : Routes.values()) {
			int index = Util.getIndex(head, field.getCsvName());
			idx.put(field, index);
		}
	}

	private boolean hasField(Routes field)
	{
		return idx.get(field) >= 0;
	}

	public List<Route> readAll() throws IOException
	{
		List<Route> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			Route route = parse(parts);
			routes.add(route);
		}

		csvReader.close();

		return routes;
	}

	private Route parse(String[] parts)
	{
		String id = parts[idx.get(Routes.ID)];
		String shortName = parts[idx.get(Routes.SHORT_NAME)];
		String longName = parts[idx.get(Routes.LONG_NAME)];
		String type = parts[idx.get(Routes.TYPE)];
		Route route = new Route(id, shortName, longName, type);

		if (hasField(Routes.AGENCY_ID)) {
			route.setAgencyId(parts[idx.get(Routes.AGENCY_ID)]);
		}
		if (hasField(Routes.DESC)) {
			route.setDesc(parts[idx.get(Routes.DESC)]);
		}
		if (hasField(Routes.URL)) {
			route.setUrl(parts[idx.get(Routes.URL)]);
		}
		if (hasField(Routes.COLOR)) {
			route.setColor(parts[idx.get(Routes.COLOR)]);
		}
		if (hasField(Routes.TEXT_COLOR)) {
			route.setTextColor(parts[idx.get(Routes.TEXT_COLOR)]);
		}

		return route;
	}

}
