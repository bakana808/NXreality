
package com.octopod.nixium.utils;

import com.octopod.nixium.nxreality.NXRPlugin;
import java.util.regex.*;
import org.bukkit.ChatColor;

public class NXTextF {
    
    static public int width(char character){
        switch(character){
            case '*': return 5;
            case '>': return 5;
            case '<': return 5;
            case ',': return 2;
            case '!': return 2;
            case '{': return 5;
            case '}': return 5;
            case '\u00a7': return 0;
            case '[': return 4;
            case ']': return 4;
            case ':': return 2;
            case '\'': return 3;
            case '|': return 2;
            case '.': return 2;
            case '`': return 2; //filler character
            case ' ': return 4;
            case 'f': return 5;
            case 'k': return 5;
            case 'I': return 4;
            case 't': return 4;
            case 'l': return 3;
            case 'i': return 2;
            default: return 6;
        }
    }

    final static ChatColor fillerColor = ChatColor.DARK_GRAY;
    final static int wordWrapThreshold = 15;
    
    static public String block(String text, int toWidth, int alignment){return block(text, toWidth, alignment, " ");}
    static public String block(String text, int toWidth, int alignment, String fillerChar){
        
        text = cut(text, toWidth, false);

        final int extraWidth = toWidth - width(text);
        final int fillerWidth = width(fillerChar);
        int[] extra;
        String[] fill;

        if(alignment == 2){
            extra = new int[]{(int)Math.floor(extraWidth/2), (int)Math.ceil(extraWidth/2)};
            fill = new String[]{"",""};
        }else{
            extra = new int[]{extraWidth};
            fill = new String[]{""};
        }
        
        int i = 0;

        for(int e:extra) {
            
            String filler = "";
            
            while(e > fillerWidth + 1){
                filler += String.valueOf(fillerChar);
                e -= fillerWidth;
            }

            switch(e){
                case 6:
                    if(fillerWidth == 6) {filler += String.valueOf(fillerChar); break;}
                case 5:
                    if(fillerWidth == 5) {filler += String.valueOf(fillerChar); break;}
                    filler += ChatColor.BOLD.toString() + " " + ChatColor.RESET;
                    break;
                case 4:
                    if(fillerWidth == 4) {filler += String.valueOf(fillerChar); break;}
                    filler += " ";
                    break;
                case 3:
                    if(fillerWidth == 3) {filler += String.valueOf(fillerChar); break;}
                    filler += ChatColor.BOLD.toString() + fillerColor + "`" + ChatColor.RESET;
                    break;
                case 2:
                    if(fillerWidth == 2) {filler += String.valueOf(fillerChar); break;}
                    filler += fillerColor + "`" + ChatColor.RESET;
                    break;
            }

            fill[i] += filler;
            i++;
            
        }
        
        switch(alignment){
            case 0:
                text = text + fill[0];
                break;
            case 1:
                text = fill[0] + text;
                break;
            case 2:
                text = fill[0] + text + fill[1];
                break;
            default:
                break;
        }
        
        return text;
        
    }
    
    static public int width(String text){
        
        int width = 0;
        boolean bolded = false;
        char lastChar = ' ';

        for(char character:text.toCharArray()){
            if(lastChar == '\u00a7'){
                if(Character.toString(character).matches("[0-9a-fA-Fkmnor]")) {
                    bolded = false;
                }
                if(Character.toString(character).toLowerCase().equals("l")){
                    bolded = true;
                }
            }else{
                if(bolded){width += width(character) + 1;}
                else{width += width(character);}
            }
            lastChar = character;
        }

        return width;
        
    }
    
    static public String cut(String text, int toWidth, boolean wrap){
        String wrappedString = "";
        String string = "";
        int totalWidth = 0;
        int endIndexSpace = 0;
        int endIndex = 0;
        for(char character:text.toCharArray()){
            totalWidth += width(character);
            if(totalWidth >= toWidth) {
                if(endIndex - endIndexSpace <= wordWrapThreshold) {endIndex = endIndexSpace;}
                break;
            }
            if(character == ' '){endIndexSpace = endIndex;}
            endIndex += 1;
        }
        
        return text.substring(0, endIndex);
    }    
}
