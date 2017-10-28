/*
 * MIT License
 *
 * Copyright (c) 2017 Cezary Bartosiak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.cbartosiak.bson.codecs.jsr310;

import java.util.HashMap;
import java.util.Map;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * Provides codecs for JSR-310 classes:
 * <ul>
 * <li>{@link DurationCodec}</li>
 * <li>{@link InstantCodec}</li>
 * <li>{@link LocalDateCodec}</li>
 * <li>{@link LocalDateTimeCodec}</li>
 * <li>{@link LocalTimeCodec}</li>
 * <li>{@link MonthDayCodec}</li>
 * <li>{@link OffsetDateTimeCodec}</li>
 * <li>{@link OffsetTimeCodec}</li>
 * <li>{@link PeriodCodec}</li>
 * <li>{@link YearCodec}</li>
 * <li>{@link YearMonthCodec}</li>
 * <li>{@link ZonedDateTimeCodec}</li>
 * <li>{@link ZoneOffsetCodec}</li>
 * </ul>
 * <p>
 * The implementation is <b>thread-safe</b>.
 * </p>
 */
public final class Jsr310CodecProvider
        implements CodecProvider {

    private final Map<Class<?>, Codec<?>> codecs = new HashMap<>();

    /**
     * Creates a JSR-310 codec provider.
     */
    @SuppressWarnings("OverlyCoupledMethod")
    public Jsr310CodecProvider() {
        putCodec(new DurationCodec());
        putCodec(new InstantCodec());
        putCodec(new LocalDateCodec());
        putCodec(new LocalDateTimeCodec());
        putCodec(new LocalTimeCodec());
        putCodec(new MonthDayCodec());
        putCodec(new OffsetDateTimeCodec());
        putCodec(new OffsetTimeCodec());
        putCodec(new PeriodCodec());
        putCodec(new YearCodec());
        putCodec(new YearMonthCodec());
        putCodec(new ZonedDateTimeCodec());
        putCodec(new ZoneOffsetCodec());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return (Codec<T>)codecs.get(clazz);
    }

    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    private <T> void putCodec(Codec<T> codec) {
        codecs.put(codec.getEncoderClass(), codec);
    }
}