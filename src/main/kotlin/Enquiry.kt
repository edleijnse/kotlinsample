import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.Locale
import java.util.ResourceBundle

/**
 * Hello world! Test Github
 */
class Enquiry {

    @Throws(IOException::class)
    fun getEnquiryFragment(iFragment: String): String {
        var myHeader: String?
        myHeader = ""
        val `in` = this.javaClass.classLoader.getResourceAsStream(iFragment)
        val br = BufferedReader(InputStreamReader(`in`, StandardCharsets.UTF_8))

        try {
            val sb = StringBuilder()
            myHeader = br.readLine()

            while (myHeader != null) {
                sb.append(myHeader)
                sb.append("\n")
                myHeader = br.readLine()
            }
            return sb.toString()
        } catch (e: IOException) {
        } finally {
            br.close()
        }
        return myHeader
    }

    @Throws(IOException::class)
    fun createEnquiry(): String {

        val enquiry = Enquiry()

        // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/i18n/resbundle/examples/PropertiesDemo.java
        val labels = ResourceBundle.getBundle("enquiry", Locale.GERMAN)
        enquiryHeaderLocation = labels.getString("header")

        labelQuestionary = labels.getString("txtquestionary")
        labelQuestionId = labels.getString("txtquestionid")
        labelQuestion = labels.getString("txtquestion")
        labelAnswerId = labels.getString("txtanswerid")
        labelAnswer = labels.getString("txtanswer")


        val myEnquiryPrepare = enquiry.getEnquiryFragment("enquiry_01_prolog.html")
        var myEnquiry = myEnquiryPrepare.replace("#labelQuestionary", labelQuestionary!!)

        val myEnquiryQuestion01Prolog01 = enquiry.getEnquiryFragment("enquiryquestion_01_prolog.html")
        val myEnquiryQuestion01Prolog02 = myEnquiryQuestion01Prolog01.replace("#labelQuestionId", labelQuestionId!!)
        val myEnquiryQuestion01Prolog03 = myEnquiryQuestion01Prolog02.replace("#labelQuestion", labelQuestion!!)
        val myEnquiryQuestion01Prolog04 = myEnquiryQuestion01Prolog03.replace("#labelAnswerId", labelAnswerId!!)
        val myEnquiryQuestion01Prolog05 = myEnquiryQuestion01Prolog04.replace("#labelAnswer", labelAnswer!!)
        myEnquiry += myEnquiryQuestion01Prolog05

        val myQuestion1 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html")
        val myNewstring1_1 = myQuestion1.replace("#questionId".toRegex(), "1")
        val myNewstring1_2 = myNewstring1_1.replace("#questionDescription".toRegex(), "Wieviele Zigaretten rauchen Sie?")
        val myNewstring1_3 = myNewstring1_2.replace("#answerId".toRegex(), "Anzahl:")

        myEnquiry += myNewstring1_3

        val myQuestion2 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html")
        myEnquiry += myQuestion2

        val myQuestion3 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html")
        myEnquiry += myQuestion3
        for (i in 0..99) {
            val myQuestion4 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html")
            val myNewstring = myQuestion4.replace("#questionId".toRegex(), Integer.toString(i + 4))
            val myNewstring2 = myNewstring.replace("#questionDescription".toRegex(), "Frage " + Integer.toString(i + 4))
            myEnquiry += myNewstring2
        }

        myEnquiry += enquiry.getEnquiryFragment("enquiry_03_epilog.html")


        return myEnquiry
    }

    companion object {
        private val testString: String? = null
        private var enquiryHeaderLocation: String? = null
        private var labelQuestionary: String? = null
        private var labelQuestionId: String? = null
        private var labelQuestion: String? = null
        private var labelAnswerId: String? = null
        private var labelAnswer: String? = null

        @JvmStatic
        fun main(args: Array<String>) {

            try {
                println("start")
                val enquiry = Enquiry()
                println(enquiry.createEnquiry())


            } catch (e: Exception) {

                e.printStackTrace()
            }

        }
    }
}
