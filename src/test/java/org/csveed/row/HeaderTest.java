/*
 * CSVeed (https://github.com/42BV/CSVeed)
 *
 * Copyright 2013-2023 CSVeed.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 */
package org.csveed.row;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.csveed.api.Header;
import org.csveed.report.CsvException;
import org.junit.jupiter.api.Test;

public class HeaderTest {

    @Test
    public void getNonExistingColumnName() {
        Header header = new HeaderImpl(createLine("alpha"));
        assertThrows(CsvException.class, () -> {
            header.getIndex("does-not-exist");
        });
    }

    @Test
    public void getNonExistingColumnIndex() {
        Header header = new HeaderImpl(createLine("alpha"));
        assertThrows(CsvException.class, () -> {
            header.getName(13);
        });
    }

    @Test
    public void toLowerCase() {
        Header header = new HeaderImpl(createLine("Alpha"));
        assertEquals("Alpha", header.getName(1));
        assertEquals(1, header.getIndex("Alpha"));
    }

    protected LineWithInfo createLine(String cell) {
        LineWithInfo line = new LineWithInfo();
        line.addCell(cell);
        return line;
    }
}
