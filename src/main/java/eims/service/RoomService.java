package eims.service;

import eims.model.acad.Room;
import eims.exception.RoomNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface RoomService {



    public Room findById(BigInteger id);
    
    public Room create(Room room);
    
    public Room update(Room room) throws RoomNotFoundException;
    
    public Room copy(Room room);
    
    public Room delete(BigInteger id) throws RoomNotFoundException;
   
    public Iterable<Room> search(_SearchDTO pageable);
    
    public Iterable<Room> findAll(_SearchDTO pageable);
    
    public Iterable<Room> findAll();
}