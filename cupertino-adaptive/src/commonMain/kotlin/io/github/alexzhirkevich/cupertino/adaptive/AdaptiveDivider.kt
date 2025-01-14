/*
 * Copyright (c) 2024. Compose Cupertino project and open source contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.github.alexzhirkevich.cupertino.adaptive

import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.github.alexzhirkevich.cupertino.CupertinoDivider
import io.github.alexzhirkevich.cupertino.CupertinoDividerDefaults

@Composable
@ExperimentalAdaptiveApi
fun AdaptiveDivider(
    modifier: Modifier = Modifier,
    adaptation: AdaptationScope<DividerAdaptation,DividerAdaptation>.() -> Unit = {}
) {
    AdaptiveWidget(
        adaptation = remember {
            DividerAdaptationScope()
        },
        adaptationScope = adaptation,
        material = {
            Divider(
                modifier = modifier,
                thickness = it.thickness,
                color = it.color
            )
        },
        cupertino = {
            CupertinoDivider(
                modifier = modifier,
                thickness = it.thickness,
                color = it.color
            )
        }
    )
}

private class DividerAdaptationScope : Adaptation<DividerAdaptation, DividerAdaptation>() {

    @Composable
    override fun rememberCupertinoAdaptation(): DividerAdaptation {

        val color = CupertinoDividerDefaults.color
        return remember(color) {
            DividerAdaptation(
                color = color,
                thickness = CupertinoDividerDefaults.Thickness
            )
        }
    }

    @Composable
    override fun rememberMaterialAdaptation(): DividerAdaptation {
        val color = DividerDefaults.color

        return remember(color) {
            DividerAdaptation(
                color = color,
                thickness = DividerDefaults.Thickness
            )
        }
    }
}

class DividerAdaptation(
    var color: Color,
    var thickness: Dp
)
