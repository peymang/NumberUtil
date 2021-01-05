/**
 * @author peyman ghafari pour(https://github.com/peymang)
 * version 1.0
 */
class NumberUtil {
    companion object {
        private const val zero = "صفر"
        private const val delimiter = "و"
        private const val negative = "منفی"
        private const val point = "ممیز"

        private const val tag = "number to persian word::"

        // max integer number length is 105
        const val maxInt = 105

        // max decimal number length is 11
        const val maxDec = 11
        private val words = mutableMapOf(
            "0" to "",
            "00" to "",
            "000" to "",
            "1" to "یک",
            "2" to "دو",
            "3" to "سه",
            "4" to "چهار",
            "5" to "پنج",
            "6" to "شش",
            "7" to "هفت",
            "8" to "هشت",
            "9" to "نه",
            "10" to "ده",
            "11" to "یازده",
            "12" to "دوازده",
            "13" to "سیزده",
            "14" to "چهارده",
            "15" to "پانزده",
            "16" to "شانزده",
            "17" to "هفده",
            "18" to "هجده",
            "19" to "نوزده",
            "20" to "بیست",
            "30" to "سی",
            "40" to "چهل",
            "50" to "پنجاه",
            "60" to "شصت",
            "70" to "هفتاد",
            "80" to "هشتاد",
            "90" to "نود",
            "100" to "یکصد",
            "200" to "دویست",
            "300" to "سیصد",
            "400" to "چهارصد",
            "500" to "پانصد",
            "600" to "ششصد",
            "700" to "هفتصد",
            "800" to "هشتصد",
            "900" to "نهصد",
            "0_" to "",
            "1_" to "یکصد",
            "3_" to "هزار",
            "6_" to "میلیون",
            "9_" to "میلیارد",
            "12_" to "بیلیون",
            "15_" to "بیلیارد",
            "18_" to "تریلیون",
            "21_" to "تریلیارد",
            "24_" to "کوآدریلیون",
            "27_" to "کادریلیارد",
            "30_" to "کوینتیلیون",
            "33_" to "کوانتینیارد",
            "36_" to "سکستیلیون",
            "39_" to "سکستیلیارد",
            "42_" to "سپتیلیون",
            "45_" to "سپتیلیارد",
            "48_" to "اکتیلیون",
            "51_" to "اکتیلیارد",
            "54_" to "نانیلیون",
            "57_" to "نانیلیارد",
            "60_" to "دسیلیون",
            "63_" to "دسیلیارد",
            "66_" to "آندسیلیون",
            "69_" to "آندسیلیارد",
            "72_" to "دودسیلیون",
            "75_" to "دودسیلیارد",
            "78_" to "تریدسیلیون",
            "81_" to "تریدسیلیارد",
            "84_" to "کوادردسیلیون",
            "87_" to "کوادردسیلیارد",
            "90_" to "کویندسیلیون",
            "93_" to "کویندسیلیارد",
            "96_" to "سیدسیلیون",
            "99_" to "سیدسیلیارد",
            "102_" to "گوگول",
            "_1" to "دهم",
            "_2" to "صدم",
            "_3" to "هزارم",
            "_4" to "ده هزارم",
            "_5" to "صد هزارم",
            "_6" to "میلیونوم",
            "_7" to "ده میلیونوم",
            "_8" to "صدمیلیونوم",
            "_9" to "میلیاردم",
            "_10" to "ده میلیاردم",
            "_11" to "صد میلیاردم"
        )

        /**
         * get number in string and return the word equivalent to that number in Persian
         * note 0: number must valid if it's not function return wrong result
         *
         * note 1: we clean prefix zero in integer part and suffix zero in decimal part
         * @param number a valid number like 123.123 or -123.123 or 0.123 or 123456789
         * @throws Exception if integer part length is more than [NumberUtil.maxInt]
         * and decimal part length is more than [NumberUtil.maxDec]
         * @return The word equivalent to that number in Persian
         */
        fun toPersianWord(number: String): String {
            if (number.isEmpty() || number.isBlank() || number == "0") return zero

            //get number sign
            val sign: String = if (number.substring(0, 1) == "-") negative else ""
            //store integer part
            val int: String = number.split(".")[0].replace("-", "")
            //store decimal part
            val dec: String = if (number.contains(".")) number.split(".")[1] else "0"

            val intWord = getIntWord(int).trim()
            val decWord = getDecWord(dec).trim()

            return ((if (intWord != zero || dec.isNotEmpty()) "$sign " else "") + "$intWord $decWord").trim()
        }

        /**
         * get number in string format and return the word equivalent to that number in Persian
         * @param number valid number for example 0 or 123456789
         * @throws Exception if number length more than [NumberUtil.maxInt]
         */
        private fun getIntWord(number: String): String {
            if (number.matches("^[0]+$".toRegex())) return zero
            var firstIndexThatNotZero = 0
            var cleanNumber = "0"

            loop@ for (i in number.indices) {
                if (number[i] != '0') {
                    firstIndexThatNotZero = i
                    break@loop
                }
            }

            cleanNumber = number.substring(firstIndexThatNotZero, number.length)
            val intMaxIndex = cleanNumber.length - 3
            val intMaxIndexMod3 = cleanNumber.length % 3
            var intWord = ""

            if (cleanNumber.length > maxInt)
                throw Exception("$tag max support integer length is $maxInt, your integer part has ${cleanNumber.length} length")

            var zeroCount = 0
            for (i in intMaxIndex downTo 0 step 3) {
                if (intWord.isNotEmpty()) intWord = " $delimiter $intWord"
                intWord = get3LenIntWord(zeroCount, cleanNumber.substring(i, i + 3)) + intWord
                zeroCount += 3
            }

            if (intMaxIndexMod3 > 0) {
                if (intWord.isNotEmpty()) intWord = " $delimiter $intWord"
                intWord = get3LenIntWord(zeroCount, cleanNumber.substring(0, intMaxIndexMod3)) + intWord
            }

            return intWord
        }

        /**
         * get number in string format and return the word equivalent to that number in Persian
         * @param number valid number for example 0 or 123456789
         * @throws Exception if number length more than [NumberUtil.maxInt]
         */
        private fun getDecWord(number: String): String {
            if (number.matches("^[0]+$".toRegex())) return ""
            var lastIndexThatNotZero = number.length
            loop@ for (i in number.length - 1 downTo 0)
                if (number[i] != '0') {
                    lastIndexThatNotZero = i
                    break@loop
                }
            val cleanNumber = number.substring(0, lastIndexThatNotZero + 1)
            if (cleanNumber.length > maxDec)
                throw Exception("$tag max support decimal length is $maxDec, your decimal part has ${cleanNumber.length} length")

            val intWord = getIntWord(cleanNumber)
            return if (intWord != zero) "$point $intWord " + words.getValue("_${cleanNumber.length}")
            else ""
        }

        /**
         * get number in string format and return the word equivalent to that number in Persian
         * @param zeroCount it's length of the number.
         * base of this function can determine which word choose form [words] array that key like "digit_"
         * @param number <=3 length number
         */
        private fun get3LenIntWord(zeroCount: Int, number: String): String {
            var n100 = ""
            var n10 = ""
            var n1 = ""
            var suffix = ""
            var result = ""

            when (number.length) {
                3 -> {
                    n100 = words.getValue(number[0] + "00")
                    n10 = words.getValue(if (number[1] == '1') number[1].toString() + number[2] else number[1] + "0")
                    n1 = words.getValue(if (number[1] == '1') "0" else number[2].toString())
                }
                2 -> {
                    n10 = words.getValue(if (number[0] == '1') number[0].toString() + number[1] else number[0] + "0")
                    n1 = words.getValue(if (number[0] == '1') "0" else number[1].toString())
                }
                1 -> {
                    n1 = words.getValue(number[0].toString())
                }
            }

            if (n100.isNotEmpty()) {
                result = n100
                if (n10.isNotEmpty() || n1.isNotEmpty()) result += " $delimiter "
            }

            if (n10.isNotEmpty()) {
                result += n10
                if (n1.isNotEmpty()) result += " $delimiter "
            }

            if (n1.isNotEmpty()) result += n1
            if (n1.isNotEmpty() || n10.isNotEmpty() || n100.isNotEmpty())
                suffix = words.getValue("${zeroCount}_")

            return "$result $suffix".trim()
        }
    }
}
