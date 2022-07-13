package com.roulette.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RouletteServiceImpl implements RouletteService {

  // 룰렛 들어올때 정의 매퍼필요없음
  @Override
  public Map<String, Object> getRoulette(int tryCount, int winPercent) {
    System.out.println("서비스 들어온거임.");
    Map<String, Object> map = new HashMap<String, Object>();

    ArrayList<String> list = new ArrayList<String>();

    String[] prize = {"당첨", "미당첨"};
    String[] prizeName = new String[tryCount];
    int winCount = 0;

    for (int j = 0; j < tryCount; j++) {
      int luckyNum = (int) (Math.random() * 100) + 1;
      if (luckyNum <= winPercent) {
        winCount++;
        prizeName[j] = prize[0];
        list.add(prizeName[j]);
      } else if (luckyNum > winPercent) {
        prizeName[j] = prize[1];
        list.add(prizeName[j]);
      }
    }
    double probability = Math.round(((double) winCount / tryCount) * 100);

    map.put("probability", probability);
    map.put("winCount", winCount);
    map.put("tryCount", tryCount);
    map.put("winPercent", winPercent);
    map.put("list", list);

    return map;
  }

  @Override
  public Map<String, Object> getPrize(int tryCount) {
    Map<String, Object> map = new HashMap<String, Object>();
    // 몇회 등장했는지. 값만
    ArrayList<Integer> countList = new ArrayList<Integer>();
    // tryCount의 횟수에 따라 상품 값이 담김
    ArrayList<String> prizeList = new ArrayList<String>();
    // 상품값의 퍼센트가 담김
    ArrayList<String> percentList = new ArrayList<String>();

    int[] percent = {5, 10, 15, 70};
    String[] prize = {"1등", "2등", "3등", "꽝"};
    int[] equalCount = new int[prize.length];
    String[] prizeName = new String[tryCount];
    int sum = 0;

    // 합계구하기
    for (int i = 0; i < percent.length; i++) {
      sum = sum + percent[i];
    }

    for (int i = 0; i < tryCount; i++) {
      int percent_random = (int) ((Math.random() * sum) + 1);
      for (int j = 0; j < percent.length; j++) {
        percent_random -= percent[j];
        if (percent_random <= 0) {
          prizeName[i] = prize[j];
          prizeList.add(prizeName[i]);
          break;
        }
      }
      for (int j = 0; j < equalCount.length; j++) {
        int count = 0;
        if (prize[j].equals(prizeName[i])) {
          count++;
        }
        equalCount[j] = equalCount[j] + count;
      }
    }
    for (int i = 0; i < equalCount.length; i++) {
      float realPercent = ((float) equalCount[i] / tryCount) * 100;
      percentList.add(String.format("%.1f", realPercent));
      countList.add(equalCount[i]);
    }
    map.put("tryCount", tryCount);
    map.put("countList", countList);
    map.put("percentList", percentList);
    map.put("prize", prize);
    map.put("prizeList", prizeList);
    map.put("percent", percent);

    return map;
  }

}
