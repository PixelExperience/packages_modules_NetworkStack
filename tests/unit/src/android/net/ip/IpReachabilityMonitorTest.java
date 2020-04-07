/*
 * Copyright (C) 2017 The Android Open Source Project
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

package android.net.ip;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.net.INetd;
import android.net.util.InterfaceParams;
import android.net.util.SharedLog;
import android.os.Handler;
import android.os.Looper;

import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests for IpReachabilityMonitor.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class IpReachabilityMonitorTest {
    @Mock IpReachabilityMonitor.Callback mCallback;
    @Mock IpReachabilityMonitor.Dependencies mDependencies;
    @Mock SharedLog mLog;
    @Mock Context mContext;
    @Mock INetd mNetd;
    Handler mHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mLog.forSubComponent(anyString())).thenReturn(mLog);
        mHandler = new Handler(Looper.getMainLooper());
    }

    IpReachabilityMonitor makeMonitor() {
        final InterfaceParams ifParams = new InterfaceParams("fake0", 1, null);
        return new IpReachabilityMonitor(
                mContext, ifParams, mHandler, mLog, mCallback, false, mDependencies, mNetd);
    }

    @Test
    public void testNothing() {
        // make sure the unit test runs in the same thread with main looper.
        // Otherwise, throwing IllegalStateException would cause test fails.
        mHandler.post(() -> makeMonitor());
    }
}