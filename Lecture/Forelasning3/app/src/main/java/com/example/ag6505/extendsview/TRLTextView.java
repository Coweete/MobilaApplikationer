package com.example.ag6505.extendsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tsroax on 2014-09-06.
 */
/*
public class TRLTextView extends TextView {
    private static String consonants = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";

    public TRLTextView(Context context) {
        super(context);
    }

    public TRLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TRLTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setText(CharSequence text, BufferType type) {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<text.length(); i++) {
            if(consonants.indexOf(text.charAt(i)) >=0) {
                str.append(text.charAt(i));
                str.append('o');
                str.append(text.charAt(i));
            } else {
                str.append(text.charAt(i));
            }
        }
        super.setText(str.subSequence(0,str.length()),type);
    }
}
*/

public class TRLTextView extends TextView {
    private static String consonants = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";
    private static Paint paint, paint2;

    public TRLTextView(Context context) {
        super(context);
        init();
    }

    public TRLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TRLTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setText(CharSequence text, BufferType type) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (consonants.indexOf(text.charAt(i)) >= 0) {
                str.append(text.charAt(i));
                str.append('o');
                str.append(text.charAt(i));
            } else {
                str.append(text.charAt(i));
            }
        }
        super.setText(str.subSequence(0, str.length()), type);
    }

    private void init() {
        if(paint==null) {
            paint = new Paint();
            paint.setShader(new LinearGradient(0, 0, 30, 10, 0xD0A0D0, Color.WHITE, Shader.TileMode.REPEAT));
        }

        if (paint2== null){
            paint2 = new Paint();
            paint2.setColor(Color.BLACK);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(paint);
        canvas.drawLine(0, 0, 500, 100, paint2);
        super.onDraw(canvas);
    }
}

