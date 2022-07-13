package com.roulette.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roulette.main.service.impl.RouletteService;

@Controller
public class RouletteController {

  @Autowired
  private RouletteService rouletteService;

  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = {"", "/home"})
  public String home() {
    System.out.println("home 작동");
    return "home";
  }

  @RequestMapping(value = "/rouletteSetting")
  public String rouletteSetting() {
    System.out.println("rouletteSetting 작동");
    return "rouletteSetting";
  }

  @RequestMapping(value = "/prizeSetting")
  public String prizeSetting() {
    System.out.println("prizeSetting 작동");
    return "prizeSetting";
  }

  @RequestMapping(value = "/rouletteView")
  public void rouletteView(int tryCount, int winPercent, Model model) {
    Map<String, Object> map = rouletteService.getRoulette(tryCount, winPercent);
    model.addAttribute("map", map);
  }

  @RequestMapping(value = "/prizeView")
  public void prizeView(int tryCount, Model model) {
    Map<String, Object> map = rouletteService.getPrize(tryCount);
    model.addAttribute("map", map);
  }

  @RequestMapping(value = "/errorPage")
  public String errorPage() {
    return "errorPage";
  }
}
