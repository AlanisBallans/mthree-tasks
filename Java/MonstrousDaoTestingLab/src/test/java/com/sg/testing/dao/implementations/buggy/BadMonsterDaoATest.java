package com.sg.testing.dao.implementations.buggy;

import com.sg.testing.dao.MonsterDao;
import com.sg.testing.model.Monster;
import com.sg.testing.model.MonsterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadMonsterDaoATest {
    private MonsterDao testDao;

    @BeforeEach
    void setUp() {
        testDao = new BadMonsterDaoA();
    }

    @Test
    void testAddGetMonster() {
        Monster monster = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(1);
        monster.setFavoriteFood("Cheese");
        int id = 1;

        testDao.addMonster(id, monster);
        Monster retrievedMonster = testDao.getMonster(id);

        assertEquals(monster, retrievedMonster, "Checking the monster has been added to the dao");
    }

    @Test
    void testAddGetMonsterCorrectException() {
        Monster monster = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(1);
        monster.setFavoriteFood("Cheese");
        int id = 0;

        testDao.addMonster(id, monster);
        Monster retrievedMonster = testDao.getMonster(id);

        assertEquals(monster, retrievedMonster, "Checking the monster has been added to the dao in the" +
                "expected manner, where it can only be retrieved because its id is 0");
    }

    @Test
    void getAllMonsters() {
        Monster monster = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(1);
        monster.setFavoriteFood("Cheese");
        int id = 1;

        testDao.addMonster(id, monster);

        Monster monster2 = new Monster();
        monster.setName("Claretta");
        monster.setType(MonsterType.VAMPIRE);
        monster.setPeopleEaten(5000);
        monster.setFavoriteFood("Blood");
        id = 2;

        testDao.addMonster(id, monster2);

        List<Monster> allMonsters = testDao.getAllMonsters();

        assertEquals(2, allMonsters.size());
        assertTrue(allMonsters.contains(monster), "Checking the list includes Ciwa");
        assertTrue(allMonsters.contains(monster2), "Checking the list includes Claretta");

    }

    @Test
    void testUpdateMonster() {
        Monster monster = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(1);
        monster.setFavoriteFood("Cheese");
        int id = 1;

        testDao.addMonster(id, monster);

        Monster monsterEdit = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(2);
        monster.setFavoriteFood("Freedom");

        testDao.updateMonster(id, monsterEdit);

        Monster retrievedMonster = testDao.getMonster(id);
        assertNotEquals(monster, retrievedMonster, "Checking Ciwa is no longer the monster under id 1");
        assertEquals(monsterEdit, retrievedMonster, "Checking Claretta has been made the monster under id 1");
    }

    @Test
    void removeMonster() {
        Monster monster = new Monster();
        monster.setName("Ciwa");
        monster.setType(MonsterType.WEREWOLF);
        monster.setPeopleEaten(1);
        monster.setFavoriteFood("Cheese");
        int id = 1;

        testDao.addMonster(id, monster);

        Monster monster2 = new Monster();
        monster.setName("Claretta");
        monster.setType(MonsterType.VAMPIRE);
        monster.setPeopleEaten(5000);
        monster.setFavoriteFood("Blood");
        id = 2;

        testDao.addMonster(id, monster2);

        Monster removedMonster = testDao.removeMonster(1);
        assertEquals(monster, removedMonster);
    }
}