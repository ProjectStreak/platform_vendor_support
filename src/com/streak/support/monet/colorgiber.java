/*
 * Copyright (C) 2018 The Android public Source Project
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

package com.streak.support.monet;

import com.streak.support.monet.ColorScheme;
import android.annotation.NonNull;

public class colorgiber {

    int defaultColor = 0x000000;
    private ColorScheme colorscheme;

    public colorgiber(@NonNull int color) {
        colorscheme = new ColorScheme(color, false);
    }

    public int getMonColor(int whichp, int whichc){
        int k = colorscheme.getColor(whichp, whichc);
        return k;
    }
}
