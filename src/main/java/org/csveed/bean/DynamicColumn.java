package org.csveed.bean;

import org.csveed.common.Column;

/**
 * <p>
 *     The concept of dynamic columns comes into play when a sheet contains columns that are not really part
 *     of the columns, but should have been separate rows. For example, let's say you have a table that looks
 *     like this:
 * </p>
 * <code><pre>
 *     | name   | town   | jan 14 | jan 15 | jan 16 |
 *     | Rob    | Leiden |   4    |   1    |   7    |
 *     | Rob    | Delft  |   0    |   3    |   8    |
 *     | Erik   | Leiden |   2    |   4    |   1    |
 *     | Erik   | Sneek  |   1    |   0    |   9    |
 * </pre></code>
 * <p>
 *     Let's say you want to compact this table into the following, normalized format:
 * <code><pre>
 *     | name   | town   | date   | visits |
 *     | Rob    | Leiden | jan 14 |   4    |
 *     | Rob    | Leiden | jan 15 |   1    |
 *     | Rob    | Leiden | jan 16 |   7    |
 *     | Rob    | Delft  | jan 14 |   0    |
 *     | Rob    | Delft  | jan 15 |   3    |
 *     | Rob    | Delft  | jan 16 |   8    |
 *     | Erik   | Leiden | jan 14 |   2    |
 *     | Erik   | Leiden | jan 15 |   4    |
 *     | Erik   | Leiden | jan 16 |   1    |
 *     | Erik   | Sneek  | jan 14 |   1    |
 *     | Erik   | Sneek  | jan 15 |   0    |
 *     | Erik   | Sneek  | jan 16 |   9    |
 * </pre></code>
 * <p>
 *     In order to realize this goal, you need to make that startIndexDynamicColumns is set to 3 on @CsvFile.
 *     This will assume the columns starting with the third and all thereafter are dynamic. For every dynamic
 *     column, a new bean will be created. All static columns will be copied into every created bean.
 * </p>
 * <p>
 *     The header name and the cell value can be copied into bean properties. In the example, the bean requires
 *     two fields date and visits. date must be annotated with @CsvHeaderName and visits with @CsvHeaderName.
 * </p>
 * @author Robert Bor
 */
public class DynamicColumn {

    private Column startColumn;

    private Column currentColumn;

    public DynamicColumn(Column configuredStartColumn) {
        this.startColumn = configuredStartColumn == null ? null : new Column(configuredStartColumn);
        this.currentColumn = configuredStartColumn == null ? null : new Column(configuredStartColumn);
    }

    public void checkForReset(int numberOfColumns) {
        if (lastDynamicColumnPassed(numberOfColumns)) {
            reset();
        }
    }

    protected void reset() {
        this.currentColumn = new Column(this.startColumn);
    }

    protected boolean lastDynamicColumnPassed(int numberOfColumns) {
        return this.currentColumn != null && this.currentColumn.getColumnIndex() > numberOfColumns;
    }

    public boolean atFirstDynamicColumn() {
        return this.startColumn == null || this.startColumn.equals(this.currentColumn);
    }

    public void advanceDynamicColumn() {
        if (currentColumn == null) {
            return;
        }
        this.currentColumn = currentColumn.nextColumn();
    }

    public boolean isDynamicColumnActive(Column currentColumn) {
        return this.currentColumn != null && this.currentColumn.getColumnIndex() == currentColumn.getColumnIndex();
    }

}
