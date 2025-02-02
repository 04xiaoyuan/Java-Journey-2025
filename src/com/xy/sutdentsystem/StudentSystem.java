package com.xy.sutdentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {
        ArrayList<Student> list = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("----------------欢迎来到小远学生管理系统----------------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出系统");
            System.out.println("请输入您的选择：");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent(list);

                case "2" -> deleteStudent(list);

                case "3" -> updateStudent(list);

                case "4" -> queryStudent(list);

                case "5" -> {
                    System.out.println("退出系统");
                    break loop;
                    //System.exit(0);终止虚拟机运行
                }
                default -> System.out.println("当前没有这个选项，请输入正确的选项;");
            }

        }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id；");
        String id;
        while (true) {
            id = sc.next();
            if (contains(list, id)) {
                System.out.println("当前id已经存在，请重新输入id：");
            } else {
                break;
            }
        }

        System.out.println("请输入学生姓名:");
        String name = sc.next();
        System.out.println("请输入学生年龄:");
        int age = sc.nextInt();
        System.out.println("请输入学生家庭住址:");
        String address = sc.next();
        Student stu = new Student(id, name, age, address);
        list.add(stu);
        System.out.println("学生信息添加成功");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除学生的id:");
        String id;
        while (true) {
            id = sc.next();
            if (contains(list, id)) {
                int index = getIndex(list, id);
                list.remove(index);
                System.out.println("id为" + id + "的学生信息删除成功");
                break;
            } else {
                System.out.println("当前id不存在，请重新输入id；");
            }
        }

    }

    //修改学生
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改学生的id:");
        String id=sc.next();
        int index = getIndex(list,id);
        if(index==-1){
            System.out.println("当前id不存在，请重新输入id；");
            return;
        }
        Student stu = list.get(index);
        System.out.println("请输入学生修改后的姓名:");
        String name = sc.next();
        stu.setName(name);
        System.out.println("请输入学生修改后的年龄:");
        int age = sc.nextInt();
        stu.setAge(age);
        System.out.println("请输入学生修改后的家庭住址:");
        String address = sc.next();
        stu.setAddress(address);
        System.out.println("学生信息修改成功");

    }

    //查询学生
    public static void queryStudent(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后再查询：");
            return;
        }
        System.out.println("id\t\t姓名\t年龄\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }

    }

    //判断id是否在集合中存在
    public static boolean contains(ArrayList<Student> list, String id) {
//        for (int i = 0; i < list.size(); i++) {
//            Student stu = new Student();
//            if (stu.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
        return getIndex(list, id) >= 0;
    }

    //通过id获取索引
    public static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            if (stu.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}