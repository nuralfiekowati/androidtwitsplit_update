package com.tweeter.alfi.twitsplit

import kotlin.math.ceil

class SplitMessageTwit : SplitMessageTwitI {

    /**
     * A function to split message with the size of n characters.
     */

    fun String.splitMessage(size: Int): List<String> {

        /**
         * We include 4 in the computation nSplitsInt below to get n character to split,
         * since 4 is our assumption for the number of characters of the part indicator.
         * E.g. 1/4 is a part indicator counted as 3 number of characters. Added by a space following, it should be counted as 4.
         */

        val nSplitsInt = ceil(length.toDouble() / (size.toDouble() - 4.toDouble())).toInt()
        println("The length of the string: " + length)
        println("The size of the string to split: " + size)
        println("The number of split for the string: " + nSplitsInt)

        var indexOfLastSpace: Int = 0
        var substrAfterLastSpaceLength = 0
        var charBuffer: String = ""
        var startOfEachSplit: Int = 0

        val listSplitString  = (0 until nSplitsInt).map { val partIndicator = """${(it + 1).toString()}/$nSplitsInt """

            var partIndicatorLength = partIndicator.length
            println("startOfEachSplit: " + startOfEachSplit)
            val endOfEachSplit = (((it + 1) * size) - ((it + 1) * partIndicatorLength))
            println("endOfEachSplit: " + endOfEachSplit)
            var substringToSplit = substring(startOfEachSplit, minOf(length, endOfEachSplit))
            println("substringToSplit: " + substringToSplit)
            val beforeLastSpace = substringToSplit.substringBeforeLast(" ")
            val afterLastSpace = substringToSplit.substringAfterLast(" ")

            charBuffer = charBuffer + beforeLastSpace + " "
            println("charNumber: " + charBuffer)

            indexOfLastSpace = charBuffer.length
            println("indexOfLastSpace: " + indexOfLastSpace)

            startOfEachSplit = indexOfLastSpace

            substrAfterLastSpaceLength = afterLastSpace.length
            println("substrAfterLastSpaceLength: " + substrAfterLastSpaceLength)

            val errorMessage = "ERROR!!Non-whitespace!"

            if (substringToSplit.contains("\\s".toRegex())) {

                if (it + 1 != nSplitsInt) {
                    val eachOfString: String = partIndicator + beforeLastSpace
                    eachOfString
                } else {
                    val eachOfString: String = partIndicator + substringToSplit
                    eachOfString
                }

            }
            else {

                if (it + 1 != nSplitsInt) {
                    errorMessage
                } else {
                    val eachOfString: String = partIndicator + substringToSplit
                    eachOfString
                }

            }

        }

        return listSplitString
    }

    /**
     * Calling the function to split above.
     * The function below requires two arguments as the input: the string to split and the size of n characters to split.
     */

    override fun doSplit(postStr: String, size: Int): List<String> {
        val splitPost = postStr.splitMessage(size)
        return splitPost
    }

}

interface SplitMessageTwitI{
    fun doSplit(postStr: String, size: Int): List<String>
}