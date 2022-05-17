package com.itesm.azul.controllers;

import com.itesm.azul.dto.NoteDTO;
import com.itesm.azul.models.Note;
import com.itesm.azul.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    //https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html
    //Create
    //insert a tuple
    @PostMapping("/save")
    public NoteDTO save(@RequestBody NoteDTO note) throws Exception{
        noteService.createNote(note);
        return note;
    }
    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Note>> getAll(){
        return ResponseEntity.ok(noteService.lista());
    }

    //Read
    //select 1 tuple
    @GetMapping("/get/{noteID}")
    public ResponseEntity<Iterable<Note>> getOne(@PathVariable("noteID") int noteID){
        //if(!personaService.existsId(personaID))
        //    return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(noteService.get(noteID));
    }


    //Delete a tuple
    @DeleteMapping("/delete/{noteID}")
    public ResponseEntity<?> delete(@PathVariable("noteID") int noteID){
        /*if(!personaService.existsId(personaID))
            return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);

         */
        noteService.delete(noteID);
        return new ResponseEntity("note eliminada", HttpStatus.OK);
    }
    //Personalizado
    @GetMapping("/staffName/{staffName}")
    public ResponseEntity<Iterable<Note>> staffName(@PathVariable("staffName") String staffName){

        return ResponseEntity.ok(noteService.staffName(staffName));
    }

    //Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Note> update(@RequestBody NoteDTO noteDTO){
       /* if(!personaService.existsId(personaDTO.getPersonaID()))
            return new ResponseEntity("no existe, no se puede actualizar", HttpStatus.NOT_FOUND);

        */
        return ResponseEntity.ok(noteService.update(noteDTO));
    }
}
