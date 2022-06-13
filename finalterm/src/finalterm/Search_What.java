package finalterm;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Search_What {

	List<StoreInfo> storeinfo;

	public Search_What(String Keyword, String category) {

		storeinfo = new ArrayList<StoreInfo>();

		String clientId = "XuXFTCmdT587nrJw2EdK"; // 애플리케이션 클라이언트 아이디값
		String clientSecret = "O6agF33Osq"; // 애플리케이션 클라이언트 시크릿값

		String text = null;
		try {
			text = URLEncoder.encode(Keyword + category, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text + "&sort=comment&display=5"; // Blog

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		getJSONParsing(responseBody);
	}

	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다." + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
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

	public void getJSONParsing(String response) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(response);
			JSONObject jsonObj = (JSONObject) obj;

			// System.out.println(jsonObj);
			// createJSONFile(jsonObj);

			bringtitleAddress(jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void createJSONFile(JSONObject obj) {
		try {
			FileWriter file = new FileWriter("data/search_export.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			System.out.println("Complete to create SEARCH JSON File");
		} catch (IOException e) {
			System.out.println("JSON 파일 추출 오류: " + e);
		}
	}

	public void bringtitleAddress(JSONObject obj) {

		try {
			JSONArray jsonArr = (JSONArray) obj.get("items");
			if (jsonArr.size() > 0) {
				for (int i = 0; i < jsonArr.size(); i++) {
					JSONObject jsonObj = (JSONObject) jsonArr.get(i);
					String title = (String) jsonObj.get("title");
					String address = (String) jsonObj.get("roadAddress");
					String mapx = (String) jsonObj.get("mapx");
					String mapy = (String) jsonObj.get("mapy");
					String link = (String) jsonObj.get("link");
					String tele = (String) jsonObj.get("telephone");
					String category = (String) jsonObj.get("category");

					// 매장 정보 저장
					StoreInfo store = new StoreInfo(title, address, mapx, mapy, link, tele, category);
					storeinfo.add(store);
				}
			}
		} catch (Exception e) {
			System.out.println("JSON 파일 오류" + e);
		}
	}

}
