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

import org.openmetromaps.gtfs4j.csv.StopTimes;
import org.openmetromaps.gtfs4j.model.StopTime;

import au.com.bytecode.opencsv.CSVReader;

public class StopTimesReader extends BaseReader<StopTime, StopTimes>
{

	private CSVReader csvReader;

	public StopTimesReader(Reader reader) throws IOException
	{
		super(StopTimes.class);

		csvReader = Util.defaultCsvReader(reader);

		String[] head = csvReader.readNext();
		initIndexes(head);
	}

	@Override
	public List<StopTime> readAll() throws IOException
	{
		List<StopTime> results = new ArrayList<>();

		while (true) {
			String[] parts = csvReader.readNext();
			if (parts == null) {
				break;
			}
			StopTime stopTime = parse(parts);
			results.add(stopTime);
		}

		csvReader.close();

		return results;
	}

	private StopTime parse(String[] parts)
	{
		String tripId = parts[idx.get(StopTimes.TRIP_ID)];
		String arrivalTime = parts[idx.get(StopTimes.ARRVIAL_TIME)];
		String departureTime = parts[idx.get(StopTimes.DEPARTURE_TIME)];
		String stopId = parts[idx.get(StopTimes.STOP_ID)];
		String stopSequence = parts[idx.get(StopTimes.STOP_SEQUENCE)];

		StopTime stopTime = new StopTime(tripId, arrivalTime, departureTime,
				stopId, stopSequence);

		if (hasField(StopTimes.STOP_HEADSIGN)) {
			stopTime.setStopHeadsign(parts[idx.get(StopTimes.STOP_HEADSIGN)]);
		}
		if (hasField(StopTimes.PICKUP_TYPE)) {
			stopTime.setPickupType(parts[idx.get(StopTimes.PICKUP_TYPE)]);
		}
		if (hasField(StopTimes.DROP_OFF_TYPE)) {
			stopTime.setDropOffType(parts[idx.get(StopTimes.DROP_OFF_TYPE)]);
		}
		if (hasField(StopTimes.SHAPE_DIST_TRAVELED)) {
			stopTime.setShapeDistTraveled(
					parts[idx.get(StopTimes.SHAPE_DIST_TRAVELED)]);
		}
		if (hasField(StopTimes.TIMEPOINT)) {
			stopTime.setTimepoint(parts[idx.get(StopTimes.TIMEPOINT)]);
		}

		return stopTime;
	}

}
