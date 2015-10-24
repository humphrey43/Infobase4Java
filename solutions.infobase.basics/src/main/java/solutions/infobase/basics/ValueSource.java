/* ValueSource - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package solutions.infobase.basics;

/**
 * @author tha1hh
 * 
 * Eine ValueSource speichert zu einem Namen einen String-Wert
 *
 */
public interface ValueSource
{
    /**
     * Lesen des Wertes zu einem Namen
     * @param key	der Name des Wertes
     * @return	der gespeicherte Wert oder null
     */
    public String getHandledValue(String key);
    
    /**
     * Lesen des Wertes zu einem Namen
     * 
     * Wenn der Name nicht vorhanden ist, wird der 
     * angegebene Default-Wert zurückgegeben
     * 
     * @param key	der Name des Wertes
     * @param defaultvalue	der Default-Wert
     * @return	der gespeicherte Wert oder der Default-Wert
     */
    public String getHandledValue(String key, String defaultvalue);
    
    /**
     * Setzen des Wertes zu einem Namen
     * 
     * Wenn der Name bereits vorhanden ist, wird der
     * Wert überschrieben
     * 
     * @param key	der Name des Wertes
     * @param value	der neue Wert
     */
    public void setHandledValue(String key, String value);
}
