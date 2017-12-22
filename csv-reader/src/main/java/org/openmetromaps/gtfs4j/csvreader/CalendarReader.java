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

import org.openmetromaps.gtfs4j.csv.Calendars;
import org.openmetromaps.gtfs4j.model.Calendar;

import au.com.bytecode.opencsv.CSVReader;

public class CalendarReader extends BaseReader<Calendar, Calendars>
{

	private CSVReader csvReader;

	public CalendarReader(Reader reader) throws IOException
	{
		super(Calendars.class);

		csvReader = Util.defaultCsvReader(reader);

		String[] head = csvReader.readNext();

		initIndexes(head);
	}

	@Override
	public List<Calendar> readAll() throws IOException
	{
		List<Calendar> routes = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			Calendar calendar = parse(parts);
			routes.add(calendar);
		}

		csvReader.close();

		return routes;
	}

	private Calendar parse(String[] parts)
	{
		String serviceId = parts[idx.get(Calendars.SERVICE_ID)];
		String monday = parts[idx.get(Calendars.MONDAY)];
		String tuesday = parts[idx.get(Calendars.TUESDAY)];
		String wednesday = parts[idx.get(Calendars.WEDNESDAY)];
		String thursday = parts[idx.get(Calendars.THURSDAY)];
		String friday = parts[idx.get(Calendars.FRIDAY)];
		String saturday = parts[idx.get(Calendars.SATURDAY)];
		String sunday = parts[idx.get(Calendars.SUNDAY)];
		String startDate = parts[idx.get(Calendars.START_DATE)];
		String endDate = parts[idx.get(Calendars.END_DATE)];

		Calendar calendar = new Calendar(serviceId, monday, tuesday, wednesday,
				thursday, friday, saturday, sunday, startDate, endDate);

		return calendar;
	}

}
