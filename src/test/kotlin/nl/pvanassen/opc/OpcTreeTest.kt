package nl.pvanassen.opc

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.Test

internal class OpcTreeTest {

    @Test
    fun testMapOneStrip() {
        val tree = OpcTree(mapOf(Pair(0, 10)))
        assert(tree).isNotNull()
        assert(tree.getPixelNumber(0, 5)).isEqualTo(5)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testOutOfBoundsOneStrip() {
        val tree = OpcTree(mapOf(Pair(0, 10)))
        assert(tree).isNotNull()
        tree.getPixelNumber(0, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testOutOfBoundsMultipleStripsSameSize() {
        val tree = OpcTree(mapOf(Pair(0, 10), Pair(1, 10)))
        assert(tree).isNotNull()
        assert(tree.getPixelNumber(0, 9)).isEqualTo(9)
        assert(tree.getPixelNumber(1, 0)).isEqualTo(10)
        assert(tree.getPixelNumber(1, 9)).isEqualTo(19)
        tree.getPixelNumber(1, 10)
    }

    @Test
    fun testMapMultipleStripsSameSize() {
        val tree = OpcTree(mapOf(Pair(0, 10), Pair(1, 10), Pair(2, 10)))
        assert(tree).isNotNull()
        assert(tree.getPixelNumber(0, 5)).isEqualTo(5)
        assert(tree.getPixelNumber(1, 5)).isEqualTo(15)
        assert(tree.getPixelNumber(2, 5)).isEqualTo(25)
    }

    @Test
    fun testMapMultipleStripsDifferentSizes() {
        val tree = OpcTree(mapOf(Pair(0, 15), Pair(1, 20), Pair(2, 3)))
        assert(tree).isNotNull()
        assert(tree.getPixelNumber(0, 5)).isEqualTo(5)
        assert(tree.getPixelNumber(1, 1)).isEqualTo(16)
        assert(tree.getPixelNumber(2, 2)).isEqualTo(37)
    }
}