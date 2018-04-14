package com.siemens.allkindsoftest.GenericTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2017/9/13.
 */
public class AnimalTest {

    public static void main(String[] args){

        List<Dog> dogs = new ArrayList<>();

        List<? extends Animal> animals = new ArrayList<>();

        List<? super Animal> bigAnimals = new ArrayList<>();

        //animals.add(new Dog());

        bigAnimals.add(new Animal());

        Generic<Dog> dogGeneric = new Generic<>();

        Class<Dog> dogClass = Dog.class;

        //variantTest(dogClass);

        List<Object> objects = new ArrayList<>();

        objects.addAll(bigAnimals);



    }

    public static void variantTest(Class<Animal> objectList){






    }

}
