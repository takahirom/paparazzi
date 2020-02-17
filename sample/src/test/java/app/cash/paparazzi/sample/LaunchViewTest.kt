/*
 * Copyright (C) 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.paparazzi.sample

import LaunchItem
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_5
import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_5_LAND
import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_7
import app.cash.paparazzi.sample.databinding.LaunchBinding
import com.xwray.groupie.databinding.BindableItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.android.fakes.RoboMonitoringInstrumentation

class LaunchViewTest {
  @get:Rule
  var paparazzi = Paparazzi(deviceConfig = NEXUS_7)

  @Before
  fun setup() {
    val roboMonitoringInstrumentation = RoboMonitoringInstrumentation()
    InstrumentationRegistry.registerInstance(roboMonitoringInstrumentation, Bundle.EMPTY)
  }

  @Test
  fun nexus7() {
    LaunchItem("item text!!!!").snapshot()
  }

  private fun<T: ViewDataBinding> BindableItem<T>.snapshot() {
    val launch = paparazzi.inflate<View>(layout)
    val bind = DataBindingUtil.bind<T>(launch) ?: error("")
    bind(bind, 0)
    paparazzi.snapshot(launch, "launch nexus7")
  }

  @Test
  fun nexus5_differentOrientations() {
    val launch = paparazzi.inflate<LinearLayout>(R.layout.launch)
    paparazzi.snapshot(launch, "launch nexus 5 portrait", deviceConfig = NEXUS_5)
    paparazzi.snapshot(launch, "launch nexus 5 landscape", deviceConfig = NEXUS_5_LAND)
  }

  @Test
  fun nexus7_differentThemes() {
    val launch = paparazzi.inflate<LinearLayout>(R.layout.launch)
    paparazzi.snapshot(
        view = launch,
        name = "launch nexus 7 light",
        theme = "android:Theme.Material.Light"
    )
    paparazzi.snapshot(
        view = launch,
        name = "launch nexus 7 light no-action-bar",
        theme = "android:Theme.Material.Light.NoActionBar"
    )
  }
}
