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

import java.util.EnumMap;
import java.util.Map;

import org.openmetromaps.gtfs4j.csv.Field;

import au.com.bytecode.opencsv.CSVWriter;

public abstract class BaseWriter<S, T extends Enum<T> & Field>
{

	protected Map<T, Integer> idx;
	private Class<T> clazz;

	protected CSVWriter csvWriter;

	public BaseWriter(Class<T> clazz)
	{
		this.clazz = clazz;
		idx = new EnumMap<>(clazz);
	}

	protected boolean hasField(T field)
	{
		return idx.get(field) >= 0;
	}

	public abstract void write(S object);

}
