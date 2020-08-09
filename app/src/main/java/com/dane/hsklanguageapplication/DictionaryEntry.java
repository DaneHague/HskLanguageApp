package com.dane.hsklanguageapplication;

public class DictionaryEntry
{
    String hanzi;
    String pinyin;
    String translations;


    public DictionaryEntry(String hanzi, String pinyin, String translations){
        this.hanzi = hanzi;
        this.pinyin = pinyin;
        this.translations = translations;
    }

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


