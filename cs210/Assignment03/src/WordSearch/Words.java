package WordSearch;
/**
 * class of words
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
