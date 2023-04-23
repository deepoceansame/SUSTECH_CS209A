package org.example;


import org.springframework.stereotype.Component;

@Component
public class Door {
    private long doorId;

    public Door(){
        this.doorId = 101;
    }

    public Door(long doorId){
        this.doorId = doorId;
    }

    public void setDoorId(long doorId) {
        this.doorId = doorId;
    }

    public long getDoorId() {
        return doorId;
    }
}
