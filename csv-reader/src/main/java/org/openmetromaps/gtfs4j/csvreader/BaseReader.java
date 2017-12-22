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
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.openmetromaps.gtfs4j.csv.Field;

public abstract class BaseReader<S, T extends Enum<T> & Field>
{

	private Class<T> clazz;
	protected Map<T, Integer> idx;
	protected List<T> fields;

	public BaseReader(Class<T> clazz)
	{
		this.clazz = clazz;
		idx = new EnumMap<>(clazz);
		fields = new ArrayList<>();
	}

	protected boolean hasField(T field)
	{
		return idx.get(field) >= 0;
	}

	public void initIndexes(String[] head)
	{
		for (int i = 0; i < head.length; i++) {
			fields.add(null);
		}
		for (T field : clazz.getEnumConstants()) {
			int index = Util.getIndex(head, field.getCsvName());
			idx.put(field, index);
			if (index >= 0) {
				fields.set(index, field);
			}
		}
	}

	/**
	 * Only use this after {@link #initIndexes(String[])} has been called.
	 */
	public List<T> getFields()
	{
		return fields;
	}

	public abstract List<S> readAll() throws IOException;

}
