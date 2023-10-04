package com.example.demo.service;

import com.example.demo.domain.Spot;
import com.example.demo.repository.SpotRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SpotService {

  @Autowired
  private SpotRepository spotRepository;

  public void DataAndSaveToDB() throws IOException {

    try {
      String apiUrl = "https://apis.data.go.kr/B551011/KorService1/detailCommon1?" +
              "serviceKey=bx85ZQyXZ%2BzD6Aah4J9Bz7yZ55piQrVEGw0378dm6fkhBh6mQIFwhoQTcpjUKSbZ3NVfcN%2BtdmU2RHEpNpcIzg%3D%3D" +
              "&MobileOS=ETC" +
              "&MobileApp=AppTest" +
              "&_type=json" +
              "&contentId=" +
              "&contentTypeId=12" +
              "&defaultYN=Y" +
              "&firstImageYN=Y" +
              "&areacodeYN=Y" +
              "&catcodeYN=Y" +
              "&addrinfoYN=Y" +
              "&mapinfoYN=Y" +
              "&overviewYN=Y" +
              "&numOfRows=10" +
              "&pageNo=1";

      URL url = new URL(apiUrl);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setRequestMethod("GET");

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

      StringBuilder result = new StringBuilder();

      String returnLine;
      while ((returnLine = bufferedReader.readLine()) != null) {
        result.append(returnLine).append("\n");
      }
      urlConnection.disconnect();

      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(result.toString()).get("response").get("body").get("items").get("item");

      for (JsonNode node : rootNode) {
        JsonNode titleNode = node.get("title");
        JsonNode addressNode = node.get("addr1");
        JsonNode overviewNode = node.get("overview");

        if (titleNode != null && addressNode != null && overviewNode != null) {
          Spot spot = new Spot();
          spot.setSpotName(titleNode.asText());
          spot.setAddress(addressNode.asText());

          // 최대 길이를 255로 가정하고 설정
          String description = overviewNode.asText();
          int maxLength = 255;
          if (description.length() > maxLength) {
            description = description.substring(0, maxLength);
          }
          spot.setDescription(description);


          spotRepository.save(spot);
          System.out.println("Spot saved: " + spot.getSpotName());
        } else {
          System.out.println("One or more required fields are missing for this spot. Skipping...");
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  public List<Spot> getAllSpots() {
    return spotRepository.findAll();
  }


}
