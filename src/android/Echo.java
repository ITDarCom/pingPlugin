package com.example.myPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class Ping extends CordovaPlugin{
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
	    super.initialize(cordova, webView);
	    // your init code here
	}
	
	 @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("ping".equals(action)) {
            this.echo(args,callbackContext);
            callbackContext.success();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }
	 
	 private void ping(JSONArray args, CallbackContext callbackContext) {
        if (args != null && args.length() > 0) {
            JSONArray resultList = new JSONArray();
            for(Object object : args){
                String ip = (String) object;
                boolean result = executePing(ip);
                if(result){
                    resultList.put("success");
                }
                else{
                    resultList.put("timeout");
                }
            }
            callbackContext.success(resultList);
        } else {
            callbackContext.error("Error occurred");
        }
     }

    private boolean executePing(ip){
         
        System.out.println(" executeCammand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 "+ip);
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue "+mExitValue);
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            System.out.println(" Exception:"+ignore);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println(" Exception:"+e);
        }
        return false;
    }
}
