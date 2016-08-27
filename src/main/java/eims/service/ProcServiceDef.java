package eims.service;

import java.util.SortedSet;

public interface ProcServiceDef {
    
    public void makeSchedule(String cfCode);

    public void fastMenuGen(SortedSet<String> list);

    public void dummyUserData();
}
