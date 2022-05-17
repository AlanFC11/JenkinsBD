package com.itesm.azul.services;


import com.itesm.azul.dto.StaffDTO;
import com.itesm.azul.models.Staff;
import com.itesm.azul.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;
    //CREATE
    public void createStaff(final StaffDTO staff){
        Staff p = new Staff();
        p.setStaffID(staff.getStaffID());
        p.setActive(staff.getActive());
        p.setCreationDate(staff.getCreationDate());
        p.setEmail(staff.getEmail());
        p.setLastName(staff.getLastName());
        p.setName(staff.getName());
        p.setRole(staff.getRole());
        p.setCreatedBy(staff.getCreatedBy());
        p.setLastModified(staff.getLastModified());
        p.setStatus(staff.getStatus());
        p.setSupervisorID(staff.getSupervisorID());
        staffRepository.save(p);
    }

    //READ
    public Iterable<Staff> lista(){
        return staffRepository.findAll();
    }
    public Iterable<Staff> get(int staffID){
        return staffRepository.findStaff(staffID);
    }

    //DELETE
    public void delete(int staffID){
        staffRepository.deleteById(staffID);
    }

    //UPDATE
    public Staff update(StaffDTO dto){
        Staff p = new Staff();
        p.setStaffID(dto.getStaffID());
        p.setActive(dto.getActive());
        p.setCreationDate(dto.getCreationDate());
        p.setEmail(dto.getEmail());
        p.setLastName(dto.getLastName());
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setCreatedBy(dto.getCreatedBy());
        p.setLastModified(dto.getLastModified());
        p.setStatus(dto.getStatus());
        p.setSupervisorID(dto.getSupervisorID());
        return staffRepository.update(p);
    }
    /*

    public boolean existsId(String personaID){
        return personaRepository.existsById(personaID);
    }



*/

}
