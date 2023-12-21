package com.example.startscreen;

import android.graphics.drawable.Drawable;

class TaggedDrawable {
    private Drawable drawable;
    private String tag;

    public TaggedDrawable(Drawable drawable, String tag) {
        this.drawable = drawable;
        this.tag = tag;
    }
    public Drawable getDrawable() {
        return drawable;
    }

    public String getTag() {
        return tag;
    }
}

