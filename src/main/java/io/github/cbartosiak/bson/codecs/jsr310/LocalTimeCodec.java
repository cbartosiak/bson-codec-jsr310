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

package io.github.cbartosiak.bson.codecs.jsr310;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDate.ofEpochDay;
import static java.time.ZoneOffset.UTC;

import java.time.LocalTime;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * <p>
 * Encodes and decodes {@code LocalTime} objects to and from {@code Date}.
 * <p>
 * The implementation is <b>thread-safe</b>.
 *
 * @see <a href="https://docs.mongodb.com/manual/reference/bson-types/">
 * BSON Types</a>
 */
public final class LocalTimeCodec
        implements Codec<LocalTime> {

    @Override
    public void encode(
            BsonWriter writer,
            LocalTime value,
            EncoderContext encoderContext) {

        writer.writeDateTime(
                value.atDate(ofEpochDay(0L))
                     .toInstant(UTC)
                     .toEpochMilli()
        );
    }

    @Override
    public LocalTime decode(
            BsonReader reader,
            DecoderContext decoderContext) {

        return ofEpochMilli(reader.readDateTime())
                .atOffset(UTC)
                .toLocalTime();
    }

    @Override
    public Class<LocalTime> getEncoderClass() {
        return LocalTime.class;
    }
}
