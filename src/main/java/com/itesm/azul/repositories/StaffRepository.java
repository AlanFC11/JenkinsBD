package com.itesm.azul.repositories;

import com.itesm.azul.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class StaffRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public StaffRepository(){
        super();
    }

    public void save(final Staff staff) {
        DynamoDbTable<Staff> staffTable = getTable();
        staffTable.putItem(staff);
    }

    /**
     * @return
     */
    private DynamoDbTable<Staff> getTable() {
        DynamoDbTable<Staff> staffTable = dynamoDbEnhancedClient.table("Staff", TableSchema.fromBean(Staff.class));
        return staffTable;
    }
    public Iterable<Staff> findAll() {
        DynamoDbTable<Staff> staffTable = getTable();
        return staffTable.scan().items();
    }

    public Iterable<Staff> findStaff(final int staffID) {
        DynamoDbTable<Staff> staffTable = getTable();


        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(staffID)
                        .build());

        Iterable<Staff> result = staffTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

    public Staff update(final Staff staff){
        DynamoDbTable<Staff> staffTable = getTable();
        Key key = Key.builder().partitionValue(staff.getStaffID()).build();
        Staff registroStaff = staffTable.getItem(r->r.key(key));
        registroStaff.setActive(staff.getActive());
        registroStaff.setEmail(staff.getEmail());
        registroStaff.setLastName(staff.getLastName());
        registroStaff.setName(staff.getName());
        registroStaff.setPassword(staff.getPassword());
        registroStaff.setRole(staff.getRole());
        registroStaff.setCreatedBy(staff.getCreatedBy());
        registroStaff.setLastModified(staff.getLastModified());
        registroStaff.setStatus(staff.getStatus());
        registroStaff.setSupervisorID(staff.getSupervisorID());
        staffTable.updateItem(registroStaff);
        return staff;

    }
    public void deleteById(final int staffID) {
        DynamoDbTable<Staff> staffTable = getTable();

        Key key = Key.builder().partitionValue(staffID).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        staffTable.deleteItem(deleteRequest);
    }
}
