/*
 * Copyright 2018 Cezary Bartosiak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.cbartosiak.bson.codecs.jsr310.internal;

import static java.lang.String.format;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

public final class DocumentCodecsUtil {

    private static final DocumentCodec DOCUMENT_CODEC = new DocumentCodec();

    private DocumentCodecsUtil() {}

    public static void writeDocument(
            BsonWriter writer,
            Document document,
            EncoderContext encoderContext) {

        DOCUMENT_CODEC.encode(writer, document, encoderContext);
    }

    public static Document readDocument(
            BsonReader reader,
            DecoderContext decoderContext) {

        return DOCUMENT_CODEC.decode(reader, decoderContext);
    }

    public static <Value> Value getFieldValue(
            Document document,
            Object key,
            Class<Value> clazz) {

        try {
            Value value = document.get(key, clazz);
            if (value == null) {
                throw new BsonInvalidOperationException(format(
                        "The value of %s is null", key
                ));
            }
            return value;
        }
        catch (ClassCastException ex) {
            throw new BsonInvalidOperationException(format(
                    "The value of %s is not of the type %s",
                    key, clazz.getName()
            ), ex);
        }
    }
}