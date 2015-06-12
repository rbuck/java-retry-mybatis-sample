package com.github.rbuck.retry.samples;

import com.github.rbuck.retry.samples.dao.PersonDao;
import com.github.rbuck.retry.samples.pojo.Person;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random(31);

    private static String createRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String args[]) {

        // load spring config file...
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/github/rbuck/retry/samples/mybatis/spring-config.xml");

        // get session factory...
        DefaultSqlSessionFactory sessionFactory = (DefaultSqlSessionFactory) ctx.getBean("sqlSessionFactory");

        // get dao...
        PersonDao personDAO = (PersonDao) ctx.getBean("personDao"); // new PersonDao(sessionFactory);

        while (true) {
            try {

                //create person bean to insert
                Person person = new Person();

                // insert a person
                person.setName(createRandomString(20));
                int id1 = personDAO.insert(person);

                // insert another person
                person.setName(createRandomString(20));
                int id2 = personDAO.insert(person);

                // select the second person by id...
                Person person2 = personDAO.selectById(id2);

                // select all persons...
                List<Person> persons = personDAO.selectAll();

                // update all persons...
                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).setName(createRandomString(20) + "_" + i);
                    personDAO.update(persons.get(i));
                }

                // select all persons...
                persons = personDAO.selectAll();

                for (Person p : persons) {
                    System.out.println(p);
                }
            } catch (Error e) {
                throw e;
            } catch (Throwable t) {
                // nada
            }
        }
    }
}
