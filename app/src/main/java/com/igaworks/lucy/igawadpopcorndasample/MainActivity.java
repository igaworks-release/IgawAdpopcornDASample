package com.igaworks.lucy.igawadpopcorndasample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.igaworks.IgawCommon;
import com.igaworks.displayad.IgawDisplayAd;
import com.igaworks.displayad.common.DAErrorCode;
import com.igaworks.displayad.interfaces.IBannerEventCallbackListener;
import com.igaworks.displayad.interfaces.IEndingAdEventCallbackListener;
import com.igaworks.displayad.interfaces.IInterstitialEventCallbackListener;
import com.igaworks.displayad.interfaces.IPopupEventCallbackListener;
import com.igaworks.displayad.view.BannerContainerView;
import com.igaworks.nativead.IgawNativeAd;
import com.igaworks.nativead.IgawNativeAdErrorCode;
import com.igaworks.nativead.IgawNativeAdListener;


public class MainActivity extends ActionBarActivity implements IBannerEventCallbackListener, IInterstitialEventCallbackListener, IPopupEventCallbackListener, IEndingAdEventCallbackListener {

    // Igaworks Common
    String tag = "Igaw";

    // Igaworks Adpopcorn DA Banner
    private BannerContainerView bannerView;
    String bannerSpotkey = "9e2eaa8560";

    // Igaworks Adpopcorn DA Interstitial
    String interstitialSpotkey = "bc9f006672";

    // Igaworks Adpopcorn DA Popup
    String PopupSpotkey = "4692bfd914";

    // Igaworks Adpopcorn DA Ending
    String EndingSpotkey = "21c5abb411";

    // Igaworks Adpopcorn DA Native
    String NativeSpotkey = "6504610602";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Igaworks Adpopcorn DA Banner
        bannerView = (BannerContainerView)findViewById(R.id.banner_container);

        // Igaworks Adpopcorn DA Banner Optional
        IgawDisplayAd.setBannerEventCallbackListener(MainActivity.this, bannerSpotkey, this);
        // Igaworks Adpopcorn DA Interstitial Optional
        IgawDisplayAd.setInterstitialEventCallbackListener(MainActivity.this, interstitialSpotkey, this);
        // Igaworks Adpopcorn DA Popup Optional
        IgawDisplayAd.setPopupEventCallbackListener(MainActivity.this, PopupSpotkey, this);
        // Igaworks Adpopcorn DA Ending Optional
        IgawDisplayAd.setEndingAdEventCallbackListener(MainActivity.this, EndingSpotkey, this);


        // Igaworks Common
        IgawCommon.startApplication(MainActivity.this);
        Log.d(tag, "startApplication ::: MainActivity");

        // Igaworks Adpopcorn DA
        IgawDisplayAd.init(MainActivity.this);
        Log.d(tag, "init ::: MainActivity");

        // Igaworks Adpopcorn DA Optional
        // IgawDisplayAd.setRefreshTime(40);
        // IgawDisplayAd.setLocation(123.456789, 23.456789);

        // Igaworks Adpopcorn DA Ending
        IgawDisplayAd.loadEndingAd(MainActivity.this, EndingSpotkey);
        Log.d(tag, "loadEndingAd ::: " + EndingSpotkey);

