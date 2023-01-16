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
package org.csveed.common;

public class ColumnExcel extends Column {

    public ColumnExcel(String columnExcel) {
        super(excelColumnToColumnIndex(columnExcel));
    }

    private static int excelColumnToColumnIndex(String excelColumn) {
        excelColumn = excelColumn.toUpperCase();
        int sum = 0;
        for (char currentChar : excelColumn.toCharArray()) {
            sum *= 26;
            sum += currentChar - 'A' + 1;
        }
        return sum;
    }

}
