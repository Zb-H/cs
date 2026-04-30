package WordSearch;
/**
 * class of words
 *
 * * Git
 *
 * -- Repository :
 *    https://github.com/Zb-H/cs/tree/main
 *
 * -- Latest version (auto-updated):
 *    https://github.com/Zb-H/cs/blob/main/cs210/Assignment03/src/WordSearch/Words.java
 */

public class Words{
    private String word;
    // constructor
    public Words(String token){
        setWord(token);
    }
    //setter
    public void setWord(String token){
        this.word = token;
    }
    //getter
    public String getWord(){
        return this.word;
    }
}
