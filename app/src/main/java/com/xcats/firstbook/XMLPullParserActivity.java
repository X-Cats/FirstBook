package com.xcats.firstbook;

import android.app.Activity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by aidan on 2/10/16.
 */

//TODO THIS IS OBSOLETE
public class XMLPullParserActivity extends Activity {
    ProfileOptionActivity profile;
    List<ProfileOptionActivity> profiles;
    String text;
    public List<ProfileOptionActivity> parse(InputStream is, String targetType){
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            //Attatch parser to xml file
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            //parser.setInput(is, null);

            int eventType = parser.getEventType();

            //Until you reach the end of the xml file, progress through it tag by tag
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = parser.getName();
                switch(eventType){
                    case XmlPullParser.START_TAG:
                        //when you reach a new employee, create employee to represent it
                        if(tagName.equalsIgnoreCase(targetType)){
                            profile = new ProfileOptionActivity();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        //when you find text save it so you can use it when you reach an end tag
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        //when you reach the end of an employee, add the employee representing it to the list of employees
                        if(tagName.equalsIgnoreCase("employee")){
                            profiles.add(profile);
                        } else if(tagName.equalsIgnoreCase("name")){
                            profile.setName(text); //if reach the end of a name tag, set name to the text that was saved from within it
                        } else if (tagName.equalsIgnoreCase("id")) {
                            profile.setPicture(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e){

        } catch (IOException e){

        }
        return profiles;
    }
}
