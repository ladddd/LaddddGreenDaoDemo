package com.example;

import de.greenrobot.daogenerator.Entity;

import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(1, "com.example.greendao");

        Entity entity = schema.addEntity("Game");

        entity.addIdProperty();
        entity.addIntProperty("level");
        entity.addIntProperty("point");

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema,
                "../LaddddGreenDaoDemo/app/src/main/gen");
    }
}
