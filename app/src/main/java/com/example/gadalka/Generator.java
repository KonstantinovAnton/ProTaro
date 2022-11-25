package com.example.gadalka;

public class Generator {

    private int cardsNumber;

    public Generator(int cardsNumber)
    {
        this.cardsNumber = cardsNumber;
    }

    public String generateCard(){

        int flag;

        int min = 1;
        int max = 3;

        int x = (int)(Math.random()*((max-min)+1))+min;

        int[] arrayCardsId = new int[cardsNumber];
        arrayCardsId[0] = x;

        String stringArrayCardsId = x + " ";

        for(int i = 1; i < cardsNumber; i++) {

            while (true) {

                x = (int)(Math.random()*((max-min)+1))+min;

                flag = 0;
                for (int j = 0; j < i; j++) {
                    if (x == arrayCardsId[j]) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0) break;
            }

            arrayCardsId[i] = x;
            stringArrayCardsId += ", " + x;

        }
        return  stringArrayCardsId;
    }
}