package com.android.test.views;

import com.android.test.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

public class ComboBox extends LinearLayout {
	public ComboBox(Context context) {
        super(context);
    }

    public ComboBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ComboBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ComboBox combobox = (ComboBox)findViewById(R.id.combobox);
		Button button1 = (Button)combobox.findViewById(R.id.button1);
		button1.setText("hello");
    }
}
