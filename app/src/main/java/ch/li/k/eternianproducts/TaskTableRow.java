package ch.li.k.eternianproducts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableRow;

public class TaskTableRow extends TableRow {

    public TaskTableRow(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
    }

    public TaskTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
