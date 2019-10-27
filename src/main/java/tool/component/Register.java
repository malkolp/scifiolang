package tool.component;

import java.util.ArrayList;

public class Register{

    private static Register instance;
    private static ArrayList<Double> tokenList;

    private Register(){tokenList = new ArrayList<>();}

    private static void init(){
        if (instance == null) instance = new Register();
    }

    static Register get(){
        init();
        return instance;
    }

    static void end(){
        tokenList = null;
        instance = null;
    }

    static ArrayList<Double> getToken(){
        ArrayList<Double> toReturn = (ArrayList<Double>) tokenList.clone();
        clearTokenList();
        return toReturn;
    }

    private static void clearTokenList(){
        tokenList.clear();
    }

    static void register(String key){
        if (Regex.alphanum(key) | Regex.numConstant(key)){
            if (TokenTableManager.contains(key)){
                tokenList.add(TokenTableManager.token(key));
            } else {
                if (Regex.numConstant(key)){
                    registerConstant(key);
                } else if (Regex.identifier(key)) {
                    registerIdentifier(key);
                } else {
                    registerError(key);
                }
            }
        } else {
            if (Regex.numConstant(key)){
                registerConstant(key);
            } else {
                registerSymbol(key);
            }
        }
    }

    private static void registerSymbol(String key){
        if (TokenTableManager.contains(key)){
            tokenList.add(TokenTableManager.token(key));
        } else {
            if (key.length() > 1){
                StringBuilder key1 = new StringBuilder();
                String key2;
                if (key.length() > 2){
                    for (int i = 0; i < key.length()-1;i++){
                        key1.append(key.charAt(i));
                    }
                } else {
                    key1 = new StringBuilder(key.charAt(0)+"");
                }
                key2 = key.charAt(key.length()-1)+"";
                registerSymbol(key1.toString());
                registerSymbol(key2);
            } else {
                registerError(key);
            }
        }
    }

    static void registerString(String key){
        //String MANAGER
        tokenList.add(TokenTableManager.token(TokenTableManager.CONSTANT));
    }

    private static void registerConstant(String key){
        //value MANAGER
        tokenList.add(TokenTableManager.token(TokenTableManager.CONSTANT));
    }

    private static void registerIdentifier(String key){
        //identifier MANAGER
        tokenList.add(TokenTableManager.token(TokenTableManager.IDENTIFIER));
    }

    private static void registerError(String key){
        //error HANDLER
        tokenList.add(TokenTableManager.token(TokenTableManager.ERROR));
    }
}
