/*
DESCRIPTION:
Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.

The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.

It is much easier to understand with an example:

* For seconds = 62, your function should return
    "1 minute and 2 seconds"
* For seconds = 3662, your function should return
    "1 hour, 1 minute and 2 seconds"

 */

package codewars;

public class TimeFormatter {

    static int SEC = 1;
    static int MIN = 60 * SEC;
    static int HOUR = 60 * MIN;
    static int DAY = 24 * HOUR;
    static int YEAR = 365 * DAY;


    public static void main(String[] args) {
        System.out.println(formatDuration(SEC + 2*MIN + HOUR + DAY + YEAR));
    }

    public static String formatDuration(int seconds) {

        if (seconds == 0)
            return "now";

        int iSec = 0, iMin = 0, iHour = 0, iDay = 0, iYear = 0;

        iSec = seconds % 60;

        iMin = seconds / 60;
        if (iMin >= 60) {
            iHour = iMin / 60;
            iMin = iMin % 60;
        }

        if (iHour >= 24) {
            iDay = iHour / 24;
            iHour = iHour % 24;
        }

        if (iDay >= 365) {
            iYear = iDay / 365;
            iDay = iDay % 365;
        }

        String sOut = "";
        sOut += formatPlural(iYear, "year");
        sOut += formatPlural(iDay, "day");
        sOut += formatPlural(iHour, "hour");
        sOut += formatPlural(iMin, "minute");
        sOut += formatPlural(iSec, "second");

        return cleanText(sOut);
    }

    public static String formatPlural(int i, String sYMD){
        String sOut = "";
        if(i > 0){
            sOut += ", " + i + " " + sYMD;
            if(i > 1){
                sOut += "s";
            }
        }
        return sOut;
    }

    public static String cleanText(String s){
        s = s.replaceFirst("^, ","");

        String sRegexp = ", ([^,]*)$";
        s = s.replaceFirst(sRegexp," and $1");

        return s;
    }
}
