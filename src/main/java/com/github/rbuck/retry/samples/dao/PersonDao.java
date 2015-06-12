package com.github.rbuck.retry.samples.dao;

import com.github.rbuck.retry.samples.pojo.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonDao {

    private SqlSessionFactory sqlSessionFactory = null;

    public PersonDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Person instances from the database.
     *
     * @return the list of all Person instances from the database.
     */
    public List<Person> selectAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Person> list = session.selectList("Person.selectAll");
            System.out.println("selectAll() --> " + list);
            return list;
        }
    }

    /**
     * Select instance of Person from the database.
     *
     * @param id the instance to be persisted.
     */
    public Person selectById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Person person = session.selectOne("Person.selectById", id);
            System.out.println("selectById(" + id + ") --> " + person);
            return person;
        }
    }

    /**
     * Insert an instance of Person into the database.
     *
     * @param person the instance to be persisted.
     */
    public int insert(Person person) {
        int id = -1;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            id = session.insert("Person.insert", person);
            session.commit();

            System.out.println("insert(" + person + ") --> " + person.getId());
            return id;
        }
    }

    /**
     * Update an instance of Person into the database.
     *
     * @param person the instance to be persisted.
     */
    public void update(Person person) {
        int id = -1;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            id = session.update("Person.update", person);
            session.commit();

            System.out.println("update(" + person + ") --> updated");
        }
    }

    /**
     * Delete an instance of Person from the database.
     *
     * @param id value of the instance to be deleted.
     */
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("Person.delete", id);
            session.commit();

            System.out.println("delete(" + id + ")");
        }
    }
}