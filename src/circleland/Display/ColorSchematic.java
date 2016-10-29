/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ColorSchematic {
    private static final Random rand = new Random();
    public static final Color[] DESERT_COLORSCHEME = desertColorScheme();
    public static final Color[] GRASS_COLORSCHEME = grassColorScheme();
    public static final Color[] TIDE_COLORSCHEME = tideColorScheme();
    public static final Color[] MAGIC_COLORSCHEME = magicColorScheme();
    public static final Color[] MUDDY_COLORSCHEME = muddyColorScheme();
    public static final Color[] RUSTIC_COLORSCHEME = rusticColorScheme();
    public static final Color[] BROWN_COLORSCHEME = brownColorScheme();
    public static final Color[] SHIRT_COLORSCHEME = shirtColorScheme();
    public static final Color[] HIGHWAY_COLORSCHEME = highwayColorScheme();
    public static final Color[] STEEL_COLORSCHEME = steelColorScheme();
    
    public static Color[] randColorScheme(){
        int colorChoice = rand.nextInt(10);
        switch(colorChoice){
            case 0: return desertColorScheme();
            case 1: return grassColorScheme();
            case 2: return tideColorScheme();
            case 3: return magicColorScheme();
            case 4: return muddyColorScheme();
            case 5: return rusticColorScheme();
            case 6: return brownColorScheme();
            case 7: return shirtColorScheme();
            case 8: return highwayColorScheme();
            case 9: return magicColorScheme();
        }
        return null;
    }
    public static Color[] desertColorScheme()
    {
        Color[] colors = new Color[7];
        colors[0] = new Color(255,50,0);
        colors[1] = new Color(230,115,0);
        colors[2] = new Color(255,150,0);
        colors[3] = new Color(255,205,0);
        colors[4] = new Color(255,255,0);
        colors[5] = new Color(255,100,0);
        colors[6] = new Color(180,80,0);
        return colors;
    }    
    public static Color[] grassColorScheme()
    {
        Color[] colors = new Color[7];
        colors[0] = new Color(64,255,0);
        colors[1] = new Color(51,255,51);
        colors[2] = new Color(0,255,0);
        colors[3] = new Color(140,255,26);
        colors[4] = new Color(179,255,51);
        colors[5] = new Color(102,255,51);
        colors[6] = new Color(0,128,0);
        return colors;
    }
    public static Color[] tideColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0xd5f4e6);
        colors[1] = new Color(0x80ced6);
        colors[2] = new Color(0xfefbd8);
        colors[3] = new Color(0x618685);
        return colors;
    }
    public static Color[] magicColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0xffef96);
        colors[1] = new Color(0x50394c);
        colors[2] = new Color(0xb2b2b2);
        colors[3] = new Color(0xf4e1d2);
        return colors;
    }
    public static Color[] muddyColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0xb2b2b2);
        colors[1] = new Color(0xf4e1d2);
        colors[2] = new Color(0xf18973);
        colors[3] = new Color(0xbc5a45);
        return colors;
    }
    public static Color[] rusticColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0xc8c3cc);
        colors[1] = new Color(0x563f46);
        colors[2] = new Color(0x8ca3a3);
        colors[3] = new Color(0x484f4f);
        return colors;
    }
    public static Color[] brownColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0xfff2df);
        colors[1] = new Color(0xd9ad7c);
        colors[2] = new Color(0xa2836e);
        colors[3] = new Color(0x674d3c);
        return colors;
    }
    public static Color[] shirtColorScheme()
    {
        Color[] colors = new Color[4];
        colors[0] = new Color(0x588c7e);
        colors[1] = new Color(0xf2e394);
        colors[2] = new Color(0xf2ae72);
        colors[3] = new Color(0xd96459);
        return colors;
    }
    public static Color[] highwayColorScheme()
    {
        Color[] colors = new Color[7];
        colors[0] = new Color(0x633517);
        colors[1] = new Color(0xa6001a);
        colors[2] = new Color(0xe06000);
        colors[3] = new Color(0xee9600);
        colors[4] = new Color(0xffab00);
        colors[5] = new Color(0x004d33);
        colors[6] = new Color(0x00477e);
        return colors;
    }
    public static Color[] steelColorScheme()
    {
        Color[] colors = new Color[7];
        colors[0] = new Color(0x708090);
        colors[1] = new Color(0x808080);
        colors[2] = new Color(0x696969);
        colors[3] = new Color(0xA9A9A9);
        colors[4] = new Color(0xD3D3D3);
        return colors;
    }
    
}
