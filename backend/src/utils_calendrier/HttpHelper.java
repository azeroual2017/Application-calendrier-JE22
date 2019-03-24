package utils_calendrier;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper {

	private void UrlHandeler(String address) throws IOException {
		
		URL url;
		InputStream is = null;
		try {
			url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Got response code " + responseCode);
            }
            is = conn.getInputStream();
            //return readStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
