package whosbetta.whosbetta.whosbest.abettaman;

import java.io.IOException;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class CameraPreview2 extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder holder;
    private Camera camera;
    @SuppressWarnings("unused")
    private Context context;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final String TAG = "Quazi's SurfaceView";
    @SuppressWarnings("deprecation")
    CameraPreview2(Context context)
    {
        super(context);
        this.context = context;
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.FILL);
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        try
        {
            camera = Camera.open();
//camera.setDisplayOrientation(90); // Rotates Camera's preview 90
// degrees
            camera.setPreviewDisplay(holder);
        }
        catch (IOException e)
        {
        }
//tryDrawing(holder);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height)
    {
        camera.startPreview();
//tryDrawing(holder);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }
// private void tryDrawing(SurfaceHolder holder)
// {
// Log.i(TAG, "Trying to draw...");
//
// Canvas canvas = new Canvas();// holder.lockCanvas();
// if (canvas == null)
// {
// Log.e(TAG, "Cannot draw onto the canvas as it's null");
// }
// else
// {
// Log.d(TAG, "Trying to draw...1");
// canvas.drawCircle(100, 200, 50, paint);
// //holder.unlockCanvasAndPost(canvas);
// }
// Log.d(TAG, "Trying to draw...2");
// }
}