package com.example.android.tourapp;

public class Item {

    //things an item will contain
    private int mCategoryId; //This will be one of Art, Eat, Event, Walk, Home
    private int mTypeId; //this will be one of Brew, Grind, or Grub
    private int mNameId; //this is the name of the event, business, artbox, or residential building (fromt the walking tour)
    private int mAddressId; //this is the location of the event, business, artbox, etc
    private int mPhoneId; //contact phone number
    private int mDetailsId; //details about the event, artbox, business, etc
    private int mArtistId; //name of artist - used in the artfragment
    private int mLatId; //latitutde value for location - used in the map view
    private int mLngId; //longitude value for location - used in map view
    private int mHoursId; //these are the open hours of the business or event
    private int mDateId;  //date when event is happening
    private int mImageResourceId = NO_IMAGE_PROVIDED; //image resource id for item

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;


    //this constructor is used to create an item object that does not have an image
    public Item(int categoryId, int dateId, int typeId,int nameId,int hoursId, int addressId,
                int phoneId,int detailsId, int artistId,int latId, int lngId ) {
        mCategoryId = categoryId;
        mTypeId = typeId;
        mNameId = nameId;
        mAddressId = addressId;
        mPhoneId = phoneId;
        mDetailsId = detailsId;
        mArtistId =artistId;
        mLatId = latId;
        mLngId = lngId;
        mHoursId = hoursId;
        mDateId = dateId;
    }

    //this constructor is used to create an item object that has an image
    public Item(int categoryId, int dateId, int typeId,int nameId,int hoursId, int addressId,
                int phoneId,int detailsId, int artistId,int latId, int lngId, int imageResourceId ) {
        mCategoryId = categoryId;
        mTypeId = typeId;
        mNameId = nameId;
        mAddressId = addressId;
        mPhoneId = phoneId;
        mDetailsId = detailsId;
        mArtistId = artistId;
        mLatId = latId;
        mLngId = lngId;
        mHoursId = hoursId;
        mDateId = dateId;
        mImageResourceId = imageResourceId;
    }

    // these are the getter methods
    public int getmCategoryId() {
        return mCategoryId;
    }

    public int getmTypeId() {
        return mTypeId;
    }

    public int getmNameId() {
        return mNameId;
    }

    public int getmAddressId() {
        return mAddressId;
    }

    public int getmPhoneId() {
        return mPhoneId;
    }

    public int getmDetailsId() {
        return mDetailsId;
    }

    public int getmArtistId() {
        return mArtistId;
    }

    public int getmLatId() {
        return mLatId;
    }

    public int getmLngId() {
        return mLngId;
    }

    public int getmHoursId() {
        return mHoursId;
    }

    public int getmDateId() {
        return mDateId;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }


    /**
     * Returns whether or not there is an image for this item.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


}
