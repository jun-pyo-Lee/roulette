package com.roulette.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roulette.dto.RouletteDTO;


@Controller
public class RouletteController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/home")
	public String home() {
	      return "home";
	}
	
	@RequestMapping("/rouletteSetting")
	public String rouletteSetting() {
	      return "rouletteSetting";
	}
	
	@RequestMapping("prizeSetting")
	public String prizeSetting() {
		return "prizeSetting";
	}
	
	@RequestMapping("/rouletteView")
	public String rouletteView(RouletteDTO rouletteDto, Model model) {
		// 독립시행
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<String> list = new ArrayList<String>();
		
		String[] prize = {"당첨","미당첨"};
		int winPercent = rouletteDto.getWinPercent();
		int tryCount = rouletteDto.getTryCount();
		String[] prizeName  = new String[tryCount];
		int winCount = 0;
		
		
		for (int j = 0; j < tryCount; j++) {
			int luckyNum = (int)(Math.random()*100)+1;
			for (int i = 0; i < prize.length-1; i++) {
				if (luckyNum <= winPercent) {
					winCount++;
					prizeName[j] = prize[i];
					list.add(prizeName[j]);
				} else if (luckyNum > winPercent){
					prizeName[j] = prize[i+1];
					list.add(prizeName[j]);
				}
			}
		}
		double probability = Math.round(((double)winCount/tryCount)*100);
		
		map.put("probability", probability);
		map.put("winCount", winCount);
		map.put("tryCount",tryCount);
		map.put("winPercent", winPercent);
		map.put("list", list);
		model.addAttribute("map", map);
		return "rouletteView";
	}
	
	@RequestMapping("prizeView")
	public String prizeView(RouletteDTO rouletteDto, Model model) {
		// 가중치랜덤
		Map<String, Object> map = new HashMap<String, Object>();
		// 몇회 등장했는지. 값만
		ArrayList<Integer> countList = new ArrayList<Integer>();
		// tryCount의 횟수에 따라 상품 값이 담김
		ArrayList<String> posList = new ArrayList<String>();
		// 상품값의 퍼센트가 담김
		ArrayList<Integer> percentList = new ArrayList<Integer>();
		
		String[] prize = {"1등","2등","3등","꽝"};
		int[] percent = {5,10,15,70};
		int max=IntStream.of(percent).sum();
		int min=0;
		int pos =0;
		int tryCount = rouletteDto.getTryCount();
		
		
		// 시행횟수
		for (int i = 0; i < tryCount; i++) {
			// 랜덤 번호 생성
			int percent_random = (int) ((Math.random() * (max - min)) + 1);
			// 가중치 이용
			for (int j = 0; j <= percent.length; j++) {
				percent_random -= percent[j];
				if (percent_random <= 0) {
					pos = j;
					posList.add(prize[pos]);
					break;
				}
			}
		}
		
		// 각 횟수들 저장, 퍼센트로 변경
		for (int i = 0; i < prize.length; i++) {
			// 횟수 저장
			countList.add(Collections.frequency(posList, prize[i]));
			// 각 횟수들 / 전체 시행 횟수 저장
			percentList.add((int) Math.round(((double) Collections.frequency(posList, prize[i])/tryCount)*100));
		}
		
		map.put("tryCount",tryCount);
		map.put("countList", countList);
		map.put("percentList", percentList);
		map.put("prize", prize);
		map.put("posList",posList);
		map.put("percent", percent);
		model.addAttribute("map", map);
		
		return "prizeView";
	}
}
