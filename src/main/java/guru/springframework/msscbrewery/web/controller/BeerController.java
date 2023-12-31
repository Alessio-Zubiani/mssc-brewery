package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
public class BeerController {
	
	private final BeerService beerService;
	

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		
		return new ResponseEntity<BeerDto>(this.beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createBeer(@RequestBody BeerDto beerDto) {
		
		BeerDto savedBeer = this.beerService.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/beer/".concat(savedBeer.getId().toString()));
		
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<Object> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		
		this.beerService.updateBeer(beerId, beerDto);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		
		this.beerService.deleteBeer(beerId);
	}
}
