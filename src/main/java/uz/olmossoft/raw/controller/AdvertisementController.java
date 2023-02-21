package uz.olmossoft.raw.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.olmossoft.raw.dto.*;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;
import uz.olmossoft.raw.service.AdvertisementService;

import java.io.IOException;
import java.util.List;

/**
 * @author Muhammadkomil Murodillayev, ср 15:25. 1/11/23
 */

@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
    private final AdvertisementService service;

    public AdvertisementController(AdvertisementService service) {
        this.service = service;
    }

    @PostMapping(value = "/")
    public ResponseEntity<DataDTO<Long>> create(@ModelAttribute AdvertisementCreateDTO dto) {

        return service.create(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DataDTO<AdvertisementDTO>> get(@PathVariable Long id) {

        return service.get(id);
    }

    @GetMapping(value = "/")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAll(@RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        return service.getAll(page, size);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<DataDTO<List<AdvertisementDTO>>> getAllByFilter(@RequestBody AdvertisementFilterDTO filterDTO) {

        return service.getAllByFilter(filterDTO);
    }


    @PutMapping(value = "/")
    public ResponseEntity<DataDTO<Long>> update(@RequestBody AdvertisementUpdateDTO dto) {

        return service.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DataDTO<Long>> delete(@PathVariable Long id) {


        return service.delete(id);
    }

    @GetMapping("/photo/{fileName:.+}")
    public void downloadPhoto(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        service.downloadPhoto(request, response, fileName);
    }

    @PutMapping("/status/{id}")
    public void setStatus(@PathVariable Long id, @RequestParam Status status) {

        service.setStatus(id, status);

    }

    @PutMapping("/mod/status/{id}")
    public void setModStatus(@PathVariable Long id, @RequestParam(name = "modStatus") ModStatus status) {
        service.setModStatus(id, status);
    }

}
