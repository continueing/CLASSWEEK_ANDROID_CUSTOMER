package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.payment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.InicisPaymentInfo;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.apache.http.util.EncodingUtils;

import java.net.URISyntaxException;

public class PaymentWebViewActivity extends ActionBarActivity {
    public static final String SCREEN_NAME= "paymentWebView";
    public static final String BUNDLE_PARM_INICIS_PAYMENT_INFO= "INICIS_PAYMENT_INFO";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);
        getActionBar().setIcon(android.R.color.transparent);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebChromeClient(new ChromeClient());
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new AndroidBridge(),"BridgeToAndroid");


        Intent intent = getIntent();

        if(intent.getData() != null) {
            Log.e("return url", "http://"+intent.getData().getHost()+intent.getData().getPath()+"?"+intent.getData().getQuery());
            myWebView.loadUrl("http://" + intent.getData().getHost() + intent.getData().getPath() + "?" + intent.getData().getQuery());
        }
        else {
            InicisPaymentInfo inicisPaymentInfo = (InicisPaymentInfo) intent.getSerializableExtra(BUNDLE_PARM_INICIS_PAYMENT_INFO);

            String postData =
                    "P_MID=" + inicisPaymentInfo.getMId() + "&" +
                            "P_AMT=" + inicisPaymentInfo.getAmount() + "&" +
                            "P_UNAME=" + inicisPaymentInfo.getUserName() + "&" +
                            "P_NOTI=" + inicisPaymentInfo.getNoti() + "&" +
                            "P_NEXT_URL=" + inicisPaymentInfo.getNextUrl() + "&" +
                            "P_NOTI_URL=" + inicisPaymentInfo.getNotiUrl() + "&" +
                        "P_RETURN_URL="+"classweek"+inicisPaymentInfo.getReturnUrl().substring(4)+"&" +
                            "P_OID=" + inicisPaymentInfo.getOId() + "&" +
                            "P_GOODS=" + inicisPaymentInfo.getGoods() + "&" +
                            "paymethod=wcard&" +
                            "inipaymobile_type=web";
            String url = "https://mobile.inicis.com/smart/wcard/";
            myWebView.postUrl(url, EncodingUtils.getBytes(postData, "euc-kr"));
        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payment_web_view, menu);
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            /*
	    	 * URL별로 분기가 필요합니다. 어플리케이션을 로딩하는것과
	    	 * WEB PAGE를 로딩하는것을 분리 하여 처리해야 합니다.
	    	 * 만일 가맹점 특정 어플 URL이 들어온다면
	    	 * 조건을 더 추가하여 처리해 주십시요.
	    	 */
//             && url.startsWith("http://175.126.82.184" )&& url.startsWith("https://175.126.82.184")
            if( !url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript:"))
            {
                Intent intent;

                try{
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    Log.e("<INICIS_TEST>", "intent getDataString : " + intent.getDataString());
                } catch (URISyntaxException ex) {
                    Log.e("<INICIS_TEST>", "URI syntax error : " + url + ":" + ex.getMessage());
                    return false;
                }

                Uri uri = Uri.parse(intent.getDataString());
                intent = new Intent(Intent.ACTION_VIEW, uri);

                try{

                    startActivity(intent);

                    /*가맹점의 사정에 따라 현재 화면을 종료하지 않아도 됩니다.
	    			    삼성카드 기타 안심클릭에서는 종료되면 안되기 때문에
	    			    조건을 걸어 종료하도록 하였습니다.*/
                    if( url.startsWith("ispmobile://"))
                    {
                        finish();
                    }

                }catch(ActivityNotFoundException e)
                {
                /* ISP어플이 현재 폰에 없다면 아래 if 구문에서 처리 합니다.
	    			 * 삼성카드 및 기타 안심클릭에서는
	    			 * 카드사 웹페이지에서 알아서 처리하기때문에
	    			 * WEBVIEW에서는 별다른 처리를 하지 않아도 처리됩니다.
	    			 */
                    if( url.startsWith("ispmobile://"))
                    {
                        return false;
                    }
                }

            }
            else
            {
                Log.e("else ",  url  );
                view.loadUrl(url);
                return false;
            }

            return true;
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e("onReceivedError",  "error code : "+errorCode+ "\n description: " + description + "\n failingUrl: "+ failingUrl );

        }
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            PaymentWebViewActivity.this.setProgress(newProgress * 1000);
        }
    }

    private class AndroidBridge {

        @JavascriptInterface
        public void sendSuccess() {
            handler.post(new Runnable() {
                public void run() {
                    Log.e("AndroidBridge","success");
                    Toast.makeText(getApplicationContext(),"결제를 완료하셨습니다. 현재 수강중 페이지에서 확인하실 수 있습니다.",Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }

        @JavascriptInterface
        public void sendFail() {
            handler.post(new Runnable() {
                public void run() {
                    Log.e("AndroidBridge","fail");
                    Toast.makeText(getApplicationContext(),"결제가 중단되었습니다.",Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(this);
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }
}
