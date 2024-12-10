import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/*
* {
  "posts": [
    {
      "id": "1",
      "secretString": "5",
      "specificField": "101920205",
      "winning-numbers": [
        2,
        45,
        34,
        23,
        7,
        5,
        3
      ],
      "money": 100000.22,
      "winners": [
        {
          "winnerId": 23,
          "name": "John",
          "money": 50000,
          "numbers": [
            2,
            45,
            34,
            23,
            3,
            5
          ]
        },
        {
          "winnerId": 54,
          "name": "Andrew",
          "money": 50000,
          "numbers": [
            53,
            3,
            12,
            11,
            18,
            22
          ]
        }
      ]
    }
  ]
}*/

import java.util.List;
import java.util.Map;

public class JsonPathTest {

    @Test
    public void checkSpecificFieldJsonPath() {
        Response response = RestAssured.get("http://localhost:3000/posts/1");

        String secretString = response.path("secretString"); //5
        List<Integer> winningNumbers = response.path("winning-numbers"); //7
        String firstWinnerName = response.path("winners.name[0]"); //John
        String firstWinnerName1 = response.path("winners[0].name"); //John
        String secondWinnerName = response.path("winners[1].name"); //Andrew
        String lastWinnerName = response.path("winners[-1].name"); //Andrew
        List<String> winnerNames = response.path("winners.name"); //2
        Map<String, ?> winner = response.path("winners[0]"); //4
        List<Map<String, ?>> winners = response.path("winners"); //2

        Map<String, ?> winnerInfo = response.path("winners.find {it.name=='Andrew'}");
        Integer winnerId = response.path("winners.find {it.name=='Andrew'}.winnerId"); //23
        Integer maxNumber = response.path("winning-numbers.max()"); //45
        Integer minNumber = response.path("winning-numbers.min()"); //2
        Map<String, ?> winnerMaxId = response.path("winners.max {it.winnerId}"); //
        Integer moneySum = response.path("winners.collect{it.money}.sum()"); //100000

        Integer winnerId2 = response.path("winners.find {it.name=='John'}.winnerId"); //23
        List<Integer> winnersId = response.path("winners.findAll {it.name=='John'}.winnerId");
    }
}
