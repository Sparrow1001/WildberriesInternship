package com.example.additionaltask

class BogoSort() {

    companion object {
        const val RAND_MAX = 32768
    }

    val rand = java.util.Random()

    private fun isSorted(a: IntArray): Boolean {
        val n = a.size
        if (n < 2) return true
        for (i in 1 until n) {
            if (a[i] < a[i - 1]) return false
        }
        return true
    }

    private fun shuffle(a: IntArray) {
        val n = a.size
        if (n < 2) return
        for (i in 0 until n) {
            val t = a[i]
            val r = rand.nextInt(Companion.RAND_MAX) % n
            a[i] = a[r]
            a[r] = t
        }
    }

    fun bogosort(a: IntArray) {
        while (!isSorted(a)) shuffle(a)
    }

}