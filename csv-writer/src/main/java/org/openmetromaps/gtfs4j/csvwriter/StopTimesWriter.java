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

import org.openmetromaps.gtfs4j.csv.StopTimes;
import org.openmetromaps.gtfs4j.model.StopTime;

public class StopTimesWriter extends BaseWriter<StopTime, StopTimes>
{

	public StopTimesWriter(Writer writer, List<StopTimes> fields)
			throws IOException
	{
		super(writer, StopTimes.class, fields);
	}

	@Override
	public String get(StopTime object, StopTimes field)
	{
		switch (field) {
		case ARRVIAL_TIME:
			return object.getArrivalTime();
		case DEPARTURE_TIME:
			return object.getDepartureTime();
		case DROP_OFF_TYPE:
			return object.getDropOffType();
		case PICKUP_TYPE:
			return object.getPickupType();
		case SHAPE_DIST_TRAVELED:
			return object.getShapeDistTraveled();
		case STOP_HEADSIGN:
			return object.getStopHeadsign();
		case STOP_ID:
			return object.getStopId();
		case STOP_SEQUENCE:
			return object.getStopSequence();
		case TIMEPOINT:
			return object.getTimepoint();
		case TRIP_ID:
			return object.getTripId();
		default:
			return null;
		}
	}

}
