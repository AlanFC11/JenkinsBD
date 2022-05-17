package com.itesm.azul.services;

import com.itesm.azul.dto.NoteDTO;
import com.itesm.azul.models.Note;
import com.itesm.azul.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;
    //CREATE
    public void createNote(final NoteDTO note){
        Note p = new Note();
        p.setNoteID(note.getNoteID());
        p.setStaffName(note.getStaffName());
        p.setText(note.getText());
        p.setTimestamp(note.getTimestamp());
        noteRepository.save(p);
    }

    //READ
    public Iterable<Note> lista(){
        return noteRepository.findAll();
    }
    public Iterable<Note> get(int noteID){
        return noteRepository.findNote(noteID);
    }
    //DELETE
    public void delete(int noteID){
        noteRepository.deleteById(noteID);
    }

    //PERSONALIZADO
    public Iterable<Note> staffName(String staffName){
        return noteRepository.staffName(staffName);
    }
    //UPDATE
    public Note update(NoteDTO dto){
        Note p = new Note();
        p.setNoteID(dto.getNoteID());
        p.setStaffName(dto.getStaffName());
        p.setText(dto.getText());
        p.setTimestamp(dto.getTimestamp());
        return noteRepository.update(p);
    }
    /*

    public boolean existsId(String personaID){
        return personaRepository.existsById(personaID);
    }



*/

}

