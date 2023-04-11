package com.example.swplanetapi.domain;

import static com.example.swplanetapi.common.PlanetConstants.INVALID_PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANET;
import static com.example.swplanetapi.common.PlanetConstants.PLANETS;
import static com.example.swplanetapi.common.PlanetConstants.PLANET_EXISTS;
import static com.example.swplanetapi.common.PlanetConstants.PLANET_NOT_EXISTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.swplanetapi.web.PlanetController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
  @InjectMocks
  private PlanetService planetService;
  @InjectMocks
  private PlanetController planetController;

  @Mock
  private PlanetRepository planetRepository;

  @Test
  public void createPlanet_WithValidData_ReturnsPlanet() {
    when(planetRepository.save(PLANET)).thenReturn(PLANET);

    Planet sut = planetService.create(PLANET);

    assertThat(sut).isEqualTo(PLANET);
  }

  @Test
  public void createPlanet_WithInvalidData_ThrowsException() {
    when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

    assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() {
    when(planetRepository.findById(PLANET_EXISTS.getId())).thenReturn(PLANETS.stream().filter(pl -> pl.getId() == PLANET_EXISTS.getId()).findFirst());

    Optional<Planet> finding = planetService.get(PLANET_EXISTS.getId());

    assertThat(finding).isEqualTo(Optional.of(PLANET_EXISTS));
  }

  @Test
  public void getPlanet_ByUnexistingId_ReturnsEmpty() {
    when(planetRepository.findById(PLANET_NOT_EXISTS.getId())).thenReturn(PLANETS.stream().filter(pl -> pl.getId() == PLANET_NOT_EXISTS.getId()).findFirst());

    Optional<Planet> finding = planetService.get(PLANET_NOT_EXISTS.getId());

    assertThat(finding).isNotEqualTo(Optional.of(PLANET_NOT_EXISTS));
  }
}
