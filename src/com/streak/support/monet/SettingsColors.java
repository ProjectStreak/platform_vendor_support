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

public class SettingsColors {

    ColorScheme colorscheme;

    public SettingsColors(int color) {
        colorscheme = new ColorScheme(color, false);
    }

    public int mainBG(){
        return colorscheme.getColor(4, 1);
    }

    public int secBG(){
        return colorscheme.getColor(4, 3);
    }

    public int accentCol(){
        return colorscheme.getColor(1, 7);
    }

    public int secAccentCol(){
        return colorscheme.getColor(2, 7);
    }

    public int tertAccentCol(){
        return colorscheme.getColor(3, 7);
    }

}
