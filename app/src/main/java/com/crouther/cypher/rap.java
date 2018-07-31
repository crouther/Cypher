package com.crouther.cypher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class rap {

    public List<String> original;
    public List<String> famousQuote;
    public List<String> oldSchoolQuote;
    public List<String> newSchoolQuote;
    public List<String> songInspired;

    public rap(){

        original = new ArrayList<String>(
                Arrays.asList(
                    "Original Or User Submitted","Savage","Limp biscuits",
                    "With an 80s funk","Squad","Hot off the skillet","Lickety split",
                    "Please and thank you","Smoothered by the rest","Preach",
                    "I call that jungle fever","Tested by the best","Moving down the 6track",
                    "Once in a lifetime","Caught up with the bass","Back it up",
                    "I did it without one","I bought with old money","Where's my grammy",
                    "It won't happen oh no","Bearer of bad news","The greatest story ever told",
                    "To a women so heartless","How could you be so…","What do I know","Wake up")
        );

        famousQuote = new ArrayList<String>(
                Arrays.asList(
                    "Direct Famous Quote","I'm always hanging with shooters (Lil Uzi Vert)",
                    "Ren said it with authority (MC Ren)","My money on my mind (Snoop Dogg)",
                    "I got 99 problems (Ice T)","It's like a jungle sometimes (Grand Master Flash and the Furious Five)",
                    "How you take my '09 jail tweet and run? (Remy Ma)","Talkin' about bringin' knives to a fight with guns (Remy Ma)",
                    "You will never know what I was in (Future)","Quick with the hands, ready (Frank Ocean)",
                    "I said a hip hop (Wonder Mike)", "Trouble on my mind (Chuck D)",
                    "His palms are sweaty (Eminem)","It was all a dream (The Notorious B.I.G)",
                    "Y'all make this shit sound so easy (J. Cole)","When my tongue goes down below (The Notorious B.I.G)",
                    "If I could say what's on my mind (Iamsu)",
                    "Had a lot of crocks try to steal your heart (Lil Wayne)","First name greatest (Drake)",
                    "She had a bad hip like a fanny pack (Kanye West)","Wake up and smell the garden (Lil Wayne)",
                    "This one right here is for the people (Nas)","Whose world is this (Nas)",
                    "And you can’t beat that with a  bat (Black Sheep)",
                    "To all my peoples in the struggle (The Notorious B.I.G)",
                    "I love it when they call me big poppa (The Notorious B.I.G)",
                    "Harder, Better, Faster, Stronger (Kanye West)",
                    "You'll never find nobody better than me (Kanye West)",
                    "Okay, lamborghini mercy (Kanye West)","No one man should have all that power (Kanye West)",
                    "I don't blame you much for wanting to be free (Rihanna)")
        );

        oldSchoolQuote = new ArrayList<String>(
                Arrays.asList(
                    "90's Generic Line","Nutty buddy","Money talks","Ooh baby","I have to testify",
                    "Gangstas walk","Pop a squat","Never let it go","I'll sleep when I'm dead",
                    "A diamond ring","Trouble holds me","You just stuck in a loop",
                    "Just hustling through","Who needs an enemy","Mirror on the wall",
                    "It's going up","You with a star","So fresh I keep it breeze","I'm easy",
                    "Don't call it a comeback","And if I die for before I wake",
                    "Can I get a witness", "When I die","First things first","You with a star",
                    "Don't push me","I ain't a killer","You're just mad cause you're ass is old",
                    "Who made up words","You might catch me on move","This or that",
                    "If my train goes off the track","Do da dippity","That's what I love",
                    "Would you try to die today","____ try to rob the gates")
        );

        newSchoolQuote = new ArrayList<String>(
                Arrays.asList(
                    "2000's Generic Quote","I keep the uzi","I just wanted you to know",
                    "Shotie want a thug", "So turn the kid raps loud","It's been a long time",
                    "So I call her big booty","When it comes to sex","That's that shit I dont like",
                    "Drug dealer sheik","Everythings for sale","The feeling just comes and goes",
                    "I don't get weak in the knees","I just wanted you to know","Your booty is mine",
                    "I got that A1 credit","Run for my money","Second chances are a beautiful thing",
                    "I was the one I did it","Your far from the usual","It may not mean nothing to y'all",
                    "She the one for me","Life is like a rollarcoaster right before it drops",
                    "My foots sleeping on the gas","Felt the body turn cold","I'd rather be alive",
                    "State of mind","I got some down b____ I can call","I'm on that good…",
                    "Stay woke","From nothing to something")
        );

        songInspired = new ArrayList<String>(
                Arrays.asList(
                        "Inspired by a Song Name","Stick Talk","Talk is cheap","It wasn't me",
                        "Birds and the bees","I'm on a boat","Skinny love","Crazy stupid love",
                        "How to love?","Bubble butt","Not in my house (Nelly)",
                        "The choice is yours (Black Sheep)","Can I kick It (Tribe Called Quest)",
                        "Do I Want to Know","I wish I knew you","Somewhere over the rainbow",
                        "If heaven was a mile away (Nas)","Flashing Lights","Famous…",
                        "Bad and Boujee","What's going on")
        );
    }

    public void addOriginal(String newOriginal){
        original.add(newOriginal);
    }

    public void addFamous(String newFamous){
        famousQuote.add(newFamous);
    }

    public void addOldSchool(String newOldSchool){
        oldSchoolQuote.add(newOldSchool);
    }

    public void addNewSchool(String newNewSchool){
        newSchoolQuote.add(newNewSchool);
    }

    public void addSong(String newSong){
        songInspired.add(newSong);
    }

    public List<String> getOrignal(){
        return original;
    }

    public List<String> getFamousQuote(){
        return famousQuote;
    }

    public List<String> getOldSchoolQuote(){
        return oldSchoolQuote;
    }

    public List<String> getNewSchoolQuote() {
        return newSchoolQuote;
    }

    public List<String> getSongInspired() {
        return songInspired;
    }
}
