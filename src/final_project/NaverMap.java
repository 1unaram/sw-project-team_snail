package final_project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

class NaverMap {

    /* 전역 변수 */
    // 받아온 지도 이미지
    BufferedImage mapImg = null;

    /* NaverMap 생성자 */
    // Marker 처리 생성자
    NaverMap(String clientId, String clientSecret, int w, int h, Marker[] markers) {
        // 요청 파라미터를 포함한 URL
        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?crs=NHN:128&w="
                + Integer.toString(w) + "&h=" + Integer.toString(h);
        for (Marker m : markers) {
            apiURL += m.toString();
        }
        System.out.println(apiURL);

        // 요청 Header 설정
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-NCP-APIGW-API-KEY-ID", clientId);
        requestHeaders.put("X-NCP-APIGW-API-KEY", clientSecret);

        // API 요청하여 Image 할당
        this.mapImg = get(apiURL, requestHeaders);
    }

    // Marker 없는 베이직 지도 생성자
    NaverMap(String clientId, String clientSecret, int w, int h, String x, String y) {

        // 요청 파라미터를 포함한 URL
        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?crs=NHN:128&w="
                + Integer.toString(w) + "&h=" + Integer.toString(h) + "&center=" + x + "," + y + "&level=16";

        // 요청 Header 설정
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-NCP-APIGW-API-KEY-ID", clientId);
        requestHeaders.put("X-NCP-APIGW-API-KEY", clientSecret);

        // API 요청하여 Image 할당
        this.mapImg = get(apiURL, requestHeaders);
    }

    /* 메서드 */
    private static BufferedImage get(String apiUrl, Map<String, String> requestHeaders) {

        // API URL 연결
        HttpURLConnection con;
        try {
            URL url = new URL(apiUrl);
            con = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }

        // 작성한 Header를 포함하여 API 요청
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 정상 호출
                try {
                    BufferedImage img = ImageIO.read(con.getInputStream());
                    return img;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                System.out.println("API를 정상적으로 호출하지 못하였습니다.");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
}

class Marker {
    String type;
    String size;
    String color;
    int label;
    String posX;
    String posY;

    // Marker(String size, String posX, String posY, int label) {
    // this(size, posX, posY, "d", label);
    // }

    Marker(String size, String posX, String posY, String type, int label) {
        this.size = size;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.label = label;
    }

    @Override
    public String toString() {
        String ret = "&markers=type:" + type + "|size:" + size + "|pos:" + posX + "%20" + posY + "|label:"
                + Integer.toString(label);

        return ret;
    }
}
