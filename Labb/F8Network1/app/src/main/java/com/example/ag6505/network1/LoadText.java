package com.example.ag6505.network1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

public class LoadText {
	private TextListener listener;
    private String stringUrl;
    private String encoding;
    private MainActivity mainActivity;
	
	public LoadText(TextListener listener, String url, String encoding,MainActivity mainActivity) {
		if(listener!=null) {
			this.listener = listener;
            this.stringUrl = url;
            this.encoding = encoding;
            this.mainActivity = mainActivity;
		}
		new Thread(new textLoader()).start();
	}


	public interface TextListener {
		public void textLoaded(String str);
	}

	private class textLoader implements Runnable{

        @Override
        public void run() {
            final StringBuilder result = new StringBuilder();
            BufferedReader reader = null;
            try {
                URL url = new URL(stringUrl);
                URLConnection connection = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
                String txt;
                while((txt=reader.readLine())!=null)
                    result.append(txt+"\n");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch(Exception e) {}
            }

            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.textLoaded(result.toString());
                }
            });
        }
    }
}


/*
public class LoadText extends AsyncTask<String,Integer,String> {
	private TextListener listener;

	public LoadText(TextListener listener, String url, String encoding) {
		if(listener!=null) {
			this.listener = listener;
			execute(url, encoding);
		}
	}
	@Override
	protected String doInBackground(String... params) {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		try {
			URL url = new URL(params[0]);
			URLConnection connection = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), params[1]));
			String txt;
			while((txt=reader.readLine())!=null)
				result.append(txt+"\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch(Exception e) {}
		}
		return result.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		listener.textLoaded(result);
	}

	public interface TextListener {
		public void textLoaded(String str);
	}
}
 */