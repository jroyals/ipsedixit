/*
 *  Copyright 2008 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.sf.ipsedixit.core.impl;

import net.sf.ipsedixit.core.FieldHandler;
import net.sf.ipsedixit.core.NumberMetaData;
import net.sf.ipsedixit.core.MetaDataCreator;
import net.sf.ipsedixit.core.RandomDataProvider;
import net.sf.ipsedixit.core.MutableField;

public class ByteFieldHandler implements FieldHandler {
    private final MetaDataCreator<NumberMetaData> metaDataCreator;
    private final RandomDataProvider randomDataProvider;

    public ByteFieldHandler(MetaDataCreator<NumberMetaData> metaDataCreator, RandomDataProvider randomDataProvider) {
        this.metaDataCreator = metaDataCreator;
        this.randomDataProvider = randomDataProvider;
    }

    public Byte getValueFor(MutableField mutableField) {
        NumberMetaData numberMetaData = metaDataCreator.getMetaData(mutableField);
        return (byte) randomDataProvider.randomLongInRange(
                (byte) numberMetaData.getMinValue(), (byte) numberMetaData.getMaxValue());
    }

    public boolean supports(MutableField mutableField) {
        Class type = mutableField.getType();
        return byte.class.equals(type) || Byte.class.equals(type);
    }
}
