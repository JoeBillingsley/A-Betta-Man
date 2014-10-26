package whosbetta.whosbetta.whosbest.abettaman;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
public class FaceStream extends Activity {

    public static TextView text;
    private CameraPreview preview;
    private Camera camera;

    private int currentCamera;
    private static final String SERVER_BASE = "10.8.166.92/ABettaMan/Servlet:8080";
    private static final int REQUEST_CODE = Menu.FIRST;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String clientToken;

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
                currentCamera = i;
            }
        }

        camera = getCamera();
        OurFaceDetectionListener listener = new OurFaceDetectionListener(this);
        camera.setFaceDetectionListener(listener);
        camera.startFaceDetection();

        CameraPreview cameraPreview = new CameraPreview(this, camera);
        addContentView(cameraPreview, new ViewGroup.LayoutParams(-1, -1));
        addContentView(listener, new ViewGroup.LayoutParams(-1, -1));

        getToken();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == BraintreePaymentActivity.RESULT_OK) {
            String paymentMethodNonce = data.getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);
            RequestParams requestParams = new RequestParams();
            requestParams.put("payment_method_nonce", paymentMethodNonce);
            requestParams.put("amount", "10.00");
            client.post(SERVER_BASE + "/payment", requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    Toast.makeText(FaceStream.this, content, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onStartClick(View view){
        Customization customization = new Customization.CustomizationBuilder()
                .primaryDescription("Awesome payment")
                .secondaryDescription("Using the Client SDK")
                .amount("$10.00")
                .submitButtonText("Pay")
                .build();
        Intent intent = new Intent(this, BraintreePaymentActivity.class);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CUSTOMIZATION, customization);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, clientToken);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void getToken() {
        client.get(SERVER_BASE + "/token", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                clientToken = content;
                findViewById(R.id.btn_start).setEnabled(true);
            }
        });
    }

    public void test() {
        Log.i("hello", "im here");
        //text = (TextView)findViewById(R.id.textStuff);
        //text.bringToFront();
    }

    @Override
    public void onResume() {
        super.onResume();

//        mBraintree.addListener(this);
//        mBraintree.unlockListeners();
    }

    @Override
    public void onPause() {

        super.onPause();

        if (camera != null) {
            camera.release();
            camera = null;
        }

//        mBraintree.lockListeners();
//        mBraintree.removeListener(this);
    }

    private Camera getCamera() {
        Camera c = Camera.open();
        return c;
    }

}
