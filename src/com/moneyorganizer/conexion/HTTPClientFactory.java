package com.moneyorganizer.conexion;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HTTPClientFactory {

	public static enum ClientType {
		HTTP_CLIENT, HTTP_URL_CONNECTION
	}

	public char[] getInputStreamFromHttpClient(String sourcePath)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(sourcePath);
		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode == 200) {
			HttpEntity entity = response.getEntity();
			char[] buffer = new char[(int)entity.getContentLength()];
	        InputStream stream = entity.getContent();
	        InputStreamReader reader = new InputStreamReader(stream);
	        reader.read(buffer);
	        stream.close();
            return buffer;
		}
		return null;
	}
}
