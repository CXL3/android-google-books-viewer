package com.pocorusso.holiducodingtask;


/**
 * Simple object representation of a volume (of which can be a book or a magazine)
 * with getters and setters.
 */
public class Volume {
    private String mTitle;
    private String mImageUrl;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;

    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
