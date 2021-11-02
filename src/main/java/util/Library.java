package util;

import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {

    public static boolean isDigits(String str) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isOperationOrNaturalConstants(String str) {
        String regex = "[-+*\\/().^%!e=]|(sqrt)|(pi)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String decimalFormat(String n) {
        if (n.contains(".")) {
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            Double d = Double.parseDouble(n);
            return df.format(d);
        }
        return n;
    }

    public static String getConfigData(String path) throws IOException {
        File file=new File("src/test/resources/config.json");
        return JsonPath.parse(file).read(path);
    }

}
