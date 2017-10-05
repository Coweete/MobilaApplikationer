package com.example.ag6505.network1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoadBitmap extends Thread {

	private BitmapListener listener;
	private String url = "";
    private MainActivity mainActivity;

	public LoadBitmap(BitmapListener listener, String url,MainActivity mainActivity) {
		if(listener!=null) {
		    this.listener = listener;
			this.url = url;
		    //execute(url);
			this.mainActivity = mainActivity;
            this.start();

		}
	}

	@Override
	public void run() {
		Bitmap result = null;
		URL url = null;
		URLConnection connection;
		InputStream input=null;
		try {
			url = new URL(this.url);
			connection = url.openConnection();
			input = connection.getInputStream();
			result = BitmapFactory.decodeStream(input);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(input!=null)
				try {
					input.close();
				} catch (IOException e) {}
		}

        final Bitmap finalResult = result;
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listener.bitmapLoaded(finalResult);
            }
        });

	}




    public interface BitmapListener {
		public void bitmapLoaded(Bitmap bitmap);
	}
}

/*
	public class LoadBitmap extends AsyncTask<String,Integer,Bitmap> {
	private BitmapListener listener;

	public LoadBitmap(BitmapListener listener, String url) {
		if(listener!=null) {
		    this.listener = listener;
		    execute(url);
		}
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap result = null;
		URL url;
		URLConnection connection;
		InputStream input=null;
		try {
			url = new URL(params[0]);
			connection = url.openConnection();
			input = connection.getInputStream();
			result = BitmapFactory.decodeStream(input);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    if(input!=null)
				try {
					input.close();
				} catch (IOException e) {}
		}
		return result;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		listener.bitmapLoaded(result);
	}

	public interface BitmapListener {
		public void bitmapLoaded(Bitmap bitmap);
	}
}
 */