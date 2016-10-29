/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.io.*;
import java.util.ArrayList;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Jeff
 */
public class SoundManager {
    public static ArrayList<SoundClip> soundClips = new ArrayList<>();
    public static ArrayList<String> clipQueue = new ArrayList<>();
    public static Sequencer sequencer;
    
    public static void initializeSoundManager(){
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
        }catch(Exception e){
            System.out.println("failed to get sequencer");
        }
    }
    public static void playMidiFile(String str){
        try{
	    // create a stream from a file
	    InputStream is = new BufferedInputStream(new FileInputStream(new File(str)));
	    // Sets the current sequence on which the sequencer operates.
	    // The stream must point to MIDI file data.
	    sequencer.setSequence(is);
	    // Starts playback of the MIDI data in the currently loaded sequence.
	    sequencer.start();
        }catch(Exception e){
            System.out.println("failed to play midi");
        }
    }
    public static void playSounds(){
        try{
            for(String s : clipQueue){
                //check cliparray for sound already loaded
                boolean needToLoad = true;
                for(SoundClip sC : soundClips){
                    if(s.equalsIgnoreCase(sC.name()) && !sC.clip().isRunning()){
                        sC.clip().setFramePosition(0);
                        sC.clip().start();
                        needToLoad = false;
                        break;
                    }
                }
                if(needToLoad){

                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));
                    soundClips.add(new SoundClip(s,clip));
                    clip.open(inputStream);
                    clip.start(); 
                }
            }
            //end
            clipQueue.clear();
        }catch(Exception e){
            System.out.println("failed to play sounds");
        }
    }
    public static void queueSound(String str){
        clipQueue.add(str);
    }
}
class SoundClip{
    private Clip clip;
    public Clip clip(){return clip;}
    public void clip(Clip b){clip = b;}
    private String name;
    public String name(){return name;}
    public void name(String b){name = b;}
    public SoundClip(String nm, Clip c){
        clip = c;
        name = nm;
    }
}
