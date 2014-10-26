package whosbetta.whosbetta.whosbest.abettaman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Joe on 25/10/2014.
 */
public class FaceDrawer extends View {

    private Context context;

    public FaceDrawer(Context context) {
        super(context);

        this.context = context;
    }

    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawText("TURN UP FOR WHAT", 200, 30, p);
    }
}
