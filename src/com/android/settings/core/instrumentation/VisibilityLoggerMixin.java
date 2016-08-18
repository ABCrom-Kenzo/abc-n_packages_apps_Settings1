/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.core.instrumentation;

import android.content.Context;

/**
 * Logs visibility change of a fragment.
 */
public class VisibilityLoggerMixin {

    private final Instrumentable mInstrumentable;
    private final EventLogWriter mEventLogWriter;

    public VisibilityLoggerMixin(Instrumentable instrumentable) {
        this(instrumentable, MetricsFactory.get().getLogger());
    }

    public VisibilityLoggerMixin(Instrumentable instrumentable, EventLogWriter eventLogWriter) {
        mInstrumentable = instrumentable;
        mEventLogWriter = eventLogWriter;
    }

    public void onResume(Context context) {
        mEventLogWriter.visible(context, mInstrumentable.getMetricsCategory());
    }

    public void onPause(Context context) {
        mEventLogWriter.hidden(context, mInstrumentable.getMetricsCategory());
    }
}