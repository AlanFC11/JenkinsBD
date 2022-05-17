package com.itesm.azul.repositories;

import com.itesm.azul.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class NoteRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public NoteRepository(){
        super();
    }

    public void save(final Note note) {
        DynamoDbTable<Note> noteTable = getTable();
        noteTable.putItem(note);
    }

    /**
     * @return
     */
    private DynamoDbTable<Note> getTable() {
        DynamoDbTable<Note> noteTable = dynamoDbEnhancedClient.table("Notes", TableSchema.fromBean(Note.class));
        return noteTable;
    }
    public Iterable<Note> findAll() {
        DynamoDbTable<Note> noteTable = getTable();
        return noteTable.scan().items();
    }

    public Iterable<Note> findNote(final int noteID) {
        DynamoDbTable<Note> noteTable = getTable();


        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(noteID)
                        .build());

        Iterable<Note> result = noteTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

//Personalizado
public Iterable<Note> staffName(final String staffName) {
    List<Note> listaNuevaNota = new ArrayList<>();
    Iterable<Note> iterableNuevaNota;
    DynamoDbIndex<Note> SGIStaffID =
            dynamoDbEnhancedClient.table("Notes",
                            TableSchema.fromBean(Note.class))
                    .index("staffName-index");
    // Create a QueryConditional object that is used in the query operation
    QueryConditional queryConditional = QueryConditional
            .keyEqualTo(Key.builder().partitionValue(staffName)
                    .build());

    PageIterable<Note> items = null;
    items = (PageIterable<Note>) SGIStaffID.query(r -> r.queryConditional(queryConditional));
    items.forEach(flowPage -> {
        listaNuevaNota.addAll(flowPage.items());
    });
    iterableNuevaNota = listaNuevaNota;

    return iterableNuevaNota;

}
    public Note update(final Note nuevaNote){
        DynamoDbTable<Note> noteTable = getTable();
        Key key = Key.builder().partitionValue(nuevaNote.getNoteID()).build();
        Note registroNuevaNote = noteTable.getItem(r->r.key(key));
        registroNuevaNote.setText(nuevaNote.getText());
        registroNuevaNote.setStaffName(nuevaNote.getStaffName());
        registroNuevaNote.setTimestamp(nuevaNote.getTimestamp());
        noteTable.updateItem(registroNuevaNote);
        return nuevaNote;

    }

    public void deleteById(final int noteID) {
        DynamoDbTable<Note> noteTable = getTable();

        Key key = Key.builder().partitionValue(noteID).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        noteTable.deleteItem(deleteRequest);
    }
}