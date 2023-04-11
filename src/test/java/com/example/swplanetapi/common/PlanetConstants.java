package com.example.swplanetapi.common;

import com.example.swplanetapi.domain.Planet;

import java.util.Arrays;
import java.util.List;

public class PlanetConstants {
  public static final Planet PLANET = new Planet("name", "climate", "terrain");
  public static final Planet INVALID_PLANET = new Planet("", "", "");
  public static final List<Planet> PLANETS = Arrays.asList(new Planet(1l,"name1", "climate1", "terrain1"), new Planet(2l,"name2", "climate2", "terrain2"), new Planet(3l,"name3", "climate3", "terrain3"));
  public static final Planet PLANET_EXISTS = new Planet(1l,"name1", "climate1", "terrain1");
  public static final Planet PLANET_NOT_EXISTS = new Planet(4l,"name4", "climate4", "terrain4");
}
