package hr.acquaint.hotelmanagement.controllers;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@Validated
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelData> getHotelsByPage(@RequestParam @Min(1) Long page,
                                           @RequestParam(required = false, name = "page_size") @Min(1) @Max(20) Long itemsPerPage) {
        return hotelService.getHotelsByPage(itemsPerPage, page);
    }

    @GetMapping("/{id}")
    public HotelData getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelData createHotel(@Valid @RequestBody HotelData newHotel) {
        return hotelService.createHotel(newHotel);
    }

    @PutMapping("/{id}")
    public HotelData updateHotel(@Valid @RequestBody HotelData newHotel, @PathVariable Long id) {
        return hotelService.updateHotel(newHotel, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
