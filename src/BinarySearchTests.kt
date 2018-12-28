import kotlincommon.test.shouldEqual
import org.junit.Test

class BinarySearchTests {
    @Test fun `find index of an element in a sorted list`() {
        emptyList<Int>().findIndexOf(0) shouldEqual -1

        listOf(1).findIndexOf(0) shouldEqual -1
        listOf(1).findIndexOf(1) shouldEqual 0
        listOf(1).findIndexOf(2) shouldEqual -1

        listOf(1, 2, 3).findIndexOf(0) shouldEqual -1
        listOf(1, 2, 3).findIndexOf(1) shouldEqual 0
        listOf(1, 2, 3).findIndexOf(2) shouldEqual 1
        listOf(1, 2, 3).findIndexOf(3) shouldEqual 2
        listOf(1, 2, 3).findIndexOf(4) shouldEqual -1
    }

    @Test fun `find index of duplicate elements`() {
        listOf(1, 1, 2, 3).findIndexOf(1) shouldEqual 1
        listOf(1, 2, 3, 3).findIndexOf(3) shouldEqual 2
    }

    @Test fun `can search huge list`() {
        val hugeList = object: AbstractList<Int>() {
            override val size = Int.MAX_VALUE
            override fun get(index: Int) = index
        }
        hugeList.findIndexOf(0) shouldEqual 0
        hugeList.findIndexOf(Int.MAX_VALUE - 1) shouldEqual Int.MAX_VALUE - 1
    }
}

private fun <E: Comparable<E>> List<E>.findIndexOf(element: E): Int {
    var from = 0
    var to = size
    while (from < to) {
        val midIndex = (from + to).ushr(1)
        val compareTo = element.compareTo(this[midIndex])
        when {
            compareTo < 0 -> to = midIndex
            compareTo > 0 -> from = midIndex + 1
            else          -> return midIndex
        }
    }
    return -1
}

