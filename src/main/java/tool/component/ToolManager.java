package tool.component;

public class ToolManager {

    public Lexer lexer(){
        return Lexer.get();
    }

    public Preprocessor preprocessor(){
        return Preprocessor.get();
    }

    public TokenTableManager tokenTableManager(){return TokenTableManager.get();}

    public void init(){
        Register.resetTokenList();

    }

    public void end(){
        Lexer.end();
        Preprocessor.end();
        TokenTableManager.end();
    }

    public void end(Object obj){
        if (obj == Lexer.get()) Lexer.end();
        else if (obj == Preprocessor.get()) Preprocessor.end();
        else if (obj == TokenTableManager.get()) TokenTableManager.end();
    }

}
