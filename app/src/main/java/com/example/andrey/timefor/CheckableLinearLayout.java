package com.example.andrey.timefor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Checkable;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    private boolean isChecked = false;

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setChecked(boolean checked) {
        isChecked= checked;
        changeColor(isChecked);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
        changeColor(isChecked);
    }

    private void changeColor(boolean isChecked){
        if (isChecked) {
            setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        } else {
            setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }
}
