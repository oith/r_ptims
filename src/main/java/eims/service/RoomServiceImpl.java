package eims.service;

import eims.model.acad.Room;
import eims.dto._SearchDTO;
import eims.exception.RoomNotFoundException;
import eims.repositories.RoomRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roomService")
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;



    @Transactional
    @Override
    public Room create(Room lookup) {
        return roomRepository.save(lookup);
    }

    @Override
    @Transactional
    public Room findById(BigInteger id) {
        Room room=roomRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return room;
    }

    @Override
    @Transactional(rollbackFor = RoomNotFoundException.class)
    public Room delete(BigInteger id) throws RoomNotFoundException {

        Room room = roomRepository.findOne(id);

        if (room == null) {
            throw new RoomNotFoundException();
        }
        roomRepository.delete(id);
        return room;
    }

    @Override
    @Transactional
    public Iterable<Room> findAll() {
        Iterable<Room> rooms=roomRepository.findAll();
        
        for (Room room : rooms) {

        //Hibernate.initialize(room.getA());
        //Hibernate.initialize(room.getZs());
        }
        
        return rooms;
    }

    @Transactional(rollbackFor = RoomNotFoundException.class)
    @Override
    public Room update(Room updated) throws RoomNotFoundException {

        Room room = roomRepository.findOne(updated.getId());

        if (room == null) {
            throw new RoomNotFoundException();
        }

        BeanUtils.copyProperties(updated, room);
        return roomRepository.save(room);
    }

    @Transactional(rollbackFor = RoomNotFoundException.class)
    @Override
    public Room copy(final Room copied) {

        Room room = new Room();
        BeanUtils.copyProperties(copied, room);
        room.setId(null);

        return roomRepository.save(room);
    }

    @Transactional
    @Override
    public Iterable<Room> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Room> search(_SearchDTO pageable) {
        return findAll();
    }
}