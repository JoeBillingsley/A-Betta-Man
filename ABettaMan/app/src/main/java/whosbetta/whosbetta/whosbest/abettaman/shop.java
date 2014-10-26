package whosbetta.whosbetta.whosbest.abettaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.dropin.Customization;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class shop extends Activity {

    private static final String SERVER_BASE = "http://10.8.166.92:8080/ABettaMan/";
    private static final int REQUEST_CODE = Menu.FIRST;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String clientToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getToken();

        Log.i("Well", SERVER_BASE + "clienttoken");
        Log.i("Wooasdoh", String.valueOf(clientToken == null));
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
                    Toast.makeText(shop.this, "Wooh", Toast.LENGTH_LONG);
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

        OurFaceDetectionListener.payment = true;

        Intent intent2 = new Intent(this, FaceStream.class);
        startActivity(intent2);
    }


    public void onFreeHat(View view) {
        OurFaceDetectionListener.payment = false;

        Intent intent = new Intent(this, FaceStream.class);
        startActivity(intent);
    }

    private void getToken() {
     //   client.get(SERVER_BASE + "clienttoken", new TextHttpResponseHandler() {
       //     @Override
         //   public void onSuccess(String content) {
           //     clientToken = content;

             //   Log.i("asd", "mbc");

                clientToken = "eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJkYjA3YTgzODQ0YjQ2YzM1MTMyMjQ1MTNhMmY5OTE5YTFkNjAzMDQ0NTdlMTJiMjI3YzYyOWJjOGJkM2ViZWE1fGNyZWF0ZWRfYXQ9MjAxNC0xMC0yNlQxMToxOTo0NC40ODgxNDQzNTArMDAwMFx1MDAyNm1lcmNoYW50X2lkPTZmazNxMm1nOWczZHpieGtcdTAwMjZwdWJsaWNfa2V5PWRzanFrcmt5aDltajl6ZnIiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvNmZrM3EybWc5ZzNkemJ4ay9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwicGF5bWVudEFwcHMiOltdLCJjbGllbnRBcGlVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvNmZrM3EybWc5ZzNkemJ4ay9jbGllbnRfYXBpIiwiYXNzZXRzVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhdXRoVXJsIjoiaHR0cHM6Ly9hdXRoLnZlbm1vLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhbmFseXRpY3MiOnsidXJsIjoiaHR0cHM6Ly9jbGllbnQtYW5hbHl0aWNzLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb20ifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6ZmFsc2UsInBheXBhbEVuYWJsZWQiOnRydWUsInBheXBhbCI6eyJkaXNwbGF5TmFtZSI6InRnYzkyIiwiY2xpZW50SWQiOm51bGwsInByaXZhY3lVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vcHAiLCJ1c2VyQWdyZWVtZW50VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3RvcyIsImJhc2VVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFzc2V0c1VybCI6Imh0dHBzOi8vY2hlY2tvdXQucGF5cGFsLmNvbSIsImRpcmVjdEJhc2VVcmwiOm51bGwsImFsbG93SHR0cCI6dHJ1ZSwiZW52aXJvbm1lbnROb05ldHdvcmsiOnRydWUsImVudmlyb25tZW50Ijoib2ZmbGluZSIsIm1lcmNoYW50QWNjb3VudElkIjoiNzI4emI2cWtxZDI0NWd2biIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJjb2luYmFzZUVuYWJsZWQiOmZhbHNlLCJtZXJjaGFudElkIjoiNmZrM3EybWc5ZzNkemJ4ayIsInZlbm1vIjoib2ZmIn0=";

                findViewById(R.id.btn_start).setEnabled(true);
            //}
        //});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
