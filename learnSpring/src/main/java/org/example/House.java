package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("House")
public class House {
    private long houseId;
    @Autowired
    private Door door;


    public House(){
        this.houseId = 101;
    }


    public House(long houseId){
        this.houseId = houseId;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Door getDoor(){
        return door;
    }
}
