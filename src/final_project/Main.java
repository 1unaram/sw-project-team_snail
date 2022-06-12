package final_project;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	static String clientId = "9pwchirpzc";
	static String clientSecret = "CNQSofjNTeOFHOoODDAvpk0QNs9a2MXZh80kFXbM";

	public static void main(String[] args) {

		new MainFrame();
		// new NaverMap();
	}

}

// 검색 트렌드 class
class DatalabSearch {

	DatalabSearch() {
		String clientId = "rcpSRqFxxhEfRkZjhZiy";
		String clientSecret = "0eERkQtJuE";

		String apiUrl = "https://openapi.naver.com/v1/datalab/search";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		requestHeaders.put("Content-Type", "application/json");

		String requestBody = "{\"startDate\":\"2017-01-01\"," +
				"\"endDate\":\"2017-04-30\"," +
				"\"timeUnit\":\"month\"," +
				"\"keywordGroups\":[{\"groupName\":\"한글\"," +
				"\"keywords\":[\"한글\",\"korean\"]}," +
				"{\"groupName\":\"영어\"," +
				"\"keywords\":[\"영어\",\"english\"]}]," +
				"\"device\":\"pc\"," +
				"\"ages\":[\"1\",\"2\"]," +
				"\"gender\":\"f\"}";

		String responseBody = post(apiUrl, requestHeaders, requestBody);
		getJSONParsing(responseBody);
	}

	public static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
		HttpURLConnection con = connect(apiUrl);

		try {
			con.setRequestMethod("POST");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(requestBody.getBytes());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	public static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	public static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	public static void getJSONParsing(String response) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(response);
			JSONObject jsonObj = (JSONObject) obj;
			// System.out.println((String)jsonObj.get("startDate"));
			createJSONFile(jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void createJSONFile(JSONObject obj) {
		try {
			FileWriter file = new FileWriter("data/trend_export.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			System.out.println("Complete to create Trend JSON File");
		} catch (IOException e) {
			System.out.println("JSON 파일 추출 오류 : " + e);
		}
	}
}

// 검색 class
class Search {

	Search() {
		String clientId = "rcpSRqFxxhEfRkZjhZiy"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "0eERkQtJuE"; // 애플리케이션 클라이언트 시크릿값"

		String text = null;
		try {
			text = URLEncoder.encode("중앙대 맛집", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text + "&sort=comment&display=10"; // Blog
		// String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text; //

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		getJSONParsing(responseBody);
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	public static void getJSONParsing(String response) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(response);
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println((String) jsonObj.get("startDate"));
			createJSONFile(jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void createJSONFile(JSONObject obj) {
		try {
			FileWriter file = new FileWriter("data/search_export.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			System.out.println("Complete to create SEARCH JSON File");
		} catch (IOException e) {
			System.out.println("JSON 파일 추출 오류 : " + e);
		}
	}

}
