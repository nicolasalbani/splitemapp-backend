package com.splitemapp.commons.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.splitemapp.commons.constants.ServiceConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestUtils {
	
	public static final int TIMEOUT = 10000;

	/**
	 *
	 * @param serviceUrl
	 * @param request
	 * @param responseType
	 * @param <E>
	 * @param <T>
	 * @return
	 */
	public static <E,T> T callRestService(String serviceUrl, E request, Class<T> responseType){
		// We get an instance of the spring framework RestTemplate and configure wrapping the root XML element
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		// We use old version of request factory that uses HTTPClient instead of HttpURLConnection to avoid bugs
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);
		restTemplate.setRequestFactory(requestFactory);  

		// Setting up the request header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "key="+ServiceConstants.API_KEY);
		
		// We make the POST rest service call
		HttpEntity<E> entity = new HttpEntity<E>(request,headers);
		T response = restTemplate.postForObject(serviceUrl, entity, responseType);

		return response;
	}

	/**
	 * Returns a byte[] containing the image
	 * @param imageUrl
	 * @return
	 * @throws IOException
	 */
	public static byte[] downloadImage(String imageUrl) throws IOException {
		URL imageURL = new URL(imageUrl);
		BufferedImage originalImage = ImageIO.read(imageURL);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);

		return baos.toByteArray();
	}

}
