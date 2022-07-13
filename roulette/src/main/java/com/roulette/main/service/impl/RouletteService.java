package com.roulette.main.service.impl;

import java.util.Map;

public interface RouletteService {

  //룰렛 Impl 정의
  Map<String, Object> getRoulette(int tryCount, int winPercent);

  //상품뽑기 Impl 불러오기
  Map<String, Object> getPrize(int tryCount);
}
