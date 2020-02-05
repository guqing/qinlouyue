package xyz.guqing.app.bean.dictmap;

import xyz.guqing.app.bean.dictmap.base.AbstractDictMap;

public class CommonDict extends AbstractDictMap {
    @Override
    public void init() {
        put("id","ID");
        put("name","名称");
        put("code","编码");
        put("title","标题");
    }

    @Override
    protected void initBeWrapped() {

    }
}
