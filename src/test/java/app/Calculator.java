package app;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import manager.DriverManager;
import org.openqa.selenium.ScreenOrientation;
import util.Library;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculator {
    private boolean isAdvanceTabOpen = false;

    //locator
    private static final String resultField = "android.widget.TextView";
    private static final String resultFieldFinal = "com.google.android.calculator:id/result_final";
    private static final String resultFieldPreview = "com.google.android.calculator:id/result_preview";
    private static final String digits = "com.google.android.calculator:id/digit_";
    private static final String btn_clear = "com.google.android.calculator:id/clr";
    private static final String btn_del = "com.google.android.calculator:id/del";
    private static final String advance_pad = "com.google.android.calculator:id/pad_advanced";
    private static final String collapse_arrow = "com.google.android.calculator:id/arrow";

    //normal pad
    public static final Map<String, String> normalPadLocatorTable = new LinkedHashMap<String, String>() {{
        put(".", "com.google.android.calculator:id/dec_point");
        put("=", "com.google.android.calculator:id/eq");
        put("-", "com.google.android.calculator:id/op_div");
        put("+", "com.google.android.calculator:id/op_add");
        put("*", "com.google.android.calculator:id/op_mul");
        put("x", "com.google.android.calculator:id/op_mul");
        put("/", "com.google.android.calculator:id/op_div");
    }};

    //advance pad locator
    public static final Map<String, String> advancePadLocatorTable = new LinkedHashMap<String, String>() {{
        put("sin", "com.google.android.calculator:id/fun_sin");
        put("ln", "com.google.android.calculator:id/fun_ln");
        put("pi", "com.google.android.calculator:id/const_pi");
        put("(", "com.google.android.calculator:id/lparen");
        put("cos", "com.google.android.calculator:id/fun_cos");
        put("log", "com.google.android.calculator:id/fun_log");
        put("e", "com.google.android.calculator:id/const_e");
        put(")", "com.google.android.calculator:id/rparen");
        put("%", "com.google.android.calculator:id/op_pct");
        put("tan", "com.google.android.calculator:id/fun_tan");
        put("sqrt", "com.google.android.calculator:id/op_sqrt");
        put("^", "com.google.android.calculator:id/op_pow");
        put("!", "com.google.android.calculator:id/op_fact");
        put("INV", "com.google.android.calculator:id/toggle_inv");
        put("DEG", "com.google.android.calculator:id/toggle_mode");
    }};

    //type value into field
    public void typeInputField(String value) {
        String[] result = value.split("(?<=[-+*/().^%!e=])|(?=[-+*/().^%!e=])");
        this.clearResult();
        this.setRadian(value);

        for (int i = 0; i < result.length; i++) {
            if (Library.isDigits(result[i]))
                this.enterDigits(result[i], digits);
            else if (Library.isOperationOrNaturalConstants(result[i]))
                this.enterOperation(result[i]);
            else {
                this.enterValues(result[i]);
                i++;
            }
        }
    }

    //click button
    public void clickBtn(String btn) {
        DriverManager.getDriver().findElementById(normalPadLocatorTable
                .get(btn.replace("[", "").replace("]", ""))).click();
    }

    //fetch result field
    public String getResultField() {
        return Library.decimalFormat(DriverManager.getDriver().findElementByClassName(resultField)
                .getText());
    }

    //fetch error messages
    public String getErrorMessage() {
        if (DriverManager.getDriver().getOrientation().equals(ScreenOrientation.LANDSCAPE))
            return DriverManager.getDriver().findElementById(resultFieldFinal).getText();
        else
            return DriverManager.getDriver().findElementById(resultFieldPreview)
                    .getText();
    }

    //enter digits
    private void enterDigits(String value, String locator) {
        if (value.length() == 1)
            DriverManager.getDriver().findElementById(locator + value)
                    .click();
        else {
            for (int i = 0; i < value.length(); i++) {
                DriverManager.getDriver().findElementById(locator
                        + value.charAt(i)).click();
            }
        }
    }

    //enter values
    private void enterValues(String value) {
        this.openAdvancePad();
        DriverManager.getDriver().findElementById(Calculator.advancePadLocatorTable.get(value))
                .click();
        this.closeAdvancePad();
    }

    //add operation
    private void enterOperation(String value) {
        if (!Calculator.normalPadLocatorTable.containsKey(value)) {
            this.openAdvancePad();
            DriverManager.getDriver().findElementById(Calculator
                    .advancePadLocatorTable.get(value)).click();
            this.closeAdvancePad();
        } else
            DriverManager.getDriver().findElementById(Calculator
                    .normalPadLocatorTable.get(value)).click();
    }

    //set radian
    private void setRadian(String value) {
        if (value.contains("pi")) {
            this.openAdvancePad();
            DriverManager.getDriver().findElementById(Calculator
                    .advancePadLocatorTable.get("DEG")).click();
            this.closeAdvancePad();
        }
    }

    //clear result field
    private void clearResult() {
        if (DriverManager.getDriver().findElementsById(btn_clear).size() != 0)
            DriverManager.getDriver().findElementById(btn_clear).click();
        else
            new TouchAction(DriverManager.getDriver())
                    .longPress(LongPressOptions.longPressOptions().withDuration(Duration.ofSeconds(2))
                            .withElement(ElementOption.element(DriverManager
                                    .getDriver().findElementById(btn_del))))
                    .perform();
    }

    //open advance pad
    private void openAdvancePad() {
        if (DriverManager.getDriver()
                .findElementsById(advance_pad).size() == 0) {
            TouchAction action = new TouchAction(DriverManager.getDriver());
            action.press(PointOption.point(995, 1313)).
                    moveTo(PointOption.point(240, 1313))
                    .release()
                    .perform();
            isAdvanceTabOpen = true;

        }
    }

    //close advance pad
    private void closeAdvancePad() {
        if (isAdvanceTabOpen) {
            DriverManager.getDriver().findElementById(collapse_arrow).click();
        }
    }

}
