package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID beerId) {
		
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle("Pale Ale")
				.build();
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		log.debug("Updating a beer...");
	}

	@Override
	public void deleteBeer(UUID beerId) {
		log.debug("Deleting a beer...");
	}

}
