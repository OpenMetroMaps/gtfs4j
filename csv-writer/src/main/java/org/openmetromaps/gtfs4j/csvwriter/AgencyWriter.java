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

import org.openmetromaps.gtfs4j.csv.Agencies;
import org.openmetromaps.gtfs4j.model.Agency;

public class AgencyWriter extends BaseWriter<Agency, Agencies>
{

	public AgencyWriter(Writer writer, List<Agencies> fields) throws IOException
	{
		super(writer, Agencies.class, fields);
	}

	@Override
	public String get(Agency object, Agencies field)
	{
		switch (field) {
		case EMAIL:
			return object.getEmail();
		case FARE_URL:
			return object.getFareUrl();
		case ID:
			return object.getId();
		case LANG:
			return object.getLang();
		case NAME:
			return object.getName();
		case PHONE:
			return object.getPhone();
		case TIMEZONE:
			return object.getTimezone();
		case URL:
			return object.getUrl();
		default:
			return null;
		}
	}

}
