package com.example.dmac1.mymusicapp;

public class Media {

    /**
     * media title i.e name of song, podcast, video, etc
     */
    private String mMediaTitle;

    /**
     * media length i.e the duration of the song, podcast, video, etc
     */
    private String mMediaLength;

    /**
     * media description i.e. summary of content
     */
    private String mMediaDescription;

    /**
     * media status i.e. in progress, complete
     */
    private String mMediaStatus;

    /**
     * Create a new Media object.
     *
     * @param mediaTitle       is the name of the song, podcast, video
     * @param mediaLength      is the duration of the song, podcast, video
     * @param mediaDescription is the summary of the content of the podcast, video
     * @param mediaStatus      is the status of the content of the lesson
     */
    public Media(String mediaTitle, String mediaLength, String mediaDescription, String mediaStatus) {
        mMediaTitle = mediaTitle;
        mMediaLength = mediaLength;
        mMediaDescription = mediaDescription;
        mMediaStatus = mediaStatus;

    }

    /**
     * Get the title of the piece of media.
     */
    public String getMediaTitle() {
        return mMediaTitle;
    }

    /**
     * Get the length/duration of the piece of media.
     */
    public String getMediaLength() {
        return mMediaLength;
    }

    /**
     * Get the description of the piece of media.
     */
    public String getMediaDescription() {
        return mMediaDescription;
    }

    /**
     * Get the status of the piece of media.
     */
    public String getMediaStatus() {
        return mMediaStatus;
    }


}
