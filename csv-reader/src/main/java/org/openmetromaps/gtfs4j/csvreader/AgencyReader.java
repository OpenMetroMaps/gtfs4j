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

import org.openmetromaps.gtfs4j.csv.Agencies;
import org.openmetromaps.gtfs4j.model.Agency;

public class AgencyReader extends BaseReader<Agency, Agencies>
{

	public AgencyReader(Reader reader) throws IOException
	{
		super(reader, Agencies.class);
	}

	@Override
	public List<Agency> readAll() throws IOException
	{
		List<Agency> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			Agency agency = parse(parts);
			routes.add(agency);
		}

		csvReader.close();

		return routes;
	}

	private Agency parse(String[] parts)
	{
		String name = parts[idx.get(Agencies.NAME)];
		String url = parts[idx.get(Agencies.URL)];
		String timezone = parts[idx.get(Agencies.TIMEZONE)];

		Agency agency = new Agency(name, url, timezone);

		if (hasField(Agencies.ID)) {
			agency.setId(parts[idx.get(Agencies.ID)]);
		}
		if (hasField(Agencies.LANG)) {
			agency.setLang(parts[idx.get(Agencies.LANG)]);
		}
		if (hasField(Agencies.PHONE)) {
			agency.setPhone(parts[idx.get(Agencies.PHONE)]);
		}
		if (hasField(Agencies.FARE_URL)) {
			agency.setFareUrl(parts[idx.get(Agencies.FARE_URL)]);
		}
		if (hasField(Agencies.EMAIL)) {
			agency.setEmail(parts[idx.get(Agencies.EMAIL)]);
		}

		return agency;
	}

}
