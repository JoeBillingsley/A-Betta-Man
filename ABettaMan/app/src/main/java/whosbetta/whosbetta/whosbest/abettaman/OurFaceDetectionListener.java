package whosbetta.whosbetta.whosbest.abettaman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public static boolean payment = false;

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
        Paint p = new Paint();

        int vWidth = getWidth();
        int vHeight = getHeight();

        Log.i("Im here1", "faces length = " + faces.length);
        for (int i = 0; i < faces.length; i++) {
            Camera.Face f = faces[i];
            Rect newRect = new Rect();
            newRect.left = ( f.rect.left+1000) * vWidth/2000;
            newRect.top  = (f.rect.top+1000) * vHeight/2000;
            newRect.right = (f.rect.right+1000) * vWidth/2000;
            newRect.bottom = (f.rect.bottom+1000) * vHeight/2000;

            int increment = 20;

            newRect.left -= increment;
            newRect.right += increment;
            newRect.top -= increment;
            newRect.bottom += increment;

            Log.i("Face coords=", "left=" +newRect.left + " right=" + newRect.right + " top=" + newRect.top + " bottom="+ newRect.bottom);
            //canvas.drawRect(left,top,right,bottom,p);

            double rand = Math.random();
            int pic;

            if(payment){
                pic = R.drawable.qwer;
            } else {
                pic = R.drawable.wasd;
            }

            Bitmap myBitmap = BitmapFactory.decodeResource(
                    getResources(),
                    pic);

            canvas.drawBitmap(myBitmap,null,newRect,p);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawMyStuff(canvas);
    }
}