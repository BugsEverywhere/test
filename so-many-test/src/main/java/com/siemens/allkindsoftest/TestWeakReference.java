package com.siemens.allkindsoftest;

import java.lang.ref.WeakReference;

/**
 * Created by Simons on 2017/5/30.
 */
public class TestWeakReference {

    public static void main(String[] args) throws InterruptedException {

        Person person = new Person("silver",1);
        WeakReference<Person> weakPerson = new WeakReference<Person>(person);

        //person = null;

        System.gc();

        System.gc();

        int i=0;

        while(true){
            Thread.sleep(1);
            if(weakPerson.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakPerson);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }
}

class Person {

    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
