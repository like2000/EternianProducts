package ch.li.k.eternianproducts.task;

import android.graphics.drawable.ColorDrawable;

public class TaskModel {
    private static final ColorDrawable redTrans = new ColorDrawable(0xa0ff0000);
    private static final ColorDrawable greenTrans = new ColorDrawable(0xa0228B22);
    private static final ColorDrawable silverTrans = new ColorDrawable(0xA0C0C0C0);

    private int arg1;
    private int arg2;
    private int result;
    private String operator;
    private ColorDrawable color;

    TaskModel(int arg1, int arg2, String operator) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.operator = operator;
        this.color = silverTrans;
    }

    void checkResult(int result) {
        boolean out = false;
        if ("*".equals(operator)) {
            out = arg1 * arg2 == result;
        } else if ("\u00F7".equals(operator)) {
            out = arg1 / arg2 == result;
        } else if ("+".equals(operator)) {
            out = arg1 + arg2 == result;
        }

        if (out) {
            setColor(greenTrans);
        } else {
            setColor(redTrans);
        }
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public String getResult() {
        return String.valueOf(result);
    }

    public void setResult(String result) {
        try {
            this.result = Integer.parseInt(result);
        } catch (NumberFormatException exception) {
        }
        System.out.println(result);
    }

    public ColorDrawable getColor() {
        return color;
    }

    private void setColor(ColorDrawable color) {
        this.color = color;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
