package whosbetta.whosbetta.whosbest.abettaman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AnimationView extends View  {
    static int x, y, r = 255, g = 255, b = 255;
    final static int radius = 30;
    Paint paint; // using this ,we can draw on canvas

    public AnimationView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        paint = new Paint();
        paint.setAntiAlias(true); // for smooth rendering
        paint.setARGB(255, r, g, b); // setting the paint color
        // to make it focusable so that it will receive touch events properly
        setFocusable(true);
        // adding touch listener to this view
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setARGB(255, r, g, b);
        // drawing the circle
        canvas.drawCircle(x, y, radius, paint);
    }
}