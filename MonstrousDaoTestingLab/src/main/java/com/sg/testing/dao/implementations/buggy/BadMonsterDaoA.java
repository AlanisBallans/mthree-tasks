/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.sg.testing.dao.implementations.buggy;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.model.Monster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    The issue with this implementation is that addMonster passes in a parameter i, but puts
    the monster in the HashMap with the key id, which is an uninitialised member variable.
    As such, all monsters are put at the default int value, 0. Therefore, if you add a
    monster where i != 0, and try to get it (the get function does use the passed in
    parameter correctly), the monster is not found.

 */

public class BadMonsterDaoA implements MonsterDao {

    Map<Integer, Monster> monsters = new HashMap<>();
    int id;
    
    @Override
    public Monster addMonster(int i, Monster m) {
        return monsters.put(id, m);
    }

    @Override
    public Monster getMonster(int id) {
       Monster m = monsters.get(id);
       return m;
    }

    @Override
    public List<Monster> getAllMonsters() {
        List<Monster> monsterList = new ArrayList<>();
        monsterList.addAll(monsters.values());
        return monsterList;
    }

    @Override
    public void updateMonster(int id, Monster m) {
        monsters.replace(id, m);
    }

    @Override
    public Monster removeMonster(int id) {
        Monster m = monsters.remove(id);
        return m;
    }
    
}
