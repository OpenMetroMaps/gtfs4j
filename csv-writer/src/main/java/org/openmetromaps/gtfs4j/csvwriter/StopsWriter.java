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

package org.openmetromaps.gtfs4j.csvwriter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.Stops;
import org.openmetromaps.gtfs4j.model.Stop;

public class StopsWriter extends BaseWriter<Stop, Stops>
{

	public StopsWriter(Writer writer, List<Stops> fields) throws IOException
	{
		super(writer, Stops.class, fields);
	}

	@Override
	public String get(Stop object, Stops field)
	{
		switch (field) {
		case CODE:
			return object.getCode();
		case DESC:
			return object.getDesc();
		case ID:
			return object.getId();
		case LAT:
			return object.getLat();
		case LOCATION_TYPE:
			return object.getLocationType();
		case LON:
			return object.getLon();
		case NAME:
			return object.getName();
		case PARENT_STATION:
			return object.getParentStation();
		case TIMEZONE:
			return object.getTimezone();
		case URL:
			return object.getUrl();
		case WHEELCHAIR_BOARDING:
			return object.getWheelchairBoarding();
		case ZONE_ID:
			return object.getZoneId();
		default:
			return null;
		}
	}

}
