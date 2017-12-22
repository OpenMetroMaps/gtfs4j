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

import org.openmetromaps.gtfs4j.csv.Calendars;
import org.openmetromaps.gtfs4j.model.Calendar;

public class CalendarWriter extends BaseWriter<Calendar, Calendars>
{

	public CalendarWriter(Writer writer, List<Calendars> fields)
			throws IOException
	{
		super(writer, Calendars.class, fields);
	}

	@Override
	public String get(Calendar object, Calendars field)
	{
		switch (field) {
		case END_DATE:
			return object.getEndDate();
		case FRIDAY:
			return object.getFriday();
		case MONDAY:
			return object.getMonday();
		case SATURDAY:
			return object.getSaturday();
		case SERVICE_ID:
			return object.getServiceId();
		case START_DATE:
			return object.getStartDate();
		case SUNDAY:
			return object.getSunday();
		case THURSDAY:
			return object.getThursday();
		case TUESDAY:
			return object.getTuesday();
		case WEDNESDAY:
			return object.getWednesday();
		default:
			return null;
		}
	}

}
