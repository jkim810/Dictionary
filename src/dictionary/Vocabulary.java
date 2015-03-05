/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Junbum
 */
public class Vocabulary{
    String word = "not set yet";
    String meaning = "not set yet";
    
    public Vocabulary(){
        
    }
    
    public Vocabulary(String newWord, String newMeaning){
        word = newWord;
        meaning = newMeaning;
    }
    
    public String getName(){
        return word;
    }
}