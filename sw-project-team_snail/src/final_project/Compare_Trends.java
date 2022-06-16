package final_project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Compare_Trends {

	/* 상속자 */
	Compare_Trends(ArrayList<String> strList, RankTextField[] textFieldArr, int cnt, ArrayList<String> rankingList) {
		String clientId = Main.searchClientId;// 애플리케이션 클라이언트 아이디값";
		String clientSecret = Main.searchClientSecret;// 애플리케이션 클라이언트 시크릿값";

		Map<Double, String> rankingmap = new HashMap<Double, String>(); // 순위 매기기 위해 사용한 해시맵
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date time = new Date();
			String endDate = format1.format(time);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -5);
			String startDate = format1.format(cal.getTime());

			String apiURL = "https://openapi.naver.com/v1/datalab/search";

			// person의 JSON정보를 담을 Array 선언
			JSONArray keywordGroups = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("startDate", startDate);
			jsonObject.put("endDate", endDate);
			jsonObject.put("timeUnit", "month");

			for (String str : strList) {
				if (str.equals(""))
					break;
				JSONObject keyword = new JSONObject();
				ArrayList<String> key = new ArrayList<String>();
				key.add(str);
				keyword.put("groupName", str);
				keyword.put("keywords", key);
				keywordGroups.add(keyword);
			}

			jsonObject.put("keywordGroups", keywordGroups);

			ArrayList<String> age = new ArrayList<String>();
			age.add("2");
			age.add("3");
			age.add("4");
			age.add("5");
			age.add("6");
			jsonObject.put("ages", age);

			// 요청
			String body = jsonObject.toString();
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setRequestProperty("Content-Type", "application/json");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(body.getBytes());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			// JSON 파싱
			String json = response.toString();
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(json);

			JSONArray results = (JSONArray) obj.get("results"); // json안에 있는 results 배열 가져오기
			double ratioSum = 0;
			for (int i = 0; i < results.size(); i++) {
				JSONObject tmp = (JSONObject) results.get(i); // results 안에 있는 배열 하나씩 출력
				String title = (String) tmp.get("title");
				System.out.println("----- " + (i + 1) + "번째 검색 결과 -----");
				System.out.println("주제어" + (i + 1) + " : " + title);
				JSONArray data = (JSONArray) tmp.get("data"); // results 배열 각각 마다 있는 data 배열 가져오기
				for (int j = 0; j < data.size(); j++) {
					JSONObject tmp2 = (JSONObject) data.get(j);
					String period = (String) tmp2.get("period");
					String ratio = tmp2.get("ratio").toString();
					double dratio = Double.parseDouble(ratio);
					ratioSum += dratio;
					System.out.println("날짜 : " + period);
					System.out.println("검색 빈도 : " + ratio);
				}
				ratioSum /= data.size();
				rankingmap.put(ratioSum, title);
				System.out.println("");
			}

			// 트렌드 순으로 정렬
			Object[] mapKey = rankingmap.keySet().toArray();
			Arrays.sort(mapKey, Collections.reverseOrder());

			for (Double keyVal : rankingmap.keySet()) {
				rankingList.add(rankingmap.get(keyVal));
			}

			TreeMap<Double, String> Reverse = new TreeMap<Double, String>(rankingmap);
			Iterator<Double> keyiterator = Reverse.descendingKeySet().iterator();

			Double k;
			String v;

			int index = 0;
			while (keyiterator.hasNext()) {
				k = (Double) keyiterator.next();
				v = (String) Reverse.get(k);
				rankingList.add(v);
				textFieldArr[index++].setText(v);
				System.out.println(k + "위 " + v);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}