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

import org.openmetromaps.gtfs4j.csv.Stops;
import org.openmetromaps.gtfs4j.model.Stop;

public class StopsReader extends BaseReader<Stop, Stops>
{

	public StopsReader(Reader reader) throws IOException
	{
		super(reader, Stops.class);
	}

	@Override
	public List<Stop> readAll() throws IOException
	{
		List<Stop> stops = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			Stop stop = parse(parts);
			stops.add(stop);
		}

		csvReader.close();

		return stops;
	}

	private Stop parse(String[] parts)
	{
		String id = parts[idx.get(Stops.ID)];
		String name = parts[idx.get(Stops.NAME)];
		String lat = parts[idx.get(Stops.LAT)];
		String lon = parts[idx.get(Stops.LON)];

		Stop stop = new Stop(id, name, lat, lon);

		if (hasField(Stops.CODE)) {
			stop.setCode(parts[idx.get(Stops.CODE)]);
		}
		if (hasField(Stops.DESC)) {
			stop.setDesc(parts[idx.get(Stops.DESC)]);
		}
		if (hasField(Stops.ZONE_ID)) {
			stop.setZoneId(parts[idx.get(Stops.ZONE_ID)]);
		}
		if (hasField(Stops.URL)) {
			stop.setUrl(parts[idx.get(Stops.URL)]);
		}
		if (hasField(Stops.LOCATION_TYPE)) {
			stop.setLocationType(parts[idx.get(Stops.LOCATION_TYPE)]);
		}
		if (hasField(Stops.PARENT_STATION)) {
			stop.setParentStation(parts[idx.get(Stops.PARENT_STATION)]);
		}
		if (hasField(Stops.TIMEZONE)) {
			stop.setTimezone(parts[idx.get(Stops.TIMEZONE)]);
		}
		if (hasField(Stops.WHEELCHAIR_BOARDING)) {
			stop.setWheelchairBoarding(
					parts[idx.get(Stops.WHEELCHAIR_BOARDING)]);
		}

		return stop;
	}

}