        /*
         * Your Code
         */
    }


     /*
      * Your Code
      */


    // Your Code
    public void openInterstitial(View view){
        Log.d(tag, "openInterstitial ::: Button Click");

        // Igaworks Adpopcorn DA Interstitial
        IgawDisplayAd.showInterstitialAd(MainActivity.this, interstitialSpotkey);
        Log.d(tag, "showInterstitialAd ::: " + interstitialSpotkey );

    }

    // Your Code
    public void openPopup(View view){
        Log.d(tag, "openPopup ::: Button Click");

        // Igaworks Adpopcorn DA Popup
        IgawDisplayAd.showPopupAd(MainActivity.this, PopupSpotkey);
        Log.d(tag, "showPopupAd ::: " + PopupSpotkey );
    }

    // Your Code
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // Igaworks Adpopcorn DA Ending
            IgawDisplayAd.showEndingAd(MainActivity.this, EndingSpotkey);
            Log.d(tag, "showEndingAd ::: " + EndingSpotkey);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void loadNative(View view){
        Log.d(tag, "loadNative ::: Button Click");

        // Igaworks Adpopcorn DA Native
        final IgawNativeAd nativeAd = new IgawNativeAd(MainActivity.this, NativeSpotkey, new IgawNativeAdListener() {
            @Override
            public void OnNativeAdRequestSucceeded(IgawNativeAd igawNativeAd) {
                // Igaworks Adpopcorn DA Native
                String nativeAdContents = igawNativeAd.getNativeAdContents();
                Log.d(tag, "OnNativeAdRequestSucceeded ::: " + nativeAdContents);

            }

            @Override
            public void OnNativeAdRequestFailed(IgawNativeAdErrorCode daErrorCode) {
                Log.d(tag, "OnNativeAdRequestFailed ::: \"ResultCode\": " + daErrorCode.getErrorCode() + ", \"ResultMsg\": " + daErrorCode.getErrorMessage());

            }
        });

        // Igaworks Adpopcorn DA Native
        nativeAd.loadAd();
        Log.d(tag, "nativeAd ::: loadAd");

        // Igaworks Adpopcorn DA Native
        nativeAd.impressionAction();
        Log.d(tag, "nativeAd ::: impressionAction");

        // Igaworks Adpopcorn DA Native
        nativeAd.clickAction(MainActivity.this);
        Log.d(tag, "nativeAd ::: clickAction");

        // Igaworks Adpopcorn DA Native
        //nativeAd.destroyAd();
        //Log.d(tag, "nativeAd ::: destroyAd");

    }


    @Override
    protected void onResume() {
        super.onResume();

        // Igaworks Common
        IgawCommon.startSession(MainActivity.this);
        Log.d(tag, "startSession ::: MainActivity" );

        // Igaworks Adpopcorn DA Banner
        IgawDisplayAd.startBannerAd(MainActivity.this, bannerSpotkey, bannerView);
        Log.d(tag, "startBannerAd ::: " + bannerSpotkey );

        /*
         * Your Code
         */
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Igaworks Adpopcorn DA Banner
        IgawDisplayAd.pauseBannerAd(MainActivity.this, bannerSpotkey);
        Log.d(tag, "pauseBannerAd ::: MainActivity");

        // Igaworks Common
        IgawCommon.endSession();
        Log.d(tag, "endSession ::: MainActivity");

        /*
         * Your Code
         */
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        // Igaworks Adpopcorn DA Banner
        IgawDisplayAd.stopBannerAd(MainActivity.this);
        Log.d(tag, "stopBannerAd ::: MainActivity");

        // Igaworks Adpopcorn DA
        IgawDisplayAd.destroy();
        Log.d(tag, "destroy ::: MainActivity");

    }


    @Override
    public void OnBannerAdReceiveSuccess() {
        // Igaworks Adpopcorn DA Banner Optional
        Log.d(tag, "OnBannerAdReceiveSuccess ::: Complete Loading Banner Ad");

    }

    @Override
    public void OnBannerAdReceiveFailed(DAErrorCode daErrorCode) {
        // Igaworks Adpopcorn DA Banner Optional
        Log.d(tag, "OnBannerAdReceiveFailed ::: \"ResultCode\": " + daErrorCode.getErrorCode() + ", \"ResultMsg\": " + daErrorCode.getErrorMessage());

    }


    @Override
    public void OnInterstitialReceiveSuccess() {
        // Igaworks Adpopcorn DA Interstitial Optional
        Log.d(tag, "OnInterstitialReceiveSuccess ::: Complete Loading Interstitial Ad");
    }

    @Override
    public void OnInterstitialReceiveFailed(DAErrorCode daErrorCode) {
        // Igaworks Adpopcorn DA Interstitial Optional
        Log.d(tag, "OnInterstitialReceiveFailed ::: \"ResultCode\": " + daErrorCode.getErrorCode() + ", \"ResultMsg\": " + daErrorCode.getErrorMessage());

    }

    @Override
    public void OnInterstitialClosed() {
        // Igaworks Adpopcorn DA Interstitial Optional
        Log.d(tag, "OnInterstitialClosed ::: Interstitial Ad is closed");

    }


    @Override
    public void OnPopupAdReceiveSuccess() {
        // Igaworks Adpopcorn DA Popup Optional
        Log.d(tag, "OnPopupAdReceiveSuccess ::: Complete Loading Popup Ad");

    }

    @Override
    public void OnPopupAdReceiveFailed(DAErrorCode daErrorCode) {
        // Igaworks Adpopcorn DA Popup Optional
        Log.d(tag, "OnPopupAdReceiveFailed ::: \"ResultCode\": " + daErrorCode.getErrorCode() + ", \"ResultMsg\": " + daErrorCode.getErrorMessage());

    }

    @Override
    public void OnPopupAdClosed() {
        // Igaworks Adpopcorn DA Popup Optional
        Log.d(tag, "OnPopupAdClosed ::: Popup Ad is closed");

    }


    @Override
    public void OnBtnClickListener(boolean isClicked) {
        // Igaworks Adpopcorn DA Ending Optional
        Log.d(tag, "OnBtnClickListener ::: " + isClicked);

        // Your Code
        if (isClicked) {

            // Igaworks Common
            IgawCommon.endSession();
            Log.d(tag, "endSession ::: MainActivity");

            android.os.Process.killProcess(android.os.Process.myPid());
            Log.d(tag, "killProcess ::: MainActivity");
        }

    }

}