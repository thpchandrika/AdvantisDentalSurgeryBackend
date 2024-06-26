package cs489.apsd.dentalsurgeryapp.controller;

import cs489.apsd.dentalsurgeryapp.dto.ResponseDto;
import cs489.apsd.dentalsurgeryapp.dto.dentist.DentistRequest;
import cs489.apsd.dentalsurgeryapp.exceptions.DentistNotFoundException;
import cs489.apsd.dentalsurgeryapp.service.DentistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dentist", description = "a dentist api")
@RestController
@RequestMapping("/api/ads/v1/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping(value = {"", "/"})
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> getAllDentist(){
        var dentistResponse = dentistService.getAllDentist();
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> getDentistById(@PathVariable Integer id) throws DentistNotFoundException {
        var dentistResponse = dentistService.getDentistById(id);
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    @PreAuthorize("hasAuthority('OFFICE_MANAGER')")
    public ResponseEntity<ResponseDto> registerDentist(@RequestBody DentistRequest request){
        var dentistResponse = dentistService.addDentist(request);
        var response = new ResponseDto(true, dentistResponse, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
