import java.util.Random;

public class RandomCodes {
    public static void main(String[] args) {
        char[] chars = new char[52];
        for (int i = 0; i < chars.length; i++) {
            if (i<26){
                //添加小写字母
                chars[i] = (char) ('a'+i);
            }else if (i>=26){
                //添加大写字母
                chars[i] = (char) ('A'+i-26);
            }
        }
        String s = "";
        Random r = new Random();
        for(int i=0;i<4;i++){
            int randomindex = r.nextInt(chars.length);
            s += chars[randomindex];
        }
        int num = r.nextInt(10);
        s += num;
        System.out.println(s);
    }
}
