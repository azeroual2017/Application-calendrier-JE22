package utils_calendrier;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import JSONRessource.JSONConnection;


public class HttpHelper {

	public static String getUrlResponse(String address, JSONConnection jSON) throws IOException {
		
		String json = "{\"email\":\""+jSON.getEmail()+"\",\"textPassword\":\""+jSON.getTextPassword()+"\"}";
		URL url;
		InputStream is = null;
		try {
			url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Got response code " + responseCode);
            }
            is = conn.getInputStream();
            return readStream(is);
		} catch (IOException e) {
			e.printStackTrace();
			return "Error in connection";
		} finally {
	        if (is != null) {
	            is.close();
	        }
	    }
	}
	
	private static String readStream(InputStream stream) throws IOException {
        
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        BufferedOutputStream out = null;
        try {
            int length = 0;
            out = new BufferedOutputStream(byteArray);
            while ((length = stream.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            return byteArray.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
