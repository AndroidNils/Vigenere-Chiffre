public class VigenereWithoutArray
{
    /**
     * @param text Text, der entweder verschlüsselt oder entschlüsselt werden soll.
     */
    public String entschluesseln(String text, String key, boolean wirdVerschlusselt) {
        text = text.toUpperCase().trim();
        key = key.toUpperCase().trim();

        String result = "";
        for(int i = 0, j = 0; i < text.length(); i++, j++) {
            if(j == key.length()) j = 0;

            if(wirdVerschlusselt)
                result += (char)((text.charAt(i) - key.charAt(j) - 130) % 26 + 65);
            else
                result += (char)((text.charAt(i) - key.charAt(j) + 26) % 26 + 65);
        }

        return result;
    }
}
