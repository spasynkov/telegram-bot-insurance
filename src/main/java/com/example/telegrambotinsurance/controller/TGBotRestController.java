package com.example.telegrambotinsurance.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class TGBotRestController {

    @RequestMapping("/rest")
    @ResponseBody
    public Date rest(){
        return new Date();
//    public List<Dog> rest() {
//    public Dog[] rest() {
//        Dog dog = new Dog();
//        dog.dogSetFactory();
//        Dog[] set = dog.dogSet;
//        return set;
//        dog.dogFactory();
//        List<Dog> list = dog.dogList;
//        return list;
    }

    public class Cat {
        private String message = "Hello";

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

    public class Dog {
        private int age = 5;
        private String name = "WaPuK";

        private List<Dog> dogList = new ArrayList<>();
        private Dog[] dogSet = new Dog[age];

        public Dog[] getDogSet() {
            return dogSet;
        }

        public void setDogSet(Dog[] dogSet) {
            this.dogSet = dogSet;
        }

        public void dogSetFactory() {
            for (int i = 0; i < dogSet.length; i++) {
                dogSet[i] = new Dog();
                dogSet[i].setName(i + "Gav" + i);
                dogSet[i].setAge(i);
            }
        }

        public void dogFactory() {
            for (int i = 0; i < age; i++) {
                dogList.add(new Dog());
                dogList.get(i).setName("RrR" + i);
                dogList.get(i).setAge(i);
            }
        }

        public List<Dog> getDogList() {
            return dogList;
        }

        public void setDogList(List<Dog> dogList) {
            this.dogList = dogList;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
