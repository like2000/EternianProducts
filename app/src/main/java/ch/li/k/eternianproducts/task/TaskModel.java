package ch.li.k.eternianproducts.task;

import android.graphics.drawable.ColorDrawable;

public class TaskModel {
    private final ColorDrawable redTrans = new ColorDrawable(0xa0ff0000);
    private final ColorDrawable greenTrans = new ColorDrawable(0xa0228B22);
    private final ColorDrawable silverTrans = new ColorDrawable(0xA0C0C0C0);

    private int arg1;
    private int arg2;
    private int result;
    private boolean check;
    private String operator;
    private ColorDrawable color;

    TaskModel(int arg1, int arg2, String operator) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.operator = operator;
        this.color = silverTrans;
    }

    public void checkResult(int result) {
        boolean out = false;
        if ("*".equals(operator)) {
            out = arg1 * arg2 == result;
        } else if ("\u00F7".equals(operator)) {
            out = arg1 / arg2 == result;
        }
        check = out;
        if (out) {
            color = greenTrans;
        } else {
            color = redTrans;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ColorDrawable getColor() {
        return color;
    }

    public void setColor(ColorDrawable color) {
        this.color = color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
