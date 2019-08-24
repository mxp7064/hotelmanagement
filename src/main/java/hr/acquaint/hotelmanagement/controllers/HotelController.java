package hr.acquaint.hotelmanagement.controllers;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.services.IHotelService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * REST controller that handles hotel CRUD http endpoints
 */
@RestController
@RequestMapping("/hotels")
@Validated
public class HotelController {

    private final IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * Endpoint to get hotels by page and page size
     *
     * @param page     page number
     * @param pageSize page size
     * @return list of hotel data objects
     */
    @GetMapping
    public List<HotelData> getHotelsByPage(@RequestParam @Min(1) Long page,
                                           @RequestParam(required = false, name = "page_size") @Min(1) @Max(20) Long pageSize) {
        return hotelService.getHotelsByPage(pageSize, page);
    }

    /**
     * Endpoint to get hotel by id
     *
     * @param id hotel id
     * @return hotel data object
     */
    @GetMapping("/{id}")
    public HotelData getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    /**
     * Endpoint to create new hotel
     *
     * @param hotelData hotel data object
     * @return created hotel data object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelData createHotel(@Valid @RequestBody HotelData hotelData) {
        return hotelService.createHotel(hotelData);
    }

    /**
     * Endpoint to update existing hotel
     *
     * @param hotelData hotel data object
     * @param id        hotel id
     * @return updated hotel data object
     */
    @PutMapping("/{id}")
    public HotelData updateHotel(@Valid @RequestBody HotelData hotelData, @PathVariable Long id) {
        return hotelService.updateHotel(hotelData, id);
    }

    /**
     * Endpoint to delete existing hotel
     *
     * @param id hotel id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
