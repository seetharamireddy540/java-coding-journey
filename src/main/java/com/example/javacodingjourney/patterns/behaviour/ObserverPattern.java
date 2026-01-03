package com.example.javacodingjourney.patterns.behaviour;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        NewsChannel channel1 = new NewsChannel("CNN");
        NewsChannel channel2 = new NewsChannel("BBC");

        agency.attach(channel1);
        agency.attach(channel2);

        agency.setNews("Breaking: Design patterns are awesome!");
    }

    // Subject interface
    private static interface Subject {
        void attach(Observer observer);

        void detach(Observer observer);

        void notifyObservers();
    }

    // Observer interface
    private static interface Observer {
        void update(String message);
    }

    // Concrete subject
    private static class NewsAgency implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String news;

        public void attach(Observer observer) {
            observers.add(observer);
        }

        public void detach(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }

        public void setNews(String news) {
            this.news = news;
            notifyObservers();
        }
    }

    // Concrete observers
    private static class NewsChannel implements Observer {
        private String name;

        public NewsChannel(String name) {
            this.name = name;
        }

        public void update(String news) {
            System.out.println(name + " received news: " + news);
        }
    }

}
