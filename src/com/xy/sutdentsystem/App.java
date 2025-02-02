package com.xy.sutdentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---------------欢迎使用学生管理系统---------------");
            System.out.println("请选择操作：1登录 2注册 3忘记密码 4关闭系统");
            String choose = sc.next();
            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("关闭系统");
                    System.exit(0);
                }
                default -> System.out.println("当前没有这个选项，请输入正确的选项;");
            }
        }
    }

    //登录
    public static void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            boolean flag = contains(list, username);
            if (!flag) {
                System.out.println("用户名" + username + "未注册" + "请先注册再登录：");
                return;
            }

            System.out.println("请输入密码：");
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("当前正确的验证码为：" + rightCode);
                System.out.println("请输入验证码：");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightCode)) {
                    System.out.println("验证码正确");
                    break;
                } else {
                    System.out.println("验证码错误");
                    continue;
                }
            }
            User userInfo = new User(username, password, null, null);
            boolean result = checkUserInfo(list, userInfo);
            if (result) {
                System.out.println("登录成功，可以开始使用简易版学生管理系统");
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            } else {
                System.out.println("登录失败，用户名或密码错误");
                if (i == 2) {
                    System.out.println("登录失败，您已经输错3次，账号" + username + "已被锁定，请联系；(给出客服联系方式)");
                    return;
                } else {
                    System.out.println("登录失败，您还剩" + (2 - i) + "次机会");
                }
            }
        }

    }

    //注册
    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        //用户名注册
        String username;
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();
            boolean flag1 = checkUsername(username);
            if (!flag1) {
                System.out.println("用户名不合法，请重新输入");
                continue;
            }
            boolean flag2 = contains(list, username);
            if (flag2) {
                System.out.println("用户名" + username + "已经存在，请重新输入");
                continue;
            } else {
                System.out.println("用户名" + username + "可用");
                break;
            }

        }
        //密码注册
        String password;
        while (true) {
            System.out.println("请输入注册密码：");
            password = sc.next();
            System.out.println("请再次输入注册密码：");
            String againPassword = sc.next();
            if (password.equals(againPassword)) {
                System.out.println("两次输入的密码一致，请继续操作：");
                break;
            } else {
                System.out.println("两次输入的密码不一致，请重新输入");
                continue;
            }
        }
        //身份证号码验证
        String userID;
        while (true) {
            System.out.println("请输入身份证号码：");
            userID = sc.next();
            boolean flag3 = checkUserID(userID);
            if (!flag3) {
                System.out.println("身份证号码不合法，请重新输入");
                continue;
            } else {
                System.out.println("身份证号码满足要求,请继续操作：");
                break;
            }
        }
        //手机号码验证
        String pNumber;
        while (true) {
            System.out.println("请输入手机号码：");
            pNumber = sc.next();
            boolean flag4 = checkPhoneNumber(pNumber);
            if (!flag4) {
                System.out.println("手机号码不合法，请重新输入");
                continue;
            } else {
                System.out.println("手机号码满足要求,请继续操作：");
                break;
            }
        }
        User user = new User(username, password, userID, pNumber);
        list.add(user);
        System.out.println("注册成功!");

    }


    //忘记密码
    private static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (!flag) {
            System.out.println("用户名" + username + "未注册，请先注册");
            return;
        }
        System.out.println("请输入身份证号码：");
        String userID = sc.next();
        System.out.println("请输入手机号码：");
        String pNumber = sc.next();
        int index = findIndex(list, username);
        User user = list.get(index);
        if (!(user.getUserID().equals(userID) || user.getpNumber().equals(pNumber))) {
            System.out.println("身份证号码或手机号码不正确，不能修改密码");
            return;
        }
        String Password;
        while (true) {
            System.out.println("请输入新密码：");
            Password = sc.next();
            System.out.println("请再次输入新密码：");
            String againPassword = sc.next();
            if (Password.equals(againPassword)) {
                System.out.println("两次输入的密码一致，请继续操作：");
                break;
            } else {
                System.out.println("两次输入的密码不一致，请重新输入；");
                continue;
            }
        }
        user.setPassword(Password);
        System.out.println("密码修改成功！");

    }

    //检查用户名是否唯一
    private static boolean checkUsername(String username) {
        int len = username.length();
        if (len < 3 || len > 15) {
            System.out.println("用户名长度必须在3-15位之间");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                System.out.println("用户名只能包含字母和数字");
                return false;
            }
        }
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }
        return count > 0;
    }

    //检查用户名是否存在
    private static boolean contains(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //身份证号码校验
    private static boolean checkUserID(String userID) {
        if (userID.length() != 18) {
            System.out.println("身份证号码长度必须为18位");
            return false;
        }
        if (userID.startsWith("0")) {
            System.out.println("身份证号码不能以0开头");
            return false;
        }
        for (int i = 0; i < userID.length() - 1; i++) {
            char c = userID.charAt(i);
            if (!((c >= '0' && c <= '9'))) {
                System.out.println("身份证号码前17位只能包含数字！");
                return false;
            }
        }
        char endChar = userID.charAt(userID.length() - 1);
        if ((endChar >= '0' && endChar <= '9') || (endChar == 'X') || (endChar == 'x')) {
            return true;
        } else {
            System.out.println("身份证号码最后一位只能是数字或X或x");
            return false;
        }
    }

    //手机号码校验
    private static boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) {
            System.out.println("手机号码长度必须为11位");
            return false;
        }
        char firstChar = phoneNumber.charAt(0);
        if (firstChar == '0') {
            System.out.println("手机号码不能以0开头");
            return false;
        }

        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (!((c >= '0' && c <= '9'))) {
                System.out.println("手机号码只能包含数字");
                return false;
            }
        }
        return true;
    }

    //生成一个验证码
    private static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < list.size(); i++) {
            int index = r.nextInt(list.size());
            sb.append(list.get(index));
        }
        int num = r.nextInt(10);
        sb.append(num);
        char[] arr = sb.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            char temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        return new String(arr);
    }

    // 检查用户名和密码是否匹配
    private static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(userInfo.getUsername()) && user.getPassword().equals(userInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    //查找用户
    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }
}
