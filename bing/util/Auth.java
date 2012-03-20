package bing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Auth {

	private static String getToken( String clientId, String clientSecret, String scope) throws IOException{
				
		String data = URLEncoder.encode("grant_type", "UTF-8")+"=" + URLEncoder.encode("client_credentials", "UTF-8");
		data+="&"+ URLEncoder.encode("client_id", "UTF-8")+"=" + URLEncoder.encode( clientId , "UTF-8");
		data+="&"+ URLEncoder.encode("client_secret", "UTF-8")+"=" + URLEncoder.encode( clientSecret , "UTF-8");
		data+="&"+ URLEncoder.encode("scope", "UTF-8")+ "=" + scope;
		
		//System.out.println( data );
		
		URL url = new URL("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestMethod("POST");
		conn.setDoOutput( true );
		
		OutputStreamWriter writer = new OutputStreamWriter( conn.getOutputStream() );
		writer.write( data );
		writer.flush();
		
		
		//Resposta
		BufferedReader reader = new BufferedReader( new InputStreamReader(conn.getInputStream()));
		String line;
		String token = "";
		while( (line = reader.readLine()) != null )
			token+= line ;
		
		
		writer.close();
		reader.close();
		
		return token;
	}
	
	public static String accessToken( String clientId, String clientSecret, String scope ) throws IOException{
		
		String acessToken = "";		
		acessToken =  getToken( clientId, clientSecret, scope ).split(",")[ 0 ].split(":")[ 1 ];		
		return acessToken.substring( 1, acessToken.length() -1 );
	}
	
}
