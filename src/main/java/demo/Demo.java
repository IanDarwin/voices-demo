package demo;

import org.pitest.voices.Chorus;
import org.pitest.voices.ChorusConfig;
import org.pitest.voices.Models;
import org.pitest.voices.Voice;
import org.pitest.voices.audio.Audio;
import org.pitest.voices.uk.EnUkDictionary;

import java.nio.file.Path;

import static org.pitest.voices.ChorusConfig.chorusConfig;

class Demo {
	public static void main(String[] args) { 
        ChorusConfig config = chorusConfig(EnUkDictionary.en_uk());
        try (Chorus chorus = new Chorus(config)) {
            Voice alba = chorus.voice(Models.albaMedium());
            Voice jenny = chorus.voice(Models.jennyDiocoMedium());
  
            Audio audio = alba.say("Hello there, I'm vaguely Scottish");
            audio.append(jenny.say("I'm not."));
            audio.append(alba.withGain(0.5f).say("I am much quieter"));
            
            audio.save(Path.of("/tmp/bleah.dat"));
            
            audio.play(); // block while audio plays
        }
	}
}
