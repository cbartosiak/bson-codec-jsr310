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

package io.github.cbartosiak.bson.codecs.jsr310.offsetdatetime;

import static java.time.OffsetDateTime.MAX;
import static java.time.OffsetDateTime.MIN;
import static java.time.OffsetDateTime.now;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.OffsetDateTime;

import org.bson.codecs.Codec;
import org.junit.jupiter.api.Test;

import io.github.cbartosiak.bson.codecs.jsr310.internal.AbstractCodecsTests;
import io.github.cbartosiak.bson.codecs.jsr310.localdatetime.LocalDateTimeAsDocumentCodec;
import io.github.cbartosiak.bson.codecs.jsr310.zoneoffset.ZoneOffsetAsInt32Codec;
import io.github.cbartosiak.bson.codecs.jsr310.zoneoffset.ZoneOffsetAsStringCodec;

@SuppressWarnings("JUnitTestMethodWithNoAssertions")
final class OffsetDateTimeCodecsTests extends AbstractCodecsTests {

    private OffsetDateTimeCodecsTests() {}

    private static void testOffsetDateTimeCodec(Codec<OffsetDateTime> codec) {
        assertThrows(
                NullPointerException.class,
                () -> testCodec(codec, null)
        );
        testCodec(codec, MIN);
        testCodec(codec, MAX);
        testCodec(codec, now());
    }

    @Test
    void testOffsetDateTimeAsStringCodec() {
        testOffsetDateTimeCodec(new OffsetDateTimeAsStringCodec());
    }

    @Test
    void testOffsetDateTimeAsDocumentCodec() {
        testOffsetDateTimeCodec(new OffsetDateTimeAsDocumentCodec());
        assertThrows(
                NullPointerException.class,
                () -> new OffsetDateTimeAsDocumentCodec(
                        null, new ZoneOffsetAsStringCodec()
                )
        );
        assertThrows(
                NullPointerException.class,
                () -> new OffsetDateTimeAsDocumentCodec(
                        new LocalDateTimeAsDocumentCodec(), null
                )
        );
        testOffsetDateTimeCodec(new OffsetDateTimeAsDocumentCodec(
                new LocalDateTimeAsDocumentCodec(),
                new ZoneOffsetAsStringCodec()
        ));
        testOffsetDateTimeCodec(new OffsetDateTimeAsDocumentCodec(
                new LocalDateTimeAsDocumentCodec(),
                new ZoneOffsetAsInt32Codec()
        ));
    }
}
