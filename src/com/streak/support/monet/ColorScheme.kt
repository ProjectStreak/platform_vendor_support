package com.streak.support.monet

import com.streak.support.monet.Cam
import android.app.WallpaperColors
import android.graphics.Color
import com.streak.support.monet.CamUtils.lstarFromInt
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

public class ColorScheme(rgb: Int, public val darkTheme: Boolean = false) {
    val hue: Double = Cam.fromInt(rgb).hue
    val accent1 = Shades.of(hue, 48.0)
    val accent2 = Shades.of(hue, 16.0)
    val accent3 = Shades.of(hue + 60.0, 32.0)
    val neutral1 = Shades.of(hue, 4.0)
    val neutral2 = Shades.of(hue, 8.0)

    public fun getColor(va: Int, vc: Int): Int{
        if (va==2){
            return accent2[vc]
        }
        if (va==3){
            return accent3[vc]
        }
        if (va==4){
            return neutral1[vc]
        }
        if (va==5){
            return neutral2[vc]
        }
        if (va==1){
            return accent1[vc]
        } else{
            return accent1[vc]
        }
    }

    companion object {
        @JvmStatic
        fun getSeedColor(wallpaperColors: WallpaperColors): Int {
            return getSeedColors(wallpaperColors).first()
        }

        @JvmStatic
        fun getSeedColors(wallpaperColors: WallpaperColors): List<Int> {
            val intValue2 = wallpaperColors.allColors.values.reduce { a, b -> a + b }.toDouble()
            val z2 = (intValue2 == 0.0)
            if (z2) {
                val list2 = wallpaperColors.mainColors.map {
                    it.toArgb()
                }.distinct().filter {
                    Cam.fromInt(it).chroma >= 15.0f && lstarFromInt(it) >= 10.0f
                }.toList()

                if (list2.isEmpty()) {
                    return listOf(-14979341)
                }
                return list2
            }

            val linkedHashMap = wallpaperColors.allColors.mapValues { it.value.toDouble() / intValue2 }

            val linkedHashMap2 = wallpaperColors.allColors.mapValues { Cam.fromInt(it.key) }

            val huePopulation = huePopulations(linkedHashMap2, linkedHashMap)

            val linkedHashMap3 = wallpaperColors.allColors.mapValues {
                val cam = linkedHashMap2[it.key]!!
                val i = cam.hue.roundToInt()
                val i2 = i - 15
                val i3 = i + 15
                var d = 0.0
                for (a in i2..i3) {
                    d += huePopulation[wrapDegrees(a)]
                }
                d
            }

            val linkedHashMap4 = linkedHashMap2.filter {
                val key4 = it.value
                val lstarFromInt = lstarFromInt(it.key)
                val d2 = linkedHashMap3[it.key]!!
                key4.chroma >= 15.0f && lstarFromInt >= 10.0f && (z2 || d2 > 0.01)
            }

            val arrayList3 = mutableListOf<Int>()
            val linkedHashMap5 = linkedHashMap4.mapValues {
                score(it.value, linkedHashMap3[it.key]!!)
            }

            val list3 = linkedHashMap5.entries.toMutableList()
            list3.sortByDescending { it.value }

            for (entry6 in list3) {
               val num2 = entry6.key
               val z = arrayList3.find {
                    val hue1 = linkedHashMap2[num2]!!.hue
                    val hue2 = linkedHashMap2[it]!!.hue
                    hueDiff(hue1, hue2) < 15 } != null
                if (z) {
                    continue
                }
                arrayList3.add(num2)
            }

            if (arrayList3.isEmpty()) {
                arrayList3.add(-14979341)
            }

            return arrayList3;
       }

        private fun wrapDegrees(i: Int): Int {
            if (i < 0) {
                return (i % 360) + 360
            }
            return if (i >= 360) i % 360 else i
        }

        private fun hueDiff(d: Double, d2: Double): Double {
            return 180.toDouble() - ((d - d2).absoluteValue - 180.toDouble()).absoluteValue
        }

        private fun humanReadable(list: List<Int>): String {
            return list.joinToString { "#" + Integer.toHexString(it) }
        }

        private fun score(cam: Cam, d: Double): Double {
            val f = cam.getChroma()
            val d2 = if (f < 48.0) 0.1 else 0.3
            val d3 = d * 70.0
            return ((f - 48.0) * d2) + d3
        }

        private fun huePopulations(map: Map<Int, Cam>, map2: Map<Int, Double>): List<Double> {
            val arrayList = List(size = 360, init = { 0.0 }).toMutableList()
            for (entry in map2.entries) {
                val d = map2[entry.key]!!
                val cam = map[entry.key]!!
                val i2 = cam.hue.roundToInt() % 360
                arrayList[i2] = arrayList[i2] + d
            }
            return arrayList
        }
    }
}
