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

import static java.lang.String.format;
import static java.time.MonthDay.of;
import static org.bson.types.Decimal128.parse;

import java.math.BigDecimal;
import java.time.MonthDay;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * <p>
 * Encodes and decodes {@code MonthDay} values to and from
 * {@code BSON Decimal128}, such as {@code 1.18}.
 * <p>
 * Values are stored in {@code %d.%02d} format, where the first part represents
 * a month and the latter a day of this month.
 * <p>
 * This type is <b>immutable</b>.
 */
public final class MonthDayCodec
        implements Codec<MonthDay> {

    @Override
    public void encode(
            BsonWriter writer,
            MonthDay value,
            EncoderContext encoderContext) {

        writer.writeDecimal128(parse(format(
                "%d.%02d",
                value.getMonthValue(),
                value.getDayOfMonth()
        )));
    }

    @Override
    public MonthDay decode(
            BsonReader reader,
            DecoderContext decoderContext) {

        BigDecimal value = reader
                .readDecimal128()
                .bigDecimalValue();
        int month = value.intValue();
        return of(
                month,
                value.subtract(new BigDecimal(month))
                     .scaleByPowerOfTen(2)
                     .intValue()
        );
    }

    @Override
    public Class<MonthDay> getEncoderClass() {
        return MonthDay.class;
    }
}
