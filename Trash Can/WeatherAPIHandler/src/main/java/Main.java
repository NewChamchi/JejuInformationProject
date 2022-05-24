/* API 사용 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {
        /* 현재 url 전송을 위한 인코딩 부분에서 변수화되어야하는 것
         * 1. 시간 (base_date, base_time)
         * 2. 좌표 (nx, ny) -> 이거 위도 경도가 아니라 기상청에서 쓰는 자체 좌표임
         * 따로 함수를 떼서 만들어야할듯
         */
        String base_date = "20220512";
        String base_time = "0500";
        String base_nx = "55";
        String base_ny = "127";

        StringBuilder sb = Main.ApiExplorer(base_date, base_time, base_nx, base_ny); // api json 파일 호출
        List<Map<String, Object>> jsonToList = Main.WeatherDataParsing(sb.toString()); // json 파일을 자바 자료형으로 parsing하는 함수. 아래에 함수 정의

        System.out.println((String) jsonToList.get(2).get("category")); // 아래와 같은 형태로 데이터의 키값에 맞는 밸류를 찾을 수 있음

        ConvertGridGPS convertGridGPS = new ConvertGridGPS();
        convertGridGPS.ConvertCalculator(1, 55,127); // 모드에서 0을 넣으면 x, y를 를 위도와 경도로 바꾸고, 1은 반대임
        double[] latlng = convertGridGPS.latXlngY(1); // 0은 x, y값, 1은 위도 경도 값
        System.out.println("lat = " + latlng[0] + " lng = " + latlng[1]);

    }

    public static List<Map<String, Object>> WeatherDataParsing(String jsonString)
    {
        /*
         * 1. json-simple는 google에서 제공해주는 json사용 라이브러리 입니다
         * 2. jsonObject.put(key, value); 형태로 데이터를 삽입합니다
         * 3. jsonObjectParse.get(key); 형태로 데이터를 추출합니다
         * 4. jsonArray.add(value); 형태로 데이터를 삽입합니다
         * 5. jsonArray.get(배열 번지); 형태로 데이터를 추출합니다
         * 6. JSONParser 는 json 데이터 파싱을 도와주는 객체입니다
         * */
        JSONObject parsedData = new JSONObject();
        JSONArray parsedItemArray = new JSONArray();
        JSONParser parser = new JSONParser();

        // json을 list에 맵핑하기 위한 객체 선언
        List<Map<String, Object>> tmpList = new ArrayList<>();

        // try-catch문을 쓸 때 유의점! 안 자체로 지역 취급이 되기 때문에 선언은 밖에서 해줘야 다른 지역에서도 인식을 함!
        try {
            /*
             * 과정이 되게 귀찮음. 처음 json으로 받은 데이터를 파싱하면, 그 이후 item이 있는 층까지 계쏙 jsonObject를 생성하면서 내려가야 함.
             * 그 이후 item에 배열이 있는데 거기서 jsonArray로 형태를 바꿔서 데이터를 확인할 수 있음
             */
            parsedData = (JSONObject) parser.parse(jsonString);
            JSONObject parsedResponse = (JSONObject) parsedData.get("response");
            JSONObject parsedBody = (JSONObject) parsedResponse.get("body");
            JSONObject parsedItems = (JSONObject) parsedBody.get("items");
            parsedItemArray = (JSONArray) parsedItems.get("item");
//            System.out.println(parsedItemArray);
        }
        catch(ParseException pe) { // 파싱 오류 발생 시 예외처리
            System.out.println("파싱 오류 발생");
        }
        for (int i = 0; i < parsedItemArray.size(); i++ ) {
            JSONObject tmp = (JSONObject) parsedItemArray.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("baseDate", tmp.get("baseDate"));
            map.put("fcstTime", tmp.get("fcstTime"));
            map.put("fcstValue", tmp.get("fcstValue"));
            map.put("nx", tmp.get("nx"));
            map.put("ny", tmp.get("ny"));
            map.put("category", tmp.get("category"));
            map.put("baseTime", tmp.get("baseTime"));
            map.put("fcstDate", tmp.get("fcstDate"));
//            System.out.println(map);
            tmpList.add(map);
        }

//        System.out.println(tmpList);
        return tmpList;
    }

    public static StringBuilder ApiExplorer(String base_date, String base_time, String base_nx, String base_ny) throws IOException
    {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* 단기 예보 URL */
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=keO1O1o%2BHwjVHWutn1ksAYyAQAxzOOf0ZwxmjL0Vsn7F9euPgIFH%2F%2Fh5ObjoGYZaZ1h%2Boyfww7nABXbaJOMoIA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘22년 5월 12일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(base_nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(base_ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { // 200이랑 300은 메세지 결과를 말하는 거임 데이터 개수랑은 상관 X
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb;
    }
}