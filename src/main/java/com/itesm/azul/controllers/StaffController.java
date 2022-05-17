package com.itesm.azul.controllers;


import com.itesm.azul.dto.StaffDTO;
import com.itesm.azul.models.Staff;
import com.itesm.azul.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html
    //Create
    //insert a tuple
    @PostMapping("/save")
    public StaffDTO save(@RequestBody StaffDTO staff) throws Exception{
        staffService.createStaff(staff);
        return staff;
    }
    //Read
    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Staff>> getAll(){
        return ResponseEntity.ok(staffService.lista());
    }

    //select 1 tuple
    @GetMapping("/get/{staffID}")
    public ResponseEntity<Iterable<Staff>> getOne(@PathVariable("staffID") int staffID){
        //if(!personaService.existsId(personaID))
        //    return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(staffService.get(staffID));
    }


    //Delete a tuple
    @DeleteMapping("/delete/{staffID}")
    public ResponseEntity<?> delete(@PathVariable("staffID") int staffID){
        /*if(!personaService.existsId(personaID))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);

         */
        staffService.delete(staffID);
        return new ResponseEntity("staff eliminado", HttpStatus.OK);
    }


    //Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Staff> update(@RequestBody StaffDTO staffDTO){
       /* if(!personaService.existsId(personaDTO.getPersonaID()))
            return new ResponseEntity("no existe, no se puede actualizar", HttpStatus.NOT_FOUND);

        */
        return ResponseEntity.ok(staffService.update(staffDTO));
    }
}
