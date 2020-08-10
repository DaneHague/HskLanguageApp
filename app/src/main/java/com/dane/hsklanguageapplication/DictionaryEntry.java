package com.dane.hsklanguageapplication;

public class DictionaryEntry
{
    int id;
    String hanzi;
    String pinyin;
    String translations;


    public DictionaryEntry(int id, String hanzi, String pinyin, String translations){
        this.id = id;
        this.hanzi = hanzi;
        this.pinyin = pinyin;
        this.translations = translations;
    }
    public int getId(){ return id;}

    public String getHanzi(){
        return hanzi;
    }

    public String getPinyin(){
        return pinyin;
    }

    public String getTranslations(){
        return pinyin;
    }
}


