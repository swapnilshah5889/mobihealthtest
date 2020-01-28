package com.example.mobihealthtest;

import java.io.Serializable;
import java.util.List;

public class CardsModel implements Serializable {

    String Title;
    List<Integer> cards;

    public CardsModel(String title, List<Integer> cards) {
        Title = title;
        this.cards = cards;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }
}
