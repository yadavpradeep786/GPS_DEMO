package com.example.gpsdemo.utils;

/**
 * Created by Bablu on 5/4/2017.
 */

public class SliderItemObject {

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    int Image;
    String Text;

    public SliderItemObject(int Image, String Text){
        this.Image =Image;
        this.Text = Text;
    }

}
