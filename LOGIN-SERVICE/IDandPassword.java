import java.util.HashMap;

public class IDandPassword {
    HashMap<String, String> Logininfo = new HashMap<String, String>();

    IDandPassword(){
        Logininfo.put("Taha", "redbull");
        Logininfo.put("MohammedYasin", "greentea");
        Logininfo.put("Botir", "coffee");
    }

    protected HashMap<String, String> getLogininfo() {
        return Logininfo;
    }
}
