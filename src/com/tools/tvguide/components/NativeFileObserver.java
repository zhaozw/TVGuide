package com.tools.tvguide.components;

import android.util.Log;

public class NativeFileObserver
{
	private static final String TAG = "NativeFileObserver";
	private static boolean sLoadSuccess = false;
	private String mPath;
	
	public NativeFileObserver(String path) 
	{
		mPath = path;
		
		if (sLoadSuccess == false)
		{
    		try
    		{
    		    System.loadLibrary("monitor");
    		    sLoadSuccess = true;
    		}
    		catch (UnsatisfiedLinkError e)
    		{
    		    Log.e(TAG, e.toString());
    		    sLoadSuccess = false;
    		}
		}
	}
	
	public void startWatching()
	{
	    if (sLoadSuccess)
	        nativeStartWatching(mPath);
	}
	
	public void stopWatching()
	{
	    if (sLoadSuccess)
	        nativeStopWatching();
	}
	
	public void setHttpRequestOnDelete(String url, String guid, String version)
	{
	    if (sLoadSuccess)
	        nativeSetHttpRequestOnDelete(url, guid, version);
	}

	private native void nativeStartWatching(String path);
	private native void nativeStopWatching();
	private native void nativeSetHttpRequestOnDelete(String url, String guid, String version);
}
