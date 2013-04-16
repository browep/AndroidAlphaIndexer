package com.github.browep.alphaindexer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class SideSelector extends View {
    private static String TAG = SideSelector.class.getCanonicalName();

    public static char[] ALPHABET = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private SectionIndexer selectionIndexer = null;
    private ListView list;
    private Paint paint;

    public SideSelector(Context context) {
        super(context);
        init();
    }

    public SideSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SideSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setBackgroundColor(0x44FFFFFF);
        paint = new Paint();
        paint.setColor(0xFFA6A9AA);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void setListView(ListView _list) {
        list = _list;
        selectionIndexer = (SectionIndexer) _list.getAdapter();
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int y = (int) event.getY();
        float selectedIndex = ((float) y / (float) getHeight()) * ALPHABET.length;

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            if (selectionIndexer == null) {
                selectionIndexer = (SectionIndexer) list.getAdapter();
            }
            int position = selectionIndexer.getPositionForSection((int) selectedIndex);
            if (position == -1) {
                return true;
            }
            list.setSelection(position);
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {

        int viewHeight = getHeight();
        int charHeight = viewHeight / ALPHABET.length;

        float widthCenter = getMeasuredWidth() / 2;
        for (int i = 0; i < ALPHABET.length; i++) {
            canvas.drawText(String.valueOf(ALPHABET[i]), widthCenter, charHeight + (i * charHeight), paint);
        }
        super.onDraw(canvas);
    }
}
