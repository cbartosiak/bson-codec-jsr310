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

package io.github.cbartosiak.bson.codecs.jsr310.month;

import static io.github.cbartosiak.bson.codecs.jsr310.internal.CodecsUtil.translateDecodeExceptions;

import java.time.Month;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * <p>
 * Encodes and decodes {@code Month} values to and from
 * {@code BSON String}, such as
 * {@code JANUARY}.
 * <p>
 * The values are stored as enum constant names
 * (see {@link Month#name()}).
 * <p>
 * This type is <b>immutable</b>.
 */
public final class MonthAsStringCodec
        implements Codec<Month> {

    @Override
    public void encode(
            BsonWriter writer,
            Month value,
            EncoderContext encoderContext) {

        writer.writeString(value.name());
    }

    @Override
    public Month decode(
            BsonReader reader,
            DecoderContext decoderContext) {

        return translateDecodeExceptions(
                reader::readString,
                Month::valueOf
        );
    }

    @Override
    public Class<Month> getEncoderClass() {
        return Month.class;
    }
}
