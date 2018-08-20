/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.json.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:35  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class User {

    private char lucky;

    private Character character;

    private String name;

    private int id;

    private BigDecimal amount;

    private Date time;

    public char getLucky() {
        return lucky;
    }

    public void setLucky(char lucky) {
        this.lucky = lucky;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
