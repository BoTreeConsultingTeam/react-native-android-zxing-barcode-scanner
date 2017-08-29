package com.botree.barcodescanner;
 
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.facebook.react.bridge.Promise;

public class ZxingModule extends ReactContextBaseJavaModule {
  
  private IntentIntegrator integrator;
  private Promise mCameraPromise;

  public ZxingModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
 
  @Override
  public String getName() {
    return "ZxingModule";
  }
 
 @ReactMethod
  public void doStartCamera(final Promise promise) {

    mCameraPromise = promise;
    try {
    integrator = new IntentIntegrator(this.getCurrentActivity());
                integrator.setPrompt("Scan a QR Code");
                integrator.setOrientationLocked(true);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.setCameraId(0);
                integrator.initiateScan();
    } catch(Exception e) {
      mCameraPromise.reject(e);
      mCameraPromise = null; 
    } 
    
  }


  
 public boolean handleActivityResult(IntentResult result) {
    // Your custom handling logic
      if(result != null) {
          if(result.getContents() == null) {
             
              if(mCameraPromise != null){
                mCameraPromise.reject(""); 
                mCameraPromise = null; 
              }
              return true;
          } else {
              if(mCameraPromise != null){
                mCameraPromise.resolve(result.getContents()); 
              }
              return true;
          }
      } else {
        return false;
      }
  }
}