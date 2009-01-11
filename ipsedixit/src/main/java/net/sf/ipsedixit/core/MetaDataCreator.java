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

package net.sf.ipsedixit.core;

/**
 * Gets the metadata information from a MutableField.  The type of MetaData depends on the type of Field.
 * @param <T> the type of MetaData to return.
 */
public interface MetaDataCreator<T extends FieldMetaData> {

    /**
     * @param mutableField the MutableField to get metadata from.
     * @return the MetaData from the field.
     */
    T getMetaData(MutableField mutableField);

}
