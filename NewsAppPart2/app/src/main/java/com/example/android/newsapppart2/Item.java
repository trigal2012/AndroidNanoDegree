package com.example.android.newsapppart2;

class Item {
    //pieces of information that make up the Item Object
    private final String sectionName;
    private final String webTitle;
    private final String contributor;
    private final String webUrl;
    private final String publicationDate;

    public Item(String sectionName, String webTitle, String contributor, String webUrl, String publicationDate) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.contributor = contributor;
        this.webUrl = webUrl;
        this.publicationDate = publicationDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getContributor() {
        return contributor;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

}
