package whosbetta.whosbetta.whosbest.abettaman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by Joe on 25/10/2014.
 */
public class OurFaceDetectionListener extends View implements Camera.FaceDetectionListener {

    private String TAG = "HELLO?";

    private Context context;
    private Camera.Face[] faces = new Camera.Face[0];

    public OurFaceDetectionListener(Context context) {
        super(context);
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        Log.i("Found", "Face detected");
        this.faces = faces;
        invalidate();
    }

    private void tryDrawing(SurfaceHolder holder) {
        Log.i(TAG, "Trying to draw...");

        Canvas canvas = holder.lockCanvas();
        if (canvas == null) {
            Log.e(TAG, "Cannot draw onto the canvas as it's null");
        } else {
            drawMyStuff(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawMyStuff(final Canvas canvas) {
        if(faces.length > 0) {
            Log.i(TAG, "Drawing...");
            canvas.drawRGB(255, 128, 128);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawMyStuff(canvas);
    }
}