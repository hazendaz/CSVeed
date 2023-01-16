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
package org.csveed.bean.conversion;

public class EnumConverter<T extends Enum> extends AbstractConverter<T> {

    public Class<T> enumClass;

    public EnumConverter(Class<T> enumClass) {
        super(enumClass);
        this.enumClass = enumClass;
    }

    @Override
    public T fromString(String text) throws Exception {
        return (T) Enum.valueOf(this.enumClass, text);
    }

    @Override
    public String toString(T value) throws Exception {
        return value.toString();
    }

}
