package com.streak.support.monet

public object MathUtils {
    public fun lerp(start: Double, stop: Double, amount: Double): Double {
        return (stop - start) * amount + start
    }
}
