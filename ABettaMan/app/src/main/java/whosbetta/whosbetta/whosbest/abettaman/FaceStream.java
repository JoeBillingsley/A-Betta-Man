package whosbetta.whosbetta.whosbest.abettaman;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.dropin.Customization;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import whosbetta.whosbetta.whosbest.abettaman.util.SystemUiHider;

import static android.widget.AbsoluteLayout.LayoutParams;
import static android.widget.AbsoluteLayout.LayoutParams.*;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
@SuppressWarnings("deprecation")
public class FaceStream extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    public static TextView text;
    private CameraPreview preview;
    private Camera camera;

    private int backCamera, frontCamera;

    private OurFaceDetectionListener listener;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_face_stream);

        // Find the total number of cameras available
        int numberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the rear-facing ("default") camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                backCamera = i;
            }
            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                frontCamera = i;
            }
        }

        gestureDetector = new GestureDetector(this,this);

        camera = Camera.open(backCamera);
        listener = new OurFaceDetectionListener(this);
        camera.setFaceDetectionListener(listener);
        camera.startFaceDetection();

        CameraPreview cameraPreview = new CameraPreview(this, camera);
        addContentView(cameraPreview, new ViewGroup.LayoutParams(-1, -1));
        addContentView(listener, new ViewGroup.LayoutParams(-1, -1));
    }

    public void test() {
        Log.i("hello", "im here");
        //text = (TextView)findViewById(R.id.textStuff);
        //text.bringToFront();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onResume() {
        super.onResume();

        //if(camera == null) {
         //   camera = getCamera();
         //   camera.open();
        // }

//        mBraintree.addListener(this);
//        mBraintree.unlockListeners();
    }

    @Override
    public void onPause() {

        super.onPause();

        //if (camera != null) {
         //   camera.release();
        //    camera = null;
       // }

//        mBraintree.lockListeners();
//        mBraintree.removeListener(this);
    }


    @Override
    public void onStop() {
        super.onStop();
       camera.release();
    }


    private Camera getCamera() {
        Camera c = Camera.open();
        return c;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

//        camera.stopFaceDetection();
//        camera.stopPreview();
//
//        camera.release();
//
//        camera.open(frontCamera);
//        camera.startPreview();
//        listener = new OurFaceDetectionListener(this);
//        camera.setFaceDetectionListener(listener);
//        camera.startFaceDetection();
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }
}
