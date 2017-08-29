package com.botree.barcodescanner;
 
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.Intent;
import android.content.Context;
import java.util.Arrays;
import com.google.zxing.integration.android.IntentResult;
 
public class ZxingPackage implements ReactPackage {
  private Context mContext;
  private ZxingModule zxingModule;
  public static ZxingModule temp;

  public ZxingPackage() {

  }

  public ZxingPackage(Context activityContext) {
    mContext = activityContext;

  }
 
  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }
 
  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
 
  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    // List<NativeModule> modules = new ArrayList<>();
    zxingModule = new ZxingModule(reactContext);
    ZxingPackage.temp = zxingModule;
    // modules.add(new ZxingModule(reactContext));
    return Arrays.<NativeModule>asList(zxingModule);
    // return modules;
  }

  public boolean handleActivityResult(IntentResult result) {
    return ZxingPackage.temp.handleActivityResult(result);
     

  }
 
}