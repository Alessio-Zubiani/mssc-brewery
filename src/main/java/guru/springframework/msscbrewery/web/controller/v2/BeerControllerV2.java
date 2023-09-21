package guru.springframework.msscbrewery.web.controller.v2;

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

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v2/beer")
@RestController
@RequiredArgsConstructor
public class BeerControllerV2 {
	
	private final BeerServiceV2 beerService;
	

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId) {
		
		return new ResponseEntity<BeerDtoV2>(this.beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createBeer(@RequestBody BeerDtoV2 beerDto) {
		
		BeerDtoV2 savedBeer = this.beerService.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/beer/".concat(savedBeer.getId().toString()));
		
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<Object> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto) {
		
		this.beerService.updateBeer(beerId, beerDto);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		
		this.beerService.deleteBeer(beerId);
	}
}
