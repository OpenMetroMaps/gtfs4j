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

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.openmetromaps.gtfs4j.csv.Field;

import au.com.bytecode.opencsv.CSVWriter;

public abstract class BaseWriter<S, T extends Enum<T> & Field>
		implements Closeable
{

	protected Class<T> clazz;
	protected List<T> fields;

	protected CSVWriter csvWriter;

	protected String[] values;

	public BaseWriter(Writer writer, Class<T> clazz, List<T> fields)
	{
		this.clazz = clazz;
		this.fields = fields;
		csvWriter = new CSVWriter(writer);
		values = new String[fields.size()];
	}

	public abstract String get(S object, T field);

	public void write(S object)
	{
		writeDefault(object);
	}

	public void writeDefault(S object)
	{
		for (int i = 0; i < fields.size(); i++) {
			values[i] = get(object, fields.get(i));
		}
		csvWriter.writeNext(values);
	}

	@Override
	public void close() throws IOException
	{
		csvWriter.close();
	}

}
