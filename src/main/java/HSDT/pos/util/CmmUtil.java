package HSDT.pos.util;
//빈칸이 들어오면 빈 칸을 넘겨주도록 하는 유틸
public class CmmUtil {
    public static String nvl(String str, String chg_str) {
        String res;

        if (str == null) {
            res = chg_str;
        } else if (str.equals("")) {
            res = chg_str;
        } else {
            res = str;
        }
        return res;
    }

    public static String nvl(String str){
        return nvl(str,"");
    }

    public static String checked(String str, String com_str){
        if(str.equals(com_str)){
            return " checked";
        }else{
            return "";
        }
    }

    public static String checked(String[] str, String com_str){
        for(int i=0;i<str.length;i++){
            if(str[i].equals(com_str))
                return " checked";
        }
        return "";
    }

    public static String select(String str,String com_str){
        if(str.equals(com_str)){
            return " selected";
        }else{
            return "";
        }
    }
}
